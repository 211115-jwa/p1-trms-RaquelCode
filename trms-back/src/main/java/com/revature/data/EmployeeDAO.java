package com.revature.data;

import com.revature.beans.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {
	public Employee getByUsername(String username);
}
