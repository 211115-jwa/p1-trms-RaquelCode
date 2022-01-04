package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.RequestsController;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
		});
	}

}
