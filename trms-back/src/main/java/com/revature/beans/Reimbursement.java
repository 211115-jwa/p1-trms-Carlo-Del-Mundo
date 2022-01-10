package com.revature.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reimbursement {
	private int reqId;
	private Employee requestor;
	private String eventDate;
	private String eventTime;
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
		submittedAt = null;
	}
	
	public Reimbursement(int reqId, Employee requestor, String eventDate, String eventTime, String location, String description, double cost, GradingFormat gradingFormat, EventType eventType, Status status, LocalDateTime submittedAt) {
		this.reqId = reqId;
		this.requestor = requestor;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.eventType =  eventType;
		this.status = status;
		this.submittedAt = submittedAt;
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

	public String getEventDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eDAte = LocalDate.parse(this.eventDate,formatter);

		return eDAte.toString();
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = String.valueOf(eventDate);
	}

	public String getEventTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime eventTime = LocalTime.parse(this.eventTime,formatter);
		
		return eventTime.toString();
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = String.valueOf(eventTime);
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
