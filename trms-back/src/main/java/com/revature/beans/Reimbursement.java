package com.revature.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Reimbursement {
	private int reqId;
	private Employee requestor;
	private LocalDate eventDate;
	private LocalTime eventTime;
	private String location;
	private String description;
	private double cost;
	private GradingFormat gradingFormat;
	private EventType eventType;
	private Status status;
	private LocalDateTime submittedAt;
	
	public Reimbursement() {
		reqId=0;
		requestor=null;
		eventDate=null;
		eventTime=null;
		location="";
		description="";
		cost=0.0;
		gradingFormat = new GradingFormat();
		eventType =  new EventType();
		status = new Status();
		submittedAt = LocalDateTime.now();
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public Employee getRequestor() {
		return requestor;
	}

	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, description, eventDate, eventTime, eventType, gradingFormat, location, reqId,
				requestor, status, submittedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost)
				&& Objects.equals(description, other.description) && Objects.equals(eventDate, other.eventDate)
				&& Objects.equals(eventTime, other.eventTime) && Objects.equals(eventType, other.eventType)
				&& Objects.equals(gradingFormat, other.gradingFormat) && Objects.equals(location, other.location)
				&& reqId == other.reqId && Objects.equals(requestor, other.requestor)
				&& Objects.equals(status, other.status) && Objects.equals(submittedAt, other.submittedAt);
	}

	@Override
	public String toString() {
		return "Reimbursement [reqId=" + reqId + ", requestor=" + requestor + ", eventDate=" + eventDate
				+ ", eventTime=" + eventTime + ", location=" + location + ", description=" + description + ", cost="
				+ cost + ", gradingFormat=" + gradingFormat + ", eventType=" + eventType + ", status=" + status
				+ ", submittedAt=" + submittedAt + "]";
	}
}
