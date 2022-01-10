package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
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
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	//private static Logger log = LogManager.getLogger(UserServiceTest.class);
	
	@Mock
	private CommentDAO comDAO = DAOFactory.getCommentDAO();

	@Mock
	private DepartmentDAO depttDAO = DAOFactory.getDepartmentDAO();

	@Mock
	private EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();

	@Mock
	private EventTypeDAO etDAO = DAOFactory.getEventTypeDAO();

	@Mock
	private GradingFormatDAO gfDAO = DAOFactory.getGradingFormatDAO();

	@Mock
	private ReimbursementDAO reqDAO = DAOFactory.getReimbursementDAO();

	@Mock
	private StatusDAO statDAO = DAOFactory.getStatusDAO();

	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	@InjectMocks
	private UserService userServ = new UserServiceImpl();

	
	private static Set<Employee> mockEmps;
	private static Set<Reimbursement> mockReqs;
	private static Set<Comment> mockComs;
	//private static Set<Department> mockDepartments;
	Department mockDept = new Department(1, "Test Department", 24);

	
	@BeforeAll
	public static void mockCommentsSetup() {
		mockComs = new HashSet<>();

		for (int i = 1; i <= 24; i++) {
			Comment com = new Comment();
			com.setCommentId(i);
			for (int j = 1; i <= 8; i++) {
				com.setRequest(new Reimbursement());
				com.getRequest().setReqId(j);
				com.setApprover(new Employee());
				if (i < 3)
					com.getApprover().setEmpId(1);
				else if (i < 5)
					com.getApprover().setEmpId(23);
				else
					com.getApprover().setEmpId(24);
			}
			mockComs.add(com);
		}
	}

	@BeforeAll
	public static void mockRequestsSetup() {
		mockReqs = new HashSet<>();

		for (int i = 1; i <= 8; i++) {
			Reimbursement req = new Reimbursement();
			req.setReqId(i);
			req.getRequestor().setEmpId(i);
			req.getStatus().setStatusId(i);
			mockReqs.add(req);
		}
	}

	
	@BeforeAll
	public static void mockEmployeesSetup() {
		mockEmps = new HashSet<>();

		for (int i = 1; i <= 24; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("Test" + i);
			emp.setLastName("Test" + i);
			emp.setUsername("test" + i);
			emp.setPassword("pass");
			emp.setFunds(1000.00);
			emp.getSupervisor().setEmpId(24);
			if (i == 24)
				emp.getRole().setRoleId(3);
			else if (i == 23)
				emp.getRole().setRoleId(2);
			else
				emp.getRole().setRoleId(1);
			mockEmps.add(emp);
		}
	}
	
	@Test
	public void logInSuccessfully() {
		// input setup
		String username="qwertyuiop";
		String password="pass";
		
		// set up the mocking
		Employee mockEmp = new Employee();
		mockEmp.setUsername(username);
		mockEmp.setPassword(password);
		when(empDAO.getByUsername(username)).thenReturn(mockEmp);
		
		// call the method we're testing
		Employee emp = userServ.logIn(username, password);
		
		// assert the expected behavior/output
		assertEquals(mockEmp,emp);
	}
	
	@Test
	public void logInIncorrectPassword() {
		String username="qwertyuiop";
		String password="12345";
		
		Employee mockEmp = new Employee();
		mockEmp.setUsername(username);
		mockEmp.setPassword("pass");
		when(empDAO.getByUsername(username)).thenReturn(mockEmp);
		
		Employee emp = userServ.logIn(username, password);
		assertNull(emp);
	}
	
	@Test
	public void logInUsernameDoesNotExist() {
		String username="asdfghjkl";
		String password="pass";
		
		when(empDAO.getByUsername(username)).thenReturn(null);
		
		Employee actualPerson = userServ.logIn(username, password);
		assertNull(actualPerson);
	}
	
	@Test
	public void registerPersonSuccessfully() {
		Employee emp = new Employee();
		
		when(empDAO.create(emp)).thenReturn(10);
		
		Employee e = userServ.register(emp);
		assertEquals(10, e.getEmpId());
	}
	
	@Test
	public void registerPersonSomethingWrong() {
		Employee person = new Employee();
		when(empDAO.create(person)).thenReturn(0);
		Employee actualPerson = userServ.register(person);
		assertNull(actualPerson);
	}
	

	
	@Test
	public void updateSuccessfully() {
		Employee mockEmp = new Employee();
		mockEmp.setEmpId(1);
		
		doNothing().when(empDAO).update(Mockito.any(Employee.class));
		when(empDAO.getById(1)).thenReturn(mockEmp);
		
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setUsername("qwertyuiop");
		Employee updatedPerson = userServ.updateUser(emp);
		assertNotEquals(emp, updatedPerson);
	}
	
	@Test
	public void updateSomethingWrong() {
		Employee mockEmp = new Employee();
		mockEmp.setEmpId(1);
		
		doNothing().when(empDAO).update(Mockito.any(Employee.class));
		when(empDAO.getById(1)).thenReturn(mockEmp);
		
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setUsername("qwertyuiop");
		Employee updatedPerson = userServ.updateUser(emp);
		assertNotEquals(emp, updatedPerson);
	}
	
}