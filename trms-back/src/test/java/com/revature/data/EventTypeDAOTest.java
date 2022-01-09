package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.revature.beans.EventType;
import com.revature.utils.DAOFactory;

public class EventTypeDAOTest {

	private static Logger log = LogManager.getLogger(ReimbursementDAOTest.class);
	
	private static EventTypeDAO ETypeDAO = DAOFactory.getEventTypeDAO();
	private static String name = "Department Approved";
	
	
	@Test
	public void testGetById() {
		EventType e = ETypeDAO.getById(2);// existing id
		log.info(e);
		assertNotNull(e);
	}//PosTest 1
	
	@Test
	public void testGetByIdInvalidId() {
		EventType e = ETypeDAO.getById(200);//non existing id
		log.info(e);
		assertNull(e);
	}// NegTest for 1
	
	@Test
	public void testGetAll() {
		Set<Object> e = ETypeDAO.getAll();
		//log.info(status);
		assertNotNull(e);
	}//PosTest for 2
	
	@Test
	public void testGetByName() {
		Set<EventType> etName = ETypeDAO.getByName(name);
		//log.info(statusName);
		assertNotEquals(null, etName);
	}// PosTest for 3
	
	@Test
	public void testGetByNameInvalidName() {
		Set<EventType> etName = ETypeDAO.getByName(name + "xxx");
		//log.info(statusName);
		assertTrue(etName.isEmpty());
	}//NegTest for 3
	
}
