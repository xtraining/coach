package com.coach.model;

import java.sql.Timestamp;

import com.coach.response.v1.TeamResponse;

/**
 * SmsHistory entity. @author MyEclipse Persistence Tools
 */

public class Team extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long coachId;
	private String name;
	private Integer status;
	private Timestamp createTime;
	private Integer memberNum;
	private Long appTeamId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public Long getAppTeamId() {
		return appTeamId;
	}
	public void setAppTeamId(Long appTeamId) {
		this.appTeamId = appTeamId;
	}
	public Integer getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}
	public TeamResponse toResponse() {
		TeamResponse r = new TeamResponse();
		r.setTeamId(id);
		r.setName(name);
		r.setMemberNum(memberNum);
		r.setStatus(status);
		return r;
	}

}