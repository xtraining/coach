package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lessonDetailResponse")
public class LessonDetailResponse extends LessonResponse implements Serializable {
	@XmlElement
	private String groundName;
	@XmlElement
	private Integer groundId;
	@XmlElement
	private Integer alertSwitch;
	@XmlElement
	private Integer memberNum;
	@XmlElement
	private String remarks;
	@XmlElement
	private String address;
	@XmlElement
	private String description;
	@XmlElement
	private Double latitude;
	@XmlElement
	private Double longitude;
	@XmlElement
	private Float hours;
	@XmlElement
	private String orgName;
	@XmlElement
	private List<MemberResponse> memberList;
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
	public Integer getAlertSwitch() {
		return alertSwitch;
	}
	public void setAlertSwitch(Integer alertSwitch) {
		this.alertSwitch = alertSwitch;
	}
	public Integer getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getHours() {
		return hours;
	}
	public void setHours(Float hours) {
		this.hours = hours;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public List<MemberResponse> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberResponse> memberList) {
		this.memberList = memberList;
	}

	
}
