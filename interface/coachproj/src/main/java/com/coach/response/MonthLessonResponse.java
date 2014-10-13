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
	private Integer orgHours = 0;
	@XmlElement
	private Integer personalHours = 0;
	public String getMonth() {
		return month;
	}
	public void setMonth(String moth) {
		this.month = moth;
	}
	public Integer getOrgHours() {
		return orgHours;
	}
	public void setOrgHours(Integer orgHours) {
		this.orgHours = orgHours;
	}
	public Integer getPersonalHours() {
		return personalHours;
	}
	public void setPersonalHours(Integer personalHours) {
		this.personalHours = personalHours;
	}

	
}
