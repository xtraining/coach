package com.coach.model;

import java.sql.Timestamp;

/**
 * CourseNews entity. @author MyEclipse Persistence Tools
 */

public class CourseNews extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long courseId;
	private Timestamp createTime;
	private Timestamp dealTime;

	// Constructors

	/** default constructor */
	public CourseNews() {
	}

	/** minimal constructor */
	public CourseNews(Long courseId, Timestamp createTime) {
		this.courseId = courseId;
		this.createTime = createTime;
	}

	/** full constructor */
	public CourseNews(Long courseId, Timestamp createTime, Timestamp dealTime) {
		this.courseId = courseId;
		this.createTime = createTime;
		this.dealTime = dealTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

}