package com.coach.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coach.response.CourseDetailResponse;
import com.coach.response.CourseResponse;
import com.coach.response.OrgCourseResponse;
import com.coach.utils.DateUtils;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class CoachRejectCourse extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String address;
	private String description;
	private Integer groundId;
	private String groundName;
	private Timestamp startTime;
	private Timestamp endTime;
	private Date expiryDate;
	private Integer cooachCourseStatus;
	private String recycleDay;
	private Float  courseHour;
	private Float  lessonHour;
	private Integer lessonNum;
	private Long courseId;
	private Long coachId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getGroundId() {
		return groundId;
	}
	public void setGroundId(Integer groundId) {
		this.groundId = groundId;
	}
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getCooachCourseStatus() {
		return cooachCourseStatus;
	}
	public void setCooachCourseStatus(Integer cooachCourseStatus) {
		this.cooachCourseStatus = cooachCourseStatus;
	}
	public String getRecycleDay() {
		return recycleDay;
	}
	public void setRecycleDay(String recycleDay) {
		this.recycleDay = recycleDay;
	}
	public Float getCourseHour() {
		return courseHour;
	}
	public void setCourseHour(Float courseHour) {
		this.courseHour = courseHour;
	}
	public Float getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(Float lessonHour) {
		this.lessonHour = lessonHour;
	}
	public Integer getLessonNum() {
		return lessonNum;
	}
	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public CourseDetailResponse toDetailResponse() {
		CourseDetailResponse c = new CourseDetailResponse();
		c.setCourseId(id);
		c.setCourseName(name);
		c.setStatus(cooachCourseStatus);
		c.setAddress(address);
		c.setCourseHour(courseHour);
		c.setGroundName(groundName);
		c.setGroundId(groundId);
		c.setLessonHour(lessonHour);
		c.setRecycleDay(recycleDay);
		c.setStartTime(DateUtils.dateToyyyyMMddHHmiss(startTime));
		return c;
	}

}