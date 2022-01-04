package com.revature.beans;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
	private int commentId;
	private Reimbursement request;
	private Employee approver;
	private String commentText;
	private LocalDateTime sentAt;
	
	public Comment() {
		commentId = 0;
		request=null;
		approver=null;
		commentText="";
		sentAt=LocalDateTime.now();
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Reimbursement getRequest() {
		return request;
	}

	public void setRequest(Reimbursement request) {
		this.request = request;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(approver, commentId, commentText, request, sentAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(approver, other.approver) && commentId == other.commentId
				&& Objects.equals(commentText, other.commentText) && Objects.equals(request, other.request)
				&& Objects.equals(sentAt, other.sentAt);
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", request=" + request + ", approver=" + approver + ", commentText="
				+ commentText + ", sentAt=" + sentAt + "]";
	}
}
