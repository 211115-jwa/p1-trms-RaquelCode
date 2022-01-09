package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class StatusDAOTest {

	private static Logger log = LogManager.getLogger(ReimbursementDAOTest.class);
	
	private static StatusDAO statDAO = DAOFactory.getStatusDAO();
	private static String name = "Department Approved";
	
	
	@Test
	public void testGetById() {
		Status s = statDAO.getById(2);// existing id
		//log.info(s);
		assertNotNull(s);
	}//PosTest 1
	
	@Test
	public void testGetByIdInvalidId() {
		Status s = statDAO.getById(200);//non existing id
		log.info(s);
		assertNotEquals(200,s.getStatusId());
	}// NegTest for 1
	
	@Test
	public void testGetAll() {
		Set<Status> status = statDAO.getAll();
		//log.info(status);
		assertNotNull(status);
	}//PosTest for 2
	
	@Test
	public void testGetByName() {
		Set<Status> statusName = statDAO.getByName(name);
		//log.info(statusName);
		assertNotEquals(null, statusName);
	}// PosTest for 3
	
	@Test
	public void testGetByNameInvalidName() {
		Set<Status> statusName = statDAO.getByName(name + "xxx");
		//log.info(statusName);
		assertTrue(statusName.isEmpty());
	}//NegTest for 3
	
}
