package com.revature.utils;

import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.data.postgres.CommentPostgres;
import com.revature.data.postgres.DepartmentPostgres;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.data.postgres.EventTypePostgres;
import com.revature.data.postgres.GradingFormatPostgres;
import com.revature.data.postgres.ReimbursementPostgres;
import com.revature.data.postgres.StatusPostgres;

/**
 * Following the factory design pattern,
 * allows one to get an instance of each DAO
 * interface that exists in the application.
 * <p>
 * Offers one central location in case DAO
 * implementations must be swapped out, so
 * that <strong>no other code</strong> that uses the DAOs 
 * in the application must be changed.
 * 
 * @author Sierra Nicholes
 *
 */
public class DAOFactory {
	public static CommentDAO getCommentDAO() { return new CommentPostgres(); }
	public static DepartmentDAO getDepartmentDAO() { return new DepartmentPostgres(); }
	public static EmployeeDAO getEmployeeDAO() { return new EmployeePostgres(); }
	public static EventTypeDAO getEventTypeDAO() { return new EventTypePostgres(); }
	public static GradingFormatDAO getGradingFormatDAO() { return new GradingFormatPostgres(); }
	public static ReimbursementDAO getReimbursementDAO() { return new ReimbursementPostgres(); }
	public static StatusDAO getStatusDAO() { return new StatusPostgres(); }
}
