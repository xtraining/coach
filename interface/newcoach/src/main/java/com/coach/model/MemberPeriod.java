package com.coach.model;

import java.sql.Timestamp;

/**
 * MemberPeriod entity. @author MyEclipse Persistence Tools
 */

public class MemberPeriod extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long periodId;
	private Long memberId;
	private Timestamp createTime;
	private Integer status;

	// Constructors

	/** default constructor */
	public MemberPeriod() {
	}

	/** full constructor */
	public MemberPeriod(Long periodId, Long memberId, Timestamp createTime,
			Integer status) {
		this.periodId = periodId;
		this.memberId = memberId;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPeriodId() {
		return this.periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}