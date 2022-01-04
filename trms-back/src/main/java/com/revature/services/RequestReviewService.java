package com.revature.services;

import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;

public interface RequestReviewService {
	/**
	 * Returns the Set of reimbursement requests that are
	 * currently pending approval from the specified Employee.
	 * The method must account for all of the following scenarios:
	 * <ul>
	 * 	<li>Requests that are at the "direct supervisor" approval
	 * status and the specified employee is the requestor's direct
	 * supervisor</li>
	 * 	<li>Requests that are at the "department head" approval
	 * status and the specified employee is the head of that department</li>
	 * 	<li>Requests that are at the "benefits coordinator" approval
	 * status and the specified employee is in the human resources
	 * department</li>
	 * </ul>
	 * 
	 * @param approver the employee who must approve the returned requests
	 * @return the Set of requests awaiting the specified employee's approval
	 */
	public Set<Reimbursement> getPendingReimbursements(Employee approver);
	
	/**
	 * Sets the specified request to the next status. For example, if
	 * the status was "Pending" with an approver of "Direct Supervisor",
	 * then the method should set the specified request's status to
	 * "Pending" with an approver of "Department Head".
	 * <p>
	 * The method then proceeds to update this information in the database.
	 * 
	 * @param request the request to be approved
	 */
	public void approveRequest(Reimbursement request);
	
	/**
	 * Sets the status of the specified request to "Rejected", then
	 * updates this information in the database.
	 * 
	 * @param request the request to be rejected
	 */
	public void rejectRequest(Reimbursement request);
	
	/**
	 * Sets the status of the specified request to "Rejected", then
	 * updates this information in the database.
	 * <p>
	 * Also submits a comment including the reason that the request
	 * is being rejected.
	 * 
	 * @param request the request to be rejected
	 */
	public void rejectRequest(Reimbursement request, Comment comment);
}
