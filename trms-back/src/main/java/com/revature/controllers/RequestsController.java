package com.revature.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.ReimbursementDAO;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.RequestReviewService;
import com.revature.services.RequestReviewServiceImpl;
import com.revature.utils.DAOFactory;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class RequestsController {
	private static EmployeeService empServ = new EmployeeServiceImpl();
	private static RequestReviewService reqServ = new RequestReviewServiceImpl();
	
	/**
	 * Retrieves the submitted reimbursement request from the
	 * HTTP request body and sends it to be inserted in the database.
	 * <p>
	 * If the insertion is not successful, sends an HTTP response
	 * with a status code of 400 (Bad Request) and a message stating
	 * that something went wrong.
	 * <p>
	 * If the insertion is successful, sends an HTTP response with
	 * a status code of 201 (Created) and the submitted request with
	 * its newly generated ID.
	 * <p>
	 * This method should be handling a POST request.
	 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void submitReimbursementRequest(Context ctx) {
		Map<String,String> data = ctx.bodyAsClass(Map.class);
		Reimbursement request = new Reimbursement();
		
		System.out.println(data);
		request.setEventDate(LocalDate.parse(data.get("eventDate")));
		request.setEventTime(LocalTime.parse(data.get("eventTime")));
		request.setLocation(data.get("location"));
		request.setCost(Double.parseDouble(data.get("cost")));
		request.setGradingFormat(reqServ.getGradingFormatByID(Integer.parseInt(data.get("gradingFormat"))));
		request.setEventType(reqServ.getEventTypeByID(Integer.parseInt(data.get("eventType"))));
		request.setDescription(data.get("description"));
		request.setRequestor(empServ.getEmployeeById(Integer.parseInt(String.valueOf(data.get("userId")))));
//		request.setSubmittedAt(null)
//		System.out.println(String.valueOf(data.get("userId")));
//		System.out.println(request.getCost());
//		
		int reqId = empServ.submitReimbursementRequest(request);
		if (reqId != 0) {
			ctx.status(HttpCode.CREATED);
			request.setReqId(reqId);
			ctx.json(request);
		} else {
			ctx.status(400);
			ctx.result("Something went wrong with your submission. Please try again.");
		}
	}
	
	/**
	 * Sends an HTTP response containing the reimbursement requests
	 * associated with a particular employee who submitted them based
	 * on that employee's ID (which is sent as a path variable).
	 * <p>
	 * If the ID is of the correct format and the employee exists in
	 * the database, the requests are returned with a status code of 200.
	 * <p>
	 * If the ID is of the correct format but the employee does not
	 * exist, a response is sent with a status code of 404 (Not Found)
	 * and a message stating that the user does not exist.
	 * <p>
	 * If the ID is <strong>not</strong> of the correct format, a
	 * response is sent with a status code of 400 (Bad Request) and
	 * a message stating that the ID must be an integer.
	 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void getRequestsByRequestor(Context ctx) {
		String requestorIdStr = ctx.pathParam("id");
		
		try {
			int requestorId = Integer.parseInt(requestorIdStr);
			Employee requestor = empServ.getEmployeeById(requestorId);
//			System.out.println(requestor);
			
			if (requestor != null) {
				if (requestor.getRole().getRoleId() == 2 || requestor.getRole().getRoleId() == 3) {
					ctx.json(reqServ.getPendingReimbursements(requestor));
				} else if(requestor.getRole().getRoleId() == 4) {
					ctx.json(reqServ.getAllRequests());
				} else {
					System.out.println(empServ.getReimbursementRequests(requestor));
					ctx.json(empServ.getReimbursementRequests(requestor));
				}
			} else {
				ctx.status(404);
				ctx.result("The user you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
	}
	
	public static void getRequestsByApprover(Context ctx) {
		String approverId = ctx.pathParam("id");
		
		try {
			int id = Integer.parseInt(approverId);
			Employee approver = empServ.getEmployeeById(id);
			
			if(approver != null) {
				ctx.json(reqServ.getPendingReimbursements(approver));
			} else {
				ctx.status(404);
				ctx.result("No pending requests");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
	}
	
	public static void approveRequest(Context ctx) {
		Map<String,String> approve = ctx.bodyAsClass(Map.class);
		String requestId = approve.get("requestId");
		try {
			int id = Integer.parseInt(requestId);
			Reimbursement request  = reqServ.getRequestByID(id);
			boolean approved = reqServ.approveRequest(request);
			
			if(approved) {
				ctx.status(HttpCode.ACCEPTED);
				ctx.json(approved);
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Request ID must be an integer.");
		}
	}
	
	public static void rejectRequest(Context ctx) {
		Map<String,String> approve = ctx.bodyAsClass(Map.class);
		String requestId = approve.get("requestId");
		String com = approve.get("comment");
		String empId = String.valueOf(approve.get("id"));
		
		Comment comment = new Comment();
		comment.setApprover(empServ.getEmployeeById(Integer.parseInt(empId)));
		comment.setCommentText(com);
		
		try {
			int id = Integer.parseInt(requestId);
			Reimbursement request = reqServ.getRequestByID(id);
			boolean approved = reqServ.rejectRequest(request, comment);
			if(approved) {
				ctx.status(HttpCode.ACCEPTED);
				ctx.json(approved);
			}
			
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Request ID must be an integer.");
		}
	}
	
	public static void getOptions(Context ctx) {
		Map<String, Set<Object>> options = empServ.getRequestOptions();
		if(options != null) {
			ctx.status(200);
			ctx.json(options);
		} else {
			ctx.status(404);
		}
	}
	
}
