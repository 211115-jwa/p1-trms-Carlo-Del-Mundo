package com.revature.controllers;

import java.util.Map;

import com.revature.beans.Employee;
import com.revature.exceptions.LoginCredentialsException;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class UsersController {
	private static UserService userServ = new UserServiceImpl();
	
	public static void logIn(Context ctx) {
		Map<String,String> credentials = ctx.bodyAsClass(Map.class);
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		try {
			Employee user = userServ.logIn(username, password);
			String token = Integer.toString(user.getEmpId());
			ctx.result(token);
		} catch (LoginCredentialsException e) {
			ctx.status(404);
			ctx.result(e.getMessage());
		}
	}
	
	public static void getUserById(Context ctx) {
		try {
			int userId = Integer.parseInt(ctx.pathParam("id"));
			Employee user = userServ.getUserById(userId);
			
			if(user != null) {
				ctx.json(user);
			} else {
				ctx.status(404);
			}
		} catch(NumberFormatException e) {
			ctx.status(400);
			ctx.result("User ID must be a numeric value");
		}
	}
	
	public static void checkLogin(Context ctx) {
		String token = ctx.body();
		try {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Employee loggedInUser = userServ.getUserById(id);
			if(loggedInUser != null) {
				ctx.json(loggedInUser);
			} else {
				ctx.status(HttpCode.UNAUTHORIZED);
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("User ID and token must be numeric values");
		}
	}
}
