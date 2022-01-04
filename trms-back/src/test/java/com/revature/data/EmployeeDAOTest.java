package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Role;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.data.EmployeeDAO;


public class EmployeeDAOTest {
	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee mockEmp = new Employee();
	private static Department dept = new Department();
	private static Employee sup = mockEmp;
	
	
	
	@Test
	public void createTest() {
		Employee emp = new Employee();
		int genId = empDAO.create(emp);
		assertNotEquals(0,genId);
	}
	
	
	@Test
	public void getByIdWhenIdExists() {
		int idInput = 45;
		Employee idOutput = empDAO.getById(idInput);
		assertEquals(45, idOutput.getEmpId());  
	}
	
	
	@Test
	public void getByIdWhenIdDoesNotExist() {
		int idInput = -1;
		assertNull(empDAO.getById(idInput));
	}
	
	@Test
	public void getUsernameWhenUserNameExists() {
		String user = "auphill5";
		Employee userOut = empDAO.getByUsername(user);
		assertEquals(user,userOut.getUsername());
	}
	
	@Test
	public void getUserNameWhenUserNameDoesNotExist() {
		String notUser = "alvegrdeeewww";
		assertNull(empDAO.getByUsername(notUser));
	}
	
	@Test
	public void updateEmployeepassword() {
		Employee toUpdate = empDAO.getById(40);
		toUpdate.setPassword("viaerovrh");
		empDAO.update(toUpdate);
		assertEquals("viaerovrh",empDAO.getById(40).getPassword());
	}
	
	@Test
	public void updateEmployeeNotFoundpassword() {
		Employee toUpdate1 = empDAO.getById(240);
		toUpdate1.setPassword("viaerovrh");
		empDAO.update(toUpdate1);
		assertNull(empDAO.getById(240).getPassword());
	}
	
}
