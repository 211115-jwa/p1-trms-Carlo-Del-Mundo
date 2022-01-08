package com.revature.controllers;

import java.util.Map;

import com.revature.beans.Employee;
import com.revature.exceptions.LoginCredentialsException;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

public class UsersController {
	private static UserService userServ = new UserServiceImpl();
	
	public static void logIn(Context ctx) {
		Map<String,String> credentials = ctx.bodyAsClass(Map.class);
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		try {
			Employee person = userServ.logIn(username, password);
			String token = Integer.toString(person.getEmpId());
			ctx.result(token);
		} catch (LoginCredentialsException e) {
			ctx.status(404);
			ctx.result(e.getMessage());
		}
	}
}
