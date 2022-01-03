package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.*;

import com.revature.beans.Employee;
import com.revature.utils.DAOFactory;

public class EmployeeDAOTestImplementation implements EmployeeDAOTest {
	private EmployeeDAO empDao = DAOFactory.getEmployeeDAO(); 
//	private int generatedID = 0;
	
	@Test
	@Override
	public void employeeIsUpdated() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(3);
		actual.setUsername("testingUpdate@email.com");
		boolean isUpdated = this.empDao.update(actual);
		assertTrue(isUpdated);
	}

	@Test
	@Override
	public void getByIdNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(1);
		assertNotNull(actual);
	}
	
	@Test
	@Override
	public void getByIdNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(0);
		assertNull(actual);
	}

	@Test
	@Override
	public void getByUserNameNotNUll() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getByUsername("smiley@email.com");
		assertNotNull(actual);
	}

	@Test
	@Override
	public void getByUserNameNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getByUsername("notExistingUsername@email.com");
		assertNull(actual);
	}

	@Test
	@Override
	public void getAllNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Set<Employee> actual = this.empDao.getAll();
		assertTrue(actual.size()>0);
	}

	@Test
	@Override
	public void employeeRoleIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(2);
		assertNotNull(actual.getRole());
	}

	@Test
	@Override
	public void employeeRoleTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(2);
		assertEquals("Direct Supervisor",actual.getRole().getName());
	}

	@Test
	@Override
	public void employeeDepartmentIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(3);
		assertNotNull(actual);
	}

	@Test
	@Override
	public void employeeDepartmentTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(3);
		assertEquals("Legal", actual.getDepartment().getName());
	}

	@Test
	@Override
	public void employeeSupervisorIsNotNull() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(4);
		assertNotNull(actual);
	}

	@Test
	@Override
	public void employeeSupervisorTest() {
		// TODO Auto-generated method stub
//		fail("Not Implemented yet");
		Employee actual = this.empDao.getById(4);
		assertEquals("thesupervisor@email.com", actual.getSupervisor().getUsername());
		
	}	
}
