package com.revature.services;


import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

//import java.util.Collections;
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
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;

@ExtendWith(MockitoExtension.class)
public class RequestReviewServiceTest {
	@Mock
	private CommentDAO cmtDao;

	@Mock
	private EmployeeDAO empDao;

	@Mock
	private ReimbursementDAO reimDao;

	
	@InjectMocks
	private RequestReviewService reqReviewServ = new RequestReviewServiceImpl();
	
	

	@BeforeAll // creating Mock for Employee
	public static void mockingTheEmployee() {

		HashSet<Employee> emmplp = new HashSet <> ();

		for (int emp =1; emp<=4; emp++) {
			Employee employee = new Employee();
			employee.setEmpId(emp);
			employee.setFirstName("" + emp);
			employee.setLastName("" + emp);
			employee.setUsername("" + emp);
			employee.setPassword("" + emp);
			employee.setFunds(1000.0);
			if (emp<=3)
				employee.getSupervisor().setEmpId(4);
			else employee.getSupervisor().setEmpId(2);
			employee.getRole().setRoleId(emp);
			emmplp.add(employee);
		}
	}

	

	@BeforeAll // creating Mock for Department
	public static void mockingTheDepartment() {
		HashSet<Department> mockingDept = new HashSet <> ();
		Department depart = new Department();
		depart.setDeptId(1);
		depart.setDeptHeadId(24);
		depart.setName("Test Name");
		mockingDept.add(depart);
	}
	
	@BeforeAll // creating Mock for Reimbursement
	public static void mockchingTheReimbursement() {

		HashSet<Reimbursement> mockingReimb = new HashSet<> (); 

		   for (int reimb =1; reimb<=5; reimb++) { 
			
			Employee employee = new Employee();
			employee.setEmpId(reimb);

			Reimbursement reimbursement = new Reimbursement();

			reimbursement.setReqId(reimb); 
			reimbursement.setRequestor(employee);
			reimbursement.getStatus().setStatusId(reimb);
			mockingReimb.add(reimbursement);
		}
	}




	@BeforeAll // creating Mock for Comment
	public static void mockkingTheComment() {
		 HashSet<Comment> mockingComment = new HashSet <> ();

		       for (int i=1; i<=2; i++) {
			
			Comment comment = new Comment();
            comment.setCommentId(i);
			comment.setCommentText("A comment text");

			comment.setRequest(new Reimbursement());
			comment.getRequest().setReqId(i);

			comment.setApprover(new Employee());
			comment.getApprover().setEmpId(i);
			mockingComment.add(comment);
		}
		       }


	@Test
	public Set<Reimbursement> getPendReimbSuccessfully(){
		
		Employee empl = new Employee();
		
		Set<Reimbursement> requet = reqReviewServ.getPendingReimbursements(empl);
		
		when(empDao.getById(empl.getEmpId())).thenReturn(empl);

		return requet;
	}
	
	@Test
	
	public void approveReqSuccessfully() {
		Reimbursement requestReimb = new Reimbursement();
		Status statusRequest = new Status();
		statusRequest.setStatusId(3);
		requestReimb.setStatus(statusRequest);
		requestReimb.setReqId(1);

		when(reimDao.getById(requestReimb.getReqId())).thenReturn(requestReimb);
		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));

		reqReviewServ.rejectRequest(requestReimb);
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}

	

	@Test
	public void approveReqNotSuccessfull() {
		Reimbursement reimbs = new Reimbursement();
		reimbs .setReqId(1);

		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));
		when(reimDao.getById(1)).thenReturn(reimbs );

		reqReviewServ.rejectRequest(reimbs );
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}


	@Test
	public void rejectReqNotSuccessfull() {
		Reimbursement reimb = new Reimbursement();
		reimb.setReqId(1);

		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));
		when(reimDao.getById(1)).thenReturn(reimb);

		reqReviewServ.rejectRequest(reimb);
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}



	@Test
	public void rejectReqSuccessfully() {
	
		Reimbursement requestReimb = new Reimbursement();
		Status statusRequest = new Status();
		statusRequest.setStatusId(3);
		requestReimb.setStatus(statusRequest);
		requestReimb.setReqId(1);

		when(reimDao.getById(requestReimb.getReqId())).thenReturn(requestReimb);
		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));

		reqReviewServ.rejectRequest(requestReimb);
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}


	@Test
	public void rejectReqCommentSuccessfully() {
		Reimbursement requestReimb = new Reimbursement();
		Status st = new Status();
		st.setStatusId(3);
		requestReimb.setStatus(st);
		requestReimb.setReqId(1);

		Comment comm = new Comment();
		comm.setCommentId(1);

		when(reimDao.getById(requestReimb.getReqId())).thenReturn(requestReimb);
		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));

		reqReviewServ.rejectRequest(requestReimb, comm);
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}

	@Test
	public void rejectReqCommentNotSuccessfull() {
		Comment commt = new Comment();
		commt.setCommentId(1);

		Reimbursement reimb = new Reimbursement();
		reimb.setReqId(1);

		doNothing().when(reimDao).update(Mockito.any(Reimbursement.class));
		when(reimDao.getById(1)).thenReturn(reimb);

		reqReviewServ.rejectRequest(reimb, commt);
		verify(reimDao).update(Mockito.any(Reimbursement.class));
	}

	

}
