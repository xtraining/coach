package com.coach.model;

/**
 * CoachCourse entity. @author MyEclipse Persistence Tools
 */

public class CoachCourse extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long coachId;
	private Long courseId;
	private Integer status;
	

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

	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	private Integer flag;
	
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}