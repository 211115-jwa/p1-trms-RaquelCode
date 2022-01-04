package com.revature.beans;

import java.util.Objects;

public class Status {
	private int statusId;
	private String name;
	private String approver;
	
	public Status() {
		statusId=1;
		name="Pending Approval";
		approver="Direct Supervisor";
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	@Override
	public int hashCode() {
		return Objects.hash(approver, name, statusId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(approver, other.approver) && Objects.equals(name, other.name)
				&& statusId == other.statusId;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", name=" + name + ", approver=" + approver + "]";
	}
}
