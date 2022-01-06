package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private EventTypeDAO eventTypeDao;
	
	@Mock
	private GradingFormatDAO gradingFormatDao;
	
	@Mock
	private StatusDAO statusDao;
	
	@Mock
	private ReimbursementDAO reqDao;
	
	@Mock
	private CommentDAO commentDao;
	
	@Mock
	private EmployeeDAO empDao;
	
	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	
	private static Set<Object> mockEventTypes;
	private static Set<Object> mockGradingFormat;
	private static Set<Reimbursement> mockRequests;
	private static Set<Comment> mockComments;
	private static Map<String, Set<Object>> mockRequestOptions;
	
	@BeforeAll									// Sets of mocks
	public static void setUpMocks() {
		mockEventTypes = new HashSet<>();
		mockGradingFormat = new HashSet<>();
		mockRequests = new HashSet<>();
		mockComments = new HashSet<>();
		mockRequestOptions = new HashMap<>();
		
		for(int i=0;i<5;i++) {
			EventType mockEventType = new EventType(i, "test"+i, i);
			GradingFormat mockGF = new GradingFormat(i, "test"+i, "test"+i);
			Reimbursement mockRequest = new Reimbursement(i, new Employee(),LocalDate.now(),LocalTime.now(),
					"test"+i,"test"+i, i*.65, mockGF, mockEventType, new Status(), LocalDateTime.now());
			Comment mockComment = new Comment(i, mockRequest, new Employee(), "text"+i, LocalDateTime.now());
			
			mockEventTypes.add(mockEventType);
			mockGradingFormat.add(mockGF);
			mockRequests.add(mockRequest);
			mockComments.add(mockComment);	
		}
		mockRequestOptions.put("eventTypes", mockEventTypes);
		mockRequestOptions.put("gradingFormats", mockGradingFormat);
	}
	
	@Test
	public void getRequestOptionsTest() {
		Map<String, Set<Object>> actual;
		
		when(eventTypeDao.getAll()).thenReturn(mockEventTypes);
		when(gradingFormatDao.getAll()).thenReturn(mockGradingFormat);
		actual = empServ.getRequestOptions();
		
		assertEquals(mockRequestOptions, actual);
	}
	
	@Test
	public void submitReimbursementReuestTest() {
		Status mockInitialStatus = new Status();
		Reimbursement mockRequest = new Reimbursement();
		
//		when(statusDao.getById(1)).thenReturn(mockInitialStatus);
		when(reqDao.create(mockRequest)).thenReturn(8);
		
		mockRequest.setStatus(mockInitialStatus);
		int actual = reqDao.create(mockRequest);
		
		assertTrue(actual > 0);
	}
	
	@Test
	public void getReimbursementRequestsTest() {
		Employee mockRequestor = new Employee();
		
		when(reqDao.getByRequestor(mockRequestor)).thenReturn(mockRequests);
		Set<Reimbursement> actual = empServ.getReimbursementRequests(mockRequestor);
		
		assertEquals(mockRequests, actual);
	}
	
	@Test
	public void getCommentsTest() {
		Reimbursement mockRequest = new Reimbursement();
		Employee mockApprover = new Employee();
		
		when(commentDao.getByRequestId(mockRequest.getReqId())).thenReturn(mockComments);
		when(empDao.getById(mockApprover.getEmpId())).thenReturn(mockApprover);
		Set<Comment> actual = empServ.getComments(mockRequest);
		
		assertTrue(actual.size() > 0);
	}
	
	@Test
	public void addCommentTest() {
		Comment mockComment = new Comment();
		when(commentDao.create(mockComment)).thenReturn(8);
		int actual = empServ.addComment(mockComment);
		
		assertEquals(8, actual);
	}
	
	@Test
	public void getEmployeeByIdTest() {
		Employee mockEmployee = new Employee();
		
		when(empDao.getById(8)).thenReturn(mockEmployee);
		Employee actual = empServ.getEmployeeById(8);
		
		assertEquals(mockEmployee, actual);
	}
	
	@Test
	public void getEmployeeByIdDoesNotExistTest() {
		when(empDao.getById(0)).thenReturn(null);
		Employee actual = empServ.getEmployeeById(0);
		
		assertNull(actual);
	}
	
}
