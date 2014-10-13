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
	private Float hours;
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

	
}
