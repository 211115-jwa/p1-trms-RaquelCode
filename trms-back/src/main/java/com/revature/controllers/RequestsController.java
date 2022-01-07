package com.revature.controllers;

import java.util.Set;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class RequestsController {
	private static EmployeeService empServ = new EmployeeServiceImpl();
	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee empl = new Employee();
	private static Logger log = LogManager.getLogger(RequestsController.class);

	public static void submitReimbursementRequest(Context ctx) {
		Reimbursement request = ctx.bodyAsClass(Reimbursement.class);
		int reqId = empServ.submitReimbursementRequest(request);
		if (reqId != 0) {
			ctx.status(HttpCode.CREATED);
			request.setReqId(reqId);
			ctx.json(request);
		} else {
			ctx.status(400);
			ctx.result("The submission went wrong. Please try again.");
		}
	}

	public static void getAllEmployees(Context ctx) {

		Set<Employee> employees = empDAO.getAll();
		log.info("getting all of the employees");

		ctx.json(employees);
	}

	public static void getRequestsByRequestor(Context ctx) {
		String requestorIdStr = ctx.pathParam("id");

		try {
			int requestorId = Integer.valueOf(requestorIdStr);
			Employee requestor = empServ.getEmployeeById(requestorId);

			if (requestor != null) {
				ctx.json(empServ.getReimbursementRequests(requestor));
			} else {
				ctx.status(404);
				ctx.result("The user you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
	}

	public static void viewEmployeeById(Context ctx) {

		int viewEmployeeid = Integer.parseInt(ctx.pathParam("empId"));
		log.info("getting employee by id: " + viewEmployeeid);
		try {

			empl = empDAO.getById(viewEmployeeid);
			if (viewEmployeeid != 0) {
				ctx.json(empl);
			} else {
				ctx.status(404);
				ctx.result("The employee does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Employee ID must be an integer. Please try again.");
		}

	}

}
