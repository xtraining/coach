package com.coach.model;

import java.sql.Timestamp;

import com.coach.response.v1.TeamResponse;

/**
 * SmsHistory entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TeamMember extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long id;
	private Long teamId;
	private Long memberId;
	private Timestamp createTime;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}