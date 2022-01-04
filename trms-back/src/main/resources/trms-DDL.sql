--CREATE SCHEMA trms;

drop table if exists status cascade;
drop table if exists grading_format cascade;
drop table if exists user_role cascade;
drop table if exists comment cascade;
drop table if exists department cascade;
drop table if exists event_type cascade;
drop table if exists employee cascade;
drop table if exists reimbursement cascade;



create table status (
	status_id serial primary key,
	status_name varchar(30),
	approver varchar(30));

create table grading_format (
	format_id serial primary key,
	format_name varchar(50),
	example varchar(250));

create table user_role (
	role_id serial primary key,
	role_name varchar(50));

create table comment (
	comment_id serial primary key,
	req_id integer not null,
	approver_id integer not null,
	comment_text varchar(100),
	sent_at timestamp );

create table department (
	dept_id serial primary key,
	dept_name varchar(30),
	dept_head_id integer);

create table event_type (
	type_id serial primary key,
	type_name varchar(40),
	percent_coverage numeric(3,2) not null,
	check (percent_coverage <= 1.00 and percent_coverage >= 0.00));

create table employee (
	emp_id serial primary key,
	first_name varchar(40),
	last_name varchar(40),
	username varchar(50) unique,
	passwd varchar(30),
	role_id integer not null,
	funds real not null,
	supervisor_id integer,
	dept_id integer
);

create table reimbursement (
	req_id serial primary key,
	emp_id integer not null,
	event_date date not null,
	event_time time not null,
	location varchar(100),
	description varchar(250),
	cost real not null,
	grading_format_id integer not null,
	event_type_id integer not null,
	status_id integer not null);



ALTER TABLE comment ADD CONSTRAINT req_id FOREIGN KEY (req_id) REFERENCES reimbursement (req_id) MATCH FULL;
ALTER TABLE comment ADD CONSTRAINT approver_id FOREIGN KEY (approver_id) REFERENCES employee (emp_id) MATCH FULL;
ALTER TABLE department ADD CONSTRAINT dept_head_id FOREIGN KEY (dept_head_id) REFERENCES employee (emp_id) MATCH FULL;
ALTER TABLE employee ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES user_role (role_id) MATCH FULL;
ALTER TABLE employee ADD CONSTRAINT supervisor_id FOREIGN KEY (supervisor_id) REFERENCES employee (emp_id) MATCH FULL;
ALTER TABLE employee ADD CONSTRAINT dept_id FOREIGN KEY (dept_id) REFERENCES department (dept_id) MATCH FULL;
ALTER TABLE reimbursement ADD CONSTRAINT emp_id FOREIGN KEY (emp_id) REFERENCES employee (emp_id) MATCH FULL;
ALTER TABLE reimbursement ADD CONSTRAINT grading_format_id FOREIGN KEY (grading_format_id) REFERENCES grading_format (format_id) MATCH FULL;
ALTER TABLE reimbursement ADD CONSTRAINT event_type_id FOREIGN KEY (event_type_id) REFERENCES event_type (type_id) MATCH FULL;
ALTER TABLE reimbursement ADD CONSTRAINT status_id FOREIGN KEY (status_id) REFERENCES status (status_id) MATCH FULL;