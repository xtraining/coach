package com.coach.model;

import java.sql.Timestamp;

import com.coach.response.ConflictLessonResponse;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonResponse;
import com.coach.utils.DateUtils;

/**
 * Period entity. @author MyEclipse Persistence Tools
 */

public class Lesson extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Long id;
	private Long courseId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private Integer type;
	private String name;
	private String address;
	private String description;
	private Integer organizationId;
	private Float hours;
	private Integer alertSwitch;
	private Integer memberNum;
	private Integer groundId;
	private String groundName;
	private Double longitude;
	private Double latitude;
	// Constructors

	public Integer getAlertSwitch() {
		return alertSwitch;
	}


	public void setAlertSwitch(Integer alertSwitch) {
		this.alertSwitch = alertSwitch;
	}


	/** default constructor */
	public Lesson() {
	}


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
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

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Float getHours() {
		return this.hours;
	}

	public void setHours(Float hours) {
		this.hours = hours;
	}


	public Integer getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public LessonResponse toResponse() {
		LessonResponse r =  new LessonResponse();
		r.setLessonId(id);
		r.setName(getName());
		r.setStartTime(DateUtils.dateToyyyyMMdd(getStartTime()));
		r.setEndTime(DateUtils.dateToyyyyMMdd(getEndTime()));
		r.setType(getType());
		return r;
	}

	public LessonDetailResponse toDetailResponse() {
		LessonDetailResponse r =  new LessonDetailResponse();
		r.setLessonId(id);
		r.setName(getName());
		r.setStartTime(DateUtils.dateToyyyyMMddHHmiss(getStartTime()));
		r.setEndTime(DateUtils.dateToyyyyMMddHHmiss(getEndTime()));
		r.setType(getType());
		r.setRemarks(getDescription());
		r.setAlertSwitch(alertSwitch);
		r.setMemberNum(memberNum);
		r.setAddress(address);
		r.setGroundId(groundId);
		r.setGroundName(groundName);
		r.setHours(hours);
		return r;
	}


	public ConflictLessonResponse toConflictResponse() {
		ConflictLessonResponse r = new ConflictLessonResponse();
		r.setFlag(1);
		r.setCourseName(getName());
		r.setStartTime(DateUtils.dateToyyyyMMddHHmiss(getStartTime()));
		r.setEndTime(DateUtils.dateToyyyyMMddHHmiss(getEndTime()));
		return r;
	}

}