package com.coach.model;

import java.sql.Timestamp;

/**
 * ClientAppkey entity. @author MyEclipse Persistence Tools
 */

public class CoachNewsHistory extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Integer coachId;
	private Integer type;
	private Timestamp lastViewTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getLastViewTime() {
		return lastViewTime;
	}
	public void setLastViewTime(Timestamp lastViewTime) {
		this.lastViewTime = lastViewTime;
	}

}