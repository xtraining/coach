package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "totalLessonResponse")
public class TotalLessonResponse implements Serializable {
	@XmlElement
	private Integer totalHours = 0;
	@XmlElement
	private Integer percent = 0;
	@XmlElement
	private String wording;
	@XmlElement
	private List<MonthLessonResponse> monthLessonList;
	public Integer getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}
	public List<MonthLessonResponse> getMonthLessonList() {
		return monthLessonList;
	}
	public void setMonthLessonList(List<MonthLessonResponse> monthLessonList) {
		this.monthLessonList = monthLessonList;
	}
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}

	
}
