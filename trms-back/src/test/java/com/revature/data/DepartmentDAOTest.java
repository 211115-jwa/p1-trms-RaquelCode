package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.utils.DAOFactory;

public class DepartmentDAOTest {


	private static Logger log = LogManager.getLogger(Department.class);
	private static DepartmentDAO deptDAO = DAOFactory.getDepartmentDAO();
	private static String name = "Department Approved";
	
	
	/*
	 * @Test public void testGetById() { Department d = deptDAO.getById(2);//
	 * existing id log.info(d); assertNotNull(d); }//PosTest 1
	 */
	@Test
	public void testGetByIdInvalidId() {
		Department d = deptDAO.getById(200);//non existing id
		log.info(d);
		assertNull(d);
	}// NegTest for 1
	
	@Test
	public void testGetAll() {
		Set<Department> d = deptDAO.getAll();
		//log.info(status);
		assertNotNull(d);
	}//PosTest for 2
	
	@Test
	public void testGetByName() {
		Set<Department> deptName = deptDAO.getByName(name);
		//log.info(statusName);
		assertNotEquals(null, deptName);
	}// PosTest for 3
	
	@Test
	public void testGetByNameInvalidName() {
		Set<Department> deptName = deptDAO.getByName(name + "xxx");
		//log.info(statusName);
		assertTrue(deptName.isEmpty());
	}//NegTest for 3
	
}