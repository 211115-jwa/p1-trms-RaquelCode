package com.revature.controllers;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.services.RequestReviewService;
import com.revature.services.RequestReviewServiceImpl;
import com.revature.utils.DAOFactory;

import io.javalin.http.Context;

public class ReviewsController {

	private static Logger log = LogManager.getLogger(ReviewsController.class);
	private static RequestReviewService revServ = new RequestReviewServiceImpl();
	private static Set<Reimbursement> rems = new HashSet<>();
	private static EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	private static ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();

	
	
	public static void getByApprover(Context ctx) {
		String approverId = ctx.pathParam("id");
		log.info("getting requests for approver:: " + approverId);

		try {

			Employee approver = empDAO.getById(Integer.valueOf(approverId));
			rems = revServ.getPendingReimbursements(approver);
			// log.info("employee: " + approver);
			// log.info(rems);
			if (rems != null) {
				ctx.json(rems);
			} else {
				ctx.status(404);
				ctx.result("The approver you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Approver ID must be an integer. Please try again.");
		}

	}

	public static void ApproveRequest(Context ctx) {
		String reimId = ctx.pathParam("id");
		
		try {
			int reqid = Integer.valueOf(reimId);
		revServ.approveRequest(remDAO.getById(reqid));
		}catch(NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
		
	}

	public static void RejectRequest(Context ctx) {
		String reimId = ctx.pathParam("id");
		int reqid = Integer.valueOf(reimId);
		try {

			revServ.rejectRequest(remDAO.getById(reqid));
			}catch(NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
		
	}
}
