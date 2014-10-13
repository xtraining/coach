package com.coach.response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "chiefLessonResponse")
public class CourseMemberResponse implements Serializable {
	@XmlElement
	private Long courseId;
	@XmlElement
	private Integer courseType;
	@XmlElement
	private String name;
	@XmlElement
	private Integer memberNum;
//	@XmlElement
//	private List<MemberDetailResponse> memberList = new ArrayList<MemberDetailResponse>();
	@XmlElement
	private String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}
//	public List<MemberDetailResponse> getMemberList() {
//		return memberList;
//	}
//	public void setMemberList(List<MemberDetailResponse> memberList) {
//		this.memberList = memberList;
//	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}


	
}
