package com.revature.services;

import com.revature.beans.Employee;

public interface UserService {
	/**
	 * Returns an Employee if the registration is successful. The create returns
	 * something greater then 0. else returns null
	 * @param newEmp the new employee that will be created
	 * @return the new employee set to the return of the create else null 
	 */	
	public Employee register(Employee newUser);
	/**
	 * Returns an employee that matches the params passed of String username 
	 * then checks for password match 
	 * @param username and password for employee login
	 * @returns employee on match of user/pass
	 */
		
		public Employee logIn(String username, String password);
		
		
		public Employee getUserById(int id);
		
		
		public Employee updateUser(Employee empToUpdate);
		
	
}
