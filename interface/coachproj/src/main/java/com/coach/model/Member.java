package com.coach.model;

import java.sql.Timestamp;

import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberResponse;
import com.coach.response.SearchMemberResponse;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */

public class Member extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String parentName;
	private String phoneNumber;
	private Timestamp createTime;
	private String code;
	private Integer status;
	private Long courseId;
	private String courseName;
	private Integer attendNum;
	private Integer courseTotalNum;
	private Timestamp date;
	private Integer memberNum;
	private Integer courseType;
	private Integer gender;
	private String description;
	private String groundName;
	private Timestamp startTime;
	public Long getId() {
		return this.id;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}

	public Integer getCourseTotalNum() {
		return courseTotalNum;
	}

	public void setCourseTotalNum(Integer courseTotalNum) {
		this.courseTotalNum = courseTotalNum;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
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

	public MemberResponse toResponse() {
		MemberResponse r = new MemberResponse();
		r.setMemberName(name);
		r.setPhoneNumber(phoneNumber);
		r.setMemberId(id);
		r.setStatus(status);
		return r;
	}
	
	public MemberDetailResponse toMemberDetailResponse() {
		MemberDetailResponse r = new MemberDetailResponse();
		r.setAttendNum(attendNum);
		r.setMemberId(id);
		r.setMemberName(name);
		r.setPhoneNumber(phoneNumber);
		r.setStatus(status);
		r.setTotalNum(courseTotalNum);
		r.setGender(gender);
		return r;
	}

	public SearchMemberResponse toSearhcMemberResponse() {
		SearchMemberResponse r = new SearchMemberResponse();
		r.setMemberName(name);
		r.setMemberId(id);
		r.setCourseId(getCourseId());
		r.setCourseName(courseName);
		return r;
	}

}