package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;

import com.revature.data.postgres.EmployeePostgres;

public interface EmployeeDAOTest {
	EmployeeDAO emplDao = new EmployeePostgres();

	@Test
	public static void createEmplyeeTest() {
		
		Employee createTest = new Employee();
		
		assertNotEquals(0, emplDao.create(createTest));
		
	}

	@Test
	public static void testEmplyeeNotValidId() {
		Employee emplOutput= emplDao.getById(10000);
		assertNull(emplOutput);
		
	}
	
	@Test
	public static void testEmplyeeUpdate() {
		Employee emplUpdate = emplDao.getById(1);
		emplUpdate.setFirstName("Boss");
		emplDao.update(emplUpdate);
		assertEquals("Boss",emplDao.getById(1).getFirstName());	
		
	}
	
	@Test
	public static void getValidEmpleeById()
	{
		String emplUsername = "Rql@2424";
		Employee actual = emplDao.getById(1);
		assertEquals(emplUsername, actual.getUsername());
		
	}
	
	@Test
	public static void getByEmplyeeUsernameWhenUsernameExists() {
		
		String emplUsernameInput = "Rql@2022";
		
		Employee emplOutput = emplDao.getByUsername(emplUsernameInput);
		
		assertEquals("Rql@2022", emplOutput.getUsername());
		
	}
	
	@Test
	public static void getByEmpllyeeUsernameWhenUsernameDoesNotExist() {
		String emplUsernameInput = "Rql@2021";
		Employee emplOutput = emplDao.getByUsername(emplUsernameInput);
		assertNull(emplOutput); 
		
	}
	
	@Test
	public static void getAll() {
		Set<Employee> givenOutput = emplDao.getAll();
		assertNotNull(givenOutput);
		
	}

}
