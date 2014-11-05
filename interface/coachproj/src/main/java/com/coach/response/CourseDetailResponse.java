package com.coach.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "courseDetailResponse")
public class CourseDetailResponse extends CourseResponse {
	@XmlElement
	private String groundName;
	@XmlElement
	private Integer groundId;
	@XmlElement
	private String address;
	@XmlElement
	private String startTime;
	@XmlElement
	private String recycleDay;
	@XmlElement
	private Float courseHour;
	@XmlElement
	private Float lessonHour;
	@XmlElement
	private Long memberNum;
	@XmlElement
	private List<MemberResponse> memberList = new ArrayList<MemberResponse>();
	@XmlElement
	private String remarks;
	@XmlElement
	private String expiryDate;
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public Integer getGroundId() {
		return groundId;
	}
	public void setGroundId(Integer groundId) {
		this.groundId = groundId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
	public List<MemberResponse> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberResponse> memberList) {
		this.memberList = memberList;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
