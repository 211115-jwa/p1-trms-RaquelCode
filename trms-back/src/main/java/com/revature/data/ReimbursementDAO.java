package com.revature.data;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;

public interface ReimbursementDAO extends GenericDAO<Reimbursement> {
	public Set<Reimbursement> getByRequestor(Employee requestor);
	public Set<Reimbursement> getByStatus(Status status);
}
