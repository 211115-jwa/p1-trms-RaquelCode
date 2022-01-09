package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.revature.beans.GradingFormat;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

@TestMethodOrder(OrderAnnotation.class)
public class GradingFormatDAOTest {

	private static Logger log = LogManager.getLogger(GradingFormatDAOTest.class);
	GradingFormatDAO gradDAO = DAOFactory.getGradingFormatDAO();
	private static GradingFormat gf;
	
	private static String name = "Presentation";
	
	@BeforeAll
	public static void mockGradingFormatSetup() {
		gf = new GradingFormat();
		gf.setFormatId(1);
		gf.setName("Awarded");
		gf.setExample("A");
	}
	
	
	@Test
	public void testGetById() {
		GradingFormat gf = gradDAO.getById(2);// existing id
		//log.info(gf);
		assertNotNull(gf);
		
	}//PosTest for 1
	
	@Test
	public void testGetByIdInvalidId() {
		gf = gradDAO.getById(200);//non existing id
		//log.info(gf);
		assertEquals(null, gf);	
	}// NegTest for 1
	
	@Test
	public void testGetAll() {
		Set<Object> gradFormat = gradDAO.getAll();
		//log.info(status);
		assertNotNull(gradFormat);
		
	}//PosTest for 2
	
	@Test
	public void testGetByName() {
		Set<GradingFormat> gfName = gradDAO.getByName(name);
		log.info(gfName);
		assertNotEquals(null, gfName);
		
	}//PosTest for 3
	
	@Test
	public void testGetByNameInvalid() {
		Set<GradingFormat> gfName = gradDAO.getByName(name + "xxx");
		//log.info(statusName);
		assertTrue(gfName.isEmpty());
		
	}//NegTest for 3
}
