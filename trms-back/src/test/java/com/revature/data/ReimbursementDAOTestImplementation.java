package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.*;

import com.revature.beans.Reimbursement;
import com.revature.utils.DAOFactory;

public class ReimbursementDAOTestImplementation implements ReimbursementDAOTest {
	private ReimbursementDAO requestDao = DAOFactory.getReimbursementDAO();

	@Test
	@Override
	public void getRequestById() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(1);
		assertNotNull(actual);
	}

	@Test
	@Override
	public void getRequestByIdNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(0);
		assertNull(actual);
	}

	@Test
	@Override
	public void getAllRequest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Set<Reimbursement> actual = this.requestDao.getAll();
		assertTrue(actual.size()>0);
	}

	@Test
	@Override
	public void requestIsUpdated() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(1);
		LocalDate today = LocalDate.now();
		actual.setEventDate(today);
		boolean isUpdated = this.requestDao.update(actual);
		assertTrue(isUpdated);
	}

	@Test
	@Override
	public void requestGradingFormatIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(1);
		assertNotNull(actual.getGradingFormat());
	}

	@Test
	@Override
	public void requestGradingFormatTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		String actual = this.requestDao.getById(1).getGradingFormat().getName();
		String expected = "Percentage";
		assertEquals(expected, actual);
	}

	@Test
	@Override
	public void requestEventTypeIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(1);
		assertNotNull(actual);
	}

	@Test
	@Override
	public void requestEventTypeTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		String actual = this.requestDao.getById(1).getEventType().getName();
		String expected = "Home Office";
		assertEquals(expected, actual);
	}

	@Test
	@Override
	public void requestStatusIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Reimbursement actual = this.requestDao.getById(1);
		assertNotNull(actual);
	}

	@Test
	@Override
	public void requestStatusTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		String actual = this.requestDao.getById(1).getStatus().getName();
		String expected = "Requesting";
		assertEquals(expected, actual);
	}

}
