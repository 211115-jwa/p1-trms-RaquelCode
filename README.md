Tuition Reimbursement Management System

The goal of this project is for you to work with Agile methodology, testing (unit, integration, and e2e), and front end technologies.

Your Jobs

Read through the given TRMS specifications document in order to understand the needs of the application and expected functionality.
Familiarize yourself with the existing code base. This is almost an MVP for the TRMS application, except that it is still missing a front end and currently only has endpoints for submitting reimbursement requests and viewing your own submitted requests.
Look at the provided ERD for the current database in src/main/resources and write the necessary DDL to create these tables in your own database. It may be valuable to look at the DAOs for help, as well as write unit tests for the DAOs to make sure that they work with the tables that you create.
Build a front end to complete the MVP that is provided, allowing users to submit and view requests.
From here, choose at least three features that you want to add to the application based on the specifications provided. Use BDD with Cucumber to plan the expected behavior of features, then implement each feature using Agile methodology.
Test each feature that you've added with Selenium using your Cucumber feature files.
Technical Requirements

Back end must be built off of the existing code base: JDBC, Javalin, JUnit, Mockito.
Database must be PostgreSQL. You may use either a local database or an AWS RDS.
Unit tests must exist for DAOs (JUnit) and services (JUnit/Mockito).
Cucumber/Selenium tests must exist for all new features that you add, as well as the submitting/viewing features.
Front end must be built using HTML/CSS and JavaScript. Styling libraries like Bootstrap are allowed.
