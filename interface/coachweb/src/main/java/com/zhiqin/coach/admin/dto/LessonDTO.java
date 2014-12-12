package com.zhiqin.coach.admin.dto;

import java.sql.Timestamp;

/**
 * Period entity. @author MyEclipse Persistence Tools
 */

public class LessonDTO {

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
	private Double hours;
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
	public LessonDTO() {
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


	public Double getHours() {
		return this.hours;
	}

	public void setHours(Double hours) {
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

}