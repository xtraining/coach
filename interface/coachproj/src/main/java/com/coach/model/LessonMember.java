package com.coach.model;

import java.sql.Timestamp;

/**
 * CourseMember entity. @author MyEclipse Persistence Tools
 */

public class LessonMember extends AbstractBaseModel implements
		java.io.Serializable {

	private Long id;
	private Long lessonId;
	private Long memberId;
	private Integer status;
	private Timestamp createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getLessonId() {
		return lessonId;
	}
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	

}