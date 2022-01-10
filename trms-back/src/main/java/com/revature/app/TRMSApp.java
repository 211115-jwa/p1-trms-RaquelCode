package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.EmployeesController;
import com.revature.controllers.RequestsController;
import com.revature.controllers.LoginController;
import com.revature.controllers.ReviewsController;

public class TRMSApp {

	/*
	Use BDD with Cucumber to plan the expected
	 * behavior of features, then implement each feature using Agile methodology.
	 * Test each feature that you've added with Selenium using your Cucumber feature
	 * files.
	 * 
	 */

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {

			config.enableCorsForAllOrigins();
		}).start();

		app.before("/requests/*/*", ctx -> {
			if (!ctx.method().equals("OPTIONS")) {
				ctx.header("Access-Control-Allow-Headers", "Token");
				ctx.header("Access-Control-Expose-Headers", "Token");

				String token = ctx.header("Token");
				if (token == null)
					ctx.status(HttpCode.UNAUTHORIZED);
					ctx.result("  and this");
			}
		
		});
		
		
		app.routes(() -> {

			path("/requests/", () -> {
				post(RequestsController::submitReimbursementRequest);

				path("pending/{id}", () -> {// requests/pending/id
					get(ReviewsController::getByApprover);
				});
				path("approve/{id}", () -> {// request/approve/{id}
					get(ReviewsController::ApproveRequest);
				});
				path("reject/{id}", () -> {// requests/reject/{id}
					get(ReviewsController::RejectRequest);
				});
				path("requestor/{id}", () -> {// /requests/requestor/id
					get(RequestsController::getRequestsByRequestor);
				});
			});

			
	

					
			path("/login", () -> {
				post(LoginController::register); // register
				path("/auth", () -> {
					post(LoginController::logIn); // login
				});
				path("/{id}", () -> {
					get(EmployeesController::viewEmployeeById); // get user by id
					put(LoginController::updateUser); // update user
					path("/auth", () -> {
						get(LoginController::checkLogin); // check login
					});
				});
			});
		});
	}

}