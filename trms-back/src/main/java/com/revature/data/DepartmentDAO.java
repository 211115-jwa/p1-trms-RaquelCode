package com.revature.data;

import java.util.Set;

import com.revature.beans.Department;

public interface DepartmentDAO extends GenericDAO<Department>{
	public Department getById(int id);
	public Set<Department> getAll();
	public Set<Department> getByName(String name);
}
