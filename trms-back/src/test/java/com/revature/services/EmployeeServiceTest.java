package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private CommentDAO commentDao;
	@Mock
	private EmployeeDAO empDao;
	@Mock
	private ReimbursementDAO reqDao;
	
	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	
	private static Set<Comment> mockAvailableComments;
	private static Set<Reimbursement> mockAvailableRequests;
	
	@BeforeAll
	public static void mockAvailableCommentsSetup() {
		mockAvailableComments = new HashSet<>();
		Employee emp = new Employee();
		emp.setEmpId(86);
		Reimbursement reim = new Reimbursement();
		reim.setReqId(26);
		for (int i=1; i<=5; i++) {
			Comment comment = new Comment();
			comment.setCommentId(i);
			comment.setApprover(emp);
			comment.setRequest(reim);
			mockAvailableComments.add(comment);
		}
		
	}
	
	@BeforeAll
	public static void mockAvailableRequestsSetup() {
		mockAvailableRequests = new HashSet<>();
		Employee emp = new Employee();
		GradingFormat gf = new GradingFormat();
		EventType et = new EventType();
		Status state = new Status();
		emp.setEmpId(87);
		
		for (int i=1; i<=5; i++) {
			Reimbursement reim = new Reimbursement();
			reim.setRequestor(emp);
			reim.setGradingFormat(gf);
			reim.setEventType(et);
			reim.setStatus(state);
			mockAvailableRequests.add(reim);
		}
		
	}
	
	@Test
	public void addNewCommentSuccessfully() {
		Comment comment = new Comment();
		when(commentDao.create(comment)).thenReturn(10);
		int newComment = empServ.addComment(comment);
		assertNotEquals(0,newComment);
	}
	
	@Test
	public void getEmployeeByIdSuccessfully() {
		Employee empl = new Employee();
		empl.setEmpId(17);
		
		when(empDao.getById(17)).thenReturn(empl);
		Employee actualEmp = empServ.getEmployeeById(17);
		
		assertEquals(empl,actualEmp);
	}
	
	@Test
	public void getsubmitReimbursementRequestsuccessfully() {
		Reimbursement reim = new Reimbursement();
		when(reqDao.create(reim)).thenReturn(10);
		int newReim = empServ.submitReimbursementRequest(reim);
		assertNotEquals(0,newReim);
	}
	
	@Test
	public void getEmployeeCommentsSuccessfully() {
		Reimbursement reim = new Reimbursement();
		reim.setReqId(26);
		when(commentDao.getByRequestId(reim.getReqId())).thenReturn(mockAvailableComments);
		Set<Comment> actualComments = empServ.getComments(reim);
		assertEquals(mockAvailableComments,actualComments);
	}
	
	@Test
	public void getReimbursementRequestsSuccessfully() {
		Employee requestor = new Employee();
		requestor.setEmpId(87);
		when(reqDao.getByRequestor(requestor)).thenReturn(mockAvailableRequests);
		Set<Reimbursement> actualReimbursements = empServ.getReimbursementRequests(requestor);
		assertEquals(mockAvailableRequests,actualReimbursements);
	}
	
}
