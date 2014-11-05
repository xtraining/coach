package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "memberDetailResponse")
public class MemberDetailResponse extends MemberResponse implements Serializable {
	@XmlElement
	private Integer attendNum;
	@XmlElement
	private Integer totalNum;
	@XmlElement
	private Integer gender;
	@XmlElement
	private String courseName;
	@XmlElement
	private List<String> attendHistory;
	public Integer getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public List<String> getAttendHistory() {
		return attendHistory;
	}
	public void setAttendHistory(List<String> attendHistory) {
		this.attendHistory = attendHistory;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
