package com.revature.data;

//import com.revature.utils.DAOFactory;

import org.junit.jupiter.api.Test;

import com.revature.beans.Reimbursement;
import com.revature.data.postgres.ReimbursementPostgres;
import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementDAOTest {
	private ReimbursementDAO reimDao = new ReimbursementPostgres();
	
	
	@Test
	public void createTest() {
		Reimbursement reim = new Reimbursement();
		int generatedId = reimDao.create(reim);
		assertNotEquals(0,generatedId);
	}
}
