package com.revature.beans;

import java.sql.ResultSet;
import java.util.Objects;

import com.revature.data.EmployeeDAO;
import com.revature.utils.DAOFactory;

public class Employee {
	private int empId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Role role;
	private double funds;
	private int supervisor_id;				// setter only
	private Employee supervisor;			//getter only
	private Department department;
	
	public Employee() {
		empId=0;
		firstName="First";
		lastName="Last";
		username="";
		password="";
//		role = new Role();
		role = null;
		funds = 1000.00;
//		supervisor = new Employee();
		supervisor = null;
//		department = new Department();
		department = null;
	}
	

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		if (funds<=0) this.funds = 0;
		else if (funds>=1000.0) this.funds=1000.0;
		else this.funds = funds;
	}

	public Employee getSupervisor() {
		EmployeeDAO empDao = DAOFactory.getEmployeeDAO();
		
		return empDao.getById(this.supervisor_id);
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisor_id = supervisorId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		return Objects.hash(department, empId, firstName, funds, lastName, password, role, supervisor, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department) && empId == other.empId
				&& Objects.equals(firstName, other.firstName)
				&& Double.doubleToLongBits(funds) == Double.doubleToLongBits(other.funds)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(supervisor, other.supervisor)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", role=" + role + ", funds=" + funds + ", supervisor="
				+ supervisor + ", department=" + department + "]";
	}
}
