package com.coach.model;

import java.sql.Timestamp;

/**
 * MemberNews entity. @author MyEclipse Persistence Tools
 */

public class MemberNews extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long coachId;
	private Long memberId;
	private Integer status;
	private Long courseId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public MemberNews() {
	}

	/** full constructor */
	public MemberNews(Long coachId, Long memberId, Integer status,
			Long courseId, Timestamp createTime) {
		this.coachId = coachId;
		this.memberId = memberId;
		this.status = status;
		this.courseId = courseId;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoachId() {
		return this.coachId;
	}

	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}