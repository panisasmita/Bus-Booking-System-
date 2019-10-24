package com.capgemini.busschedulingjpawithhibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback_details")
public class Feedback {
	@Id
	@Column
	private int userId;
	
	@Column
	private int busId;
	
	@Column
	private String feedback;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "Feedback [userId=" + userId + ", busId=" + busId + ", feedback=" + feedback + "]";
	}
	
}
