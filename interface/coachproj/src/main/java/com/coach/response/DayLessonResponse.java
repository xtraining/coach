package com.coach.response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dayLessonResponse")
public class DayLessonResponse implements Serializable{
	@XmlElement
	private Integer day;
	@XmlElement
	private String date;
	@XmlElement
	private Float hours = 0F;
	@XmlElement
	private List<LessonResponse> lessonList = new ArrayList<LessonResponse>();
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Float getHours() {
		return hours;
	}
	public void setHours(Float hours) {
		this.hours = hours;
	}
	public List<LessonResponse> getLessonList() {
		return lessonList;
	}
	public void setLessonList(List<LessonResponse> lessonList) {
		this.lessonList = lessonList;
	}
	
}
