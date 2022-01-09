package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class CommentDAOTest {

	private static Logger log = LogManager.getLogger(ReimbursementDAOTest.class);
	
	private static CommentDAO comDAO = DAOFactory.getCommentDAO();
	private static Comment mockCom;
	private static Reimbursement mockRem;
	private static Employee mockEmp;
	private static int genId;
	private static EventType et;
	private static Status status;
	private static GradingFormat gf;
	private static Role role;
	private static Employee sup;
	private static Department dept;
	

	@BeforeAll
	public static void mockCommentSetup() {
		mockCom = new Comment();
		mockEmp.setEmpId(10);
		mockCom.setRequest(mockRem);
		mockCom.setApprover(mockEmp);
		mockCom.setCommentText("Yada, Yada, Yada.. blah, blash");
		mockCom.setSentAt(LocalDateTime.now());
	}
	
	@BeforeAll
	public static void mockReimbursementSetup() {// mock req
		mockRem = new Reimbursement();
		mockRem.setRequestor(mockEmp);
		mockRem.setEventDate(LocalDate.of(2000,01,01));
		mockRem.setEventTime(LocalTime.of(11,11,11));
		mockRem.setLocation("location string");
		mockRem.setDescription("test description for mock");
		mockRem.setCost(1);
		mockRem.setGradingFormat(gf);
		mockRem.setEventType(et);
		mockRem.setStatus(status);
		mockRem.setSubmittedAt(LocalDateTime.now());
	}
	@BeforeAll
	public static void mockEventTypeSetup() {
		et = new EventType();
		et.setEventId(1);
		et.setName("Other");
		et.setPercentCovered(30.0);
	}
	@BeforeAll
	public static void mockStatusSetup() {
		status = new Status();
		status.setStatusId(2);
		status.setName("Pending Approval");
		status.setApprover("Direct Supervisor");
	}	
	@BeforeAll
	public static void mockGradingFormatSetup() {
		gf = new GradingFormat();
		gf.setFormatId(1);
		gf.setName("Awarded");
		gf.setExample("A");
	}
	
		
	@BeforeAll
	public static void mockEmployeeSetup() {// mock emp
		mockEmp = new Employee();
		mockEmp.setFirstName("first");
		mockEmp.setLastName("last");
		mockEmp.setUsername("Huffy");
		mockEmp.setPassword("xxl password");
		mockEmp.setRole(role);
		mockEmp.setFunds(1000);
		mockEmp.setSupervisor(sup);
		mockEmp.setDepartment(dept);
	}	
	@BeforeAll
	public static void mockRoleSetup() {// mock
		role = new Role();
		role.setRoleId(19);
		role.setName("fired");
	}
	
	
	
	
	@Disabled("Not Needed, Tests good, but will continue creating")	
	@Test	//  no delete DAO at the moment
	public void testCreate() {// 1
		
		genId = comDAO.create(mockCom);
		assertNotEquals(0, genId);
	}
	
	@Test
	public void testGetById() {
		Comment c = comDAO.getById(1);// existing id
		log.info(c);
		assertNotNull(c);
	}//PosTest 2
	
	@Test
	public void testGetByIdInvalidId() {
		Comment c = comDAO.getById(200);//non existing id
		log.info(c);
		assertNull(c);
	}// NegTest for 2
	
	@Test
	public void testGetAll() {
		Set<Comment> c = comDAO.getAll();
		//log.info(c);
		assertTrue(!c.isEmpty());
	}//PosTest for 3
	
	@Test
	public void testGetByRequestId() {
		Set<Comment> comReq = comDAO.getByRequestId(1);
		//log.info(comReq);
		assertNotEquals(null, comReq);
	}// PosTest for 4
	
	@Test
	public void testGetByRequestIdInvalidId() {
		Set<Comment> comReq = comDAO.getByRequestId(200);
		//log.info(comReq);
		assertTrue(comReq.isEmpty());
	}//NegTest for 4
	
}

