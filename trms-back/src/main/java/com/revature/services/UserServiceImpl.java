package com.revature.services;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.exceptions.ExistingUsernameException;
import com.revature.exceptions.LoginCredentialsException;
import com.revature.utils.DAOFactory;

public class UserServiceImpl implements UserService{
	private EmployeeDAO empDao = DAOFactory.getEmployeeDAO();

	@Override
	public int register(Employee newEmployee) throws ExistingUsernameException {
		// TODO Auto-generated method stub
		Employee user = empDao.getByUsername(newEmployee.getUsername());
		int generatedID = 0;
		
		if(user == null) {
			generatedID = empDao.create(newEmployee);
		} else {
			throw new ExistingUsernameException();
		}
		
		return generatedID;
	}

	@Override
	public Employee logIn(String username, String password) throws LoginCredentialsException {
		// TODO Auto-generated method stub
		Employee user = empDao.getByUsername(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new LoginCredentialsException();
		}
	}

	@Override
	public boolean userUpdate(Employee updateUser) {
		// TODO Auto-generated method stub
		return empDao.update(updateUser);
	}

	@Override
	public boolean deleteUser(Employee userToDelete) {
		// TODO Auto-generated method stub
		return empDao.delete(userToDelete);
	}

	@Override
	public Employee getUserById(int Id) {
		// TODO Auto-generated method stub
		return empDao.getById(Id);
	}

}
