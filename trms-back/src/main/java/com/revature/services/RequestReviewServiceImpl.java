package com.revature.services;

import java.time.LocalDateTime;
//import java.util.HashSet;
import java.util.Set;
//import java.util.stream.Collectors;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
//import com.revature.beans.Status;
import com.revature.data.CommentDAO;
//import com.revature.data.EmployeeDAO;
//import com.revature.data.EventTypeDAO;
//import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {

	
	private StatusDAO statusDao = DAOFactory.getStatusDAO();
	private ReimbursementDAO pendingDao = DAOFactory.getReimbursementDAO();
	private CommentDAO commentDao = DAOFactory.getCommentDAO();
	//private EmployeeDAO empDao = DAOFactory.getEmployeeDAO();

	



	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> reimbursements = pendingDao.getByRequestor(approver);
		reimbursements.forEach(reimbursement -> {

			if (reimbursement.getEventType().equals(statusDao.getByName("Pending")))
				;
			reimbursement.setRequestor(approver);
		});

		return reimbursements;
	}

	
	
	@Override
	public void approveRequest(Reimbursement request) {
		if (pendingDao.getById(request.getReqId()) != null) {
			pendingDao.update(request);
			request = pendingDao.getById(request.getReqId());
		}

	}

	@Override
	public void rejectRequest(Reimbursement request) {
		if (pendingDao.getById(request.getReqId()) != null) {
			pendingDao.update(request);
			request = pendingDao.getById(request.getReqId());
		}

	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		if (pendingDao.getById(request.getReqId()) != null) {
			pendingDao.update(request);
			request = pendingDao.getById(request.getReqId());
			comment.setSentAt(LocalDateTime.now());
			commentDao.create(comment);
		}

	}

	
}
