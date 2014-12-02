package com.coach.response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orgCourseResponse")
public class OrgCourseResponse implements Serializable {
	@XmlElement
	private Integer orgId;
	@XmlElement
	private String orgName = "";
	@XmlElement
	private Integer type;
	@XmlElement
	private Integer courseNum = 0;
	@XmlElement
	private List<CourseResponse> courseResponseList = new ArrayList<CourseResponse>();
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<CourseResponse> getCourseResponseList() {
		return courseResponseList;
	}
	public void setCourseResponseList(List<CourseResponse> courseResponseList) {
		this.courseResponseList = courseResponseList;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	
}
