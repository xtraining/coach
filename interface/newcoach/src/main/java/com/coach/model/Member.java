package com.coach.model;

import java.sql.Timestamp;

import com.coach.response.MemberResponse;
import com.coach.response.TeamResponse;

/**
 * SmsHistory entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Member extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long id;
	private Long coachId;
	private String phoneNumber;
	private String name;;
	private Timestamp createTime;
	private Integer gender;
	private Integer status;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
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
	public MemberResponse toResponse() {
		MemberResponse r = new MemberResponse();
		r.setMemberId(id);
		r.setMemberName(name);
		r.setPhoneNumber(phoneNumber);
		if(status != null && status != 0){
			r.setStatus(1);
		} else {
			r.setStatus(0);
		}
		return r;
	}

}