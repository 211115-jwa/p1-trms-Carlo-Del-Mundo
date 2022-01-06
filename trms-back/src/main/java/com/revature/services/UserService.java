package com.revature.services;

import com.revature.beans.Employee;
import com.revature.exceptions.ExistingUsernameException;
import com.revature.exceptions.LoginCredentialsException;

public interface UserService {
	public int register(Employee newEmployee) throws ExistingUsernameException;
	public Employee logIn(String username, String password) throws LoginCredentialsException;
	public boolean userUpdate(Employee updateUser);
	public boolean deleteUser(Employee userDelete);
}
