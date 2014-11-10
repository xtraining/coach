package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lessonDetailResponse")
public class MonthLessonResponse implements Serializable {
	@XmlElement
	private String month;
	@XmlElement
	private Double orgHours = 0D;
	@XmlElement
	private Double personalHours = 0D;
	public String getMonth() {
		return month;
	}
	public void setMonth(String moth) {
		this.month = moth;
	}
	public Double getOrgHours() {
		return orgHours;
	}
	public void setOrgHours(Double orgHours) {
		this.orgHours = orgHours;
	}
	public Double getPersonalHours() {
		return personalHours;
	}
	public void setPersonalHours(Double personalHours) {
		this.personalHours = personalHours;
	}

	
}
