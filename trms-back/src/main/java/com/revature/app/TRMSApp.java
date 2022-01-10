package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import io.javalin.plugin.json.JsonMapper;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.RequestsController;
import com.revature.controllers.UsersController;

public class TRMSApp {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		});
		
		app.start();
				
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
				path("/approver/{id}", () -> {
					get(RequestsController::getRequestsByApprover);
				});
				path("/approve", () -> {
					post(RequestsController::approveRequest);
				});
				path("/reject", () -> {
					post(RequestsController::rejectRequest);
				});
				path("/options", () -> {
					get(RequestsController::getOptions);
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

