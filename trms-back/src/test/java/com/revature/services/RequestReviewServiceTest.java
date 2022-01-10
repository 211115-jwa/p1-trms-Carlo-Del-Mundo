package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.postgresql.core.CommandCompleteParser;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;

@ExtendWith(MockitoExtension.class)
public class RequestReviewServiceTest {
	@Mock
	private ReimbursementDAO reimbursementDao; 
	
	@Mock
	private StatusDAO statusDao;
	
	@Mock
	private CommentDAO commentDao;
	
	@InjectMocks
	private RequestReviewService revServ = new RequestReviewServiceImpl();
	
	private static Set<Reimbursement> mockRequests;
	
	@BeforeAll
	public static void setUpMocks() {
		mockRequests = new HashSet<>();
		
		for(int i=0;i<5;i++) {
			Reimbursement mockRequest = new Reimbursement(i, new Employee(), 
										LocalDate.now().toString(),LocalTime.now().toString(),
										"test"+i,"test"+i, i*.65, new GradingFormat(), 
										new EventType(), new Status(), LocalDateTime.now());
			mockRequests.add(mockRequest);
		}
	}
	
	@Test
	public void getPendingReimbursementsTest() {
		Employee mockEmployee = new Employee();

		when(reimbursementDao.getPendingByApprover(mockEmployee)).thenReturn(mockRequests);
		Set<Reimbursement> actual = revServ.getPendingReimbursements(mockEmployee);
		
		assertEquals(mockRequests, actual);
	}
	
	@Test
	public void approveRequestTest() {
		Reimbursement mockRequest = new Reimbursement();
				
		when(reimbursementDao.update(mockRequest)).thenReturn(true);
		boolean actualUpdated = revServ.approveRequest(mockRequest);
		
		assertTrue(actualUpdated);
	}
	
	@Test
	public void rejectRequestTest() {
		Reimbursement mockRequest = new Reimbursement();
		Status mockStatus = new Status();
		
		when(statusDao.getById(3)).thenReturn(mockStatus);
		when(reimbursementDao.update(mockRequest)).thenReturn(true);
		
		mockRequest.setStatus(mockStatus);
		boolean actualReject = revServ.rejectRequest(mockRequest);
		
		assertTrue(actualReject);
	}
	
	@Test
	public void rejectRequestWithCommentTest() {
		Reimbursement mockRequest = new Reimbursement();
		Comment mockComment = new Comment();
		Status mockStatus = new Status();
		
		when(statusDao.getById(3)).thenReturn(mockStatus);
		when(commentDao.create(mockComment)).thenReturn(8);
		when(reimbursementDao.update(mockRequest)).thenReturn(true);
		
		mockRequest.setStatus(mockStatus);
		boolean actualReject = revServ.rejectRequest(mockRequest, mockComment);
		
		assertTrue(actualReject);		
	}
	
}
