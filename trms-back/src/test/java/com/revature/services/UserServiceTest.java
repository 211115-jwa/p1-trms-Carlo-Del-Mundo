package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.exceptions.ExistingUsernameException;
import com.revature.exceptions.LoginCredentialsException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private EmployeeDAO empDao;
	
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
		
	
	@Test
	public void userRegistrationTest() throws ExistingUsernameException {
		Employee mockUser = new Employee();
		
		when(empDao.create(mockUser)).thenReturn(8);
		int mockID = userServ.register(mockUser);
		assertTrue(mockID > 0);
	}
	
	@Test
	public void registerExistingUsernameTest() throws ExistingUsernameException {
		Employee mockUser = new Employee();
		when(empDao.getByUsername(mockUser.getUsername())).thenReturn(mockUser);
		assertThrows(ExistingUsernameException.class, () -> {
			userServ.register(mockUser);
		});
	}
	
	@Test
	public void registrationFailedTest() throws ExistingUsernameException {
		Employee mockUser = new Employee();
		when(empDao.create(mockUser)).thenReturn(0);
		int generatedID = userServ.register(mockUser);
		assertFalse(generatedID>0);
	}
	
	@Test
	public void successfulUserUpdate() {
		Employee mockUser = new Employee();
		when(empDao.update(mockUser)).thenReturn(true);
		boolean updated = userServ.userUpdate(mockUser);
		assertTrue(updated);
	}
	
	@Test
	public void unsuccessfulUserUpdate() {
		Employee mockUser = new Employee();
		when(empDao.update(mockUser)).thenReturn(false);
		boolean updated = userServ.userUpdate(mockUser);
		assertFalse(updated);
	}
	
	@Test
	public void successfullyDeleteUser() {
		Employee mockUser = new Employee();
		when(empDao.delete(mockUser)).thenReturn(true);
		boolean deleted = userServ.deleteUser(mockUser);
		assertTrue(deleted);
	}
	
	public void unsuccessfullyDeleteUser() {
		Employee mockUser = new Employee();
		when(empDao.delete(mockUser)).thenReturn(false);
		boolean deleted = userServ.deleteUser(mockUser);
		assertFalse(deleted);
	}
	
	@Test
	public void loginSuccessfullyTest() throws LoginCredentialsException {
		String username = "smiley@email.com";
		String password = "iForgot";
		
		Employee mockUser = new Employee();
		mockUser.setUsername("smiley@email.com");
		mockUser.setPassword("iForgot");
		
		when(empDao.getByUsername(username)).thenReturn(mockUser);	
		Employee actualUser = userServ.logIn(username, password);
		assertEquals(mockUser, actualUser);
	}
	
	@Test
	public void IncorrectPasswordLoginTest() throws LoginCredentialsException {
		String username = "CorrectUsername";
		String password = "WrongPassword";
		
		Employee mockUser = new Employee();
		mockUser.setUsername(username);
		mockUser.setPassword("iForgot");
		
		when(empDao.getByUsername(username)).thenReturn(mockUser);
		assertThrows(LoginCredentialsException.class, () -> {
			userServ.logIn(username, password);
		});
	}
	
	@Test
	public void IncorrectUsernameLoginTest() throws LoginCredentialsException {
		String username = "WrongUsername";
		String password = "iForgot";
		
		when(empDao.getByUsername(username)).thenReturn(null);
		assertThrows(LoginCredentialsException.class, () -> {
			userServ.logIn(username, password);
		});
	}
}
