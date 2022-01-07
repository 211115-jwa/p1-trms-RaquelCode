package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

//import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.postgres.ReimbursementPostgres;

public interface ReimbursementDAOTest {
	ReimbursementDAO reimbDao = new ReimbursementPostgres();

	@Test
	public static void createReimbursementTest() {
		Reimbursement createReimbursement = new Reimbursement();
		assertNotEquals(0, reimbDao.create(createReimbursement));
		
	}

	@Test
	public static void getAll() {
		Set<Reimbursement> givenOutput = reimbDao.getAll();
		assertNotNull(givenOutput);
		
	}

	@Test
	public static void testReimbursementUpdate() {
		Reimbursement emplUpdate = reimbDao.getById(1);
		emplUpdate.setLocation("Seattle");
		reimbDao.update(emplUpdate);
		assertEquals("Seattle",reimbDao.getById(1).getLocation());	
		
	}

	@Test
	public static void testReimbursemnetNotValidId() {
		Reimbursement reimbOutput= reimbDao.getById(10000);
		assertNull(reimbOutput);
	}
	
	

}
