package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.RequestsController;
import com.revature.controllers.UsersController;

public class TRMSApp {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		});
		
		app.start();
		
//		app.before("/requests/*", ctx -> {
//			if(!ctx.method().equals("OPTIONS")) {
//				ctx.header("Access-Control-Allow-Headers", "Token");
//				ctx.header("Access-Control-Expose-Headers", "Token");
//				
//				String token = ctx.header("header");
//				System.out.println(token);
//				if(token == null) ctx.status(HttpCode.UNAUTHORIZED);
//			}
//		});
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
				path("/approver/{id}", () -> {
					get(RequestsController::getRequestsByApprover);
					path("/approve", () -> {
						post(RequestsController::approveRequest);
					});
					path("/reject", () -> {
						post(RequestsController::rejectRequest);
					});
				});
			});
			
			path("/users", () -> {
				path("/auth", () -> {
					post(UsersController::logIn);
				});
				path("/{id}", () -> {
					get(UsersController::getUserById);
					
					path("/auth", () -> {
						get(UsersController::checkLogin);
					});
				});
				
			});
		});
		
	}

}
