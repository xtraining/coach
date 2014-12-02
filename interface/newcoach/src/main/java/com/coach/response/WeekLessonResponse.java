package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "weekLessonResponse")
public class WeekLessonResponse implements Serializable {
	@XmlElement
	private String avatarUrl;
	@XmlElement
	private Integer courseNewsFlag;
	@XmlElement
	private Integer memberNewsFlag;
	@XmlElement
	private Integer startHour = 0;
	@XmlElement
	private List<LessonResponse> lessonList;
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public Integer getCourseNewsFlag() {
		return courseNewsFlag;
	}
	public void setCourseNewsFlag(Integer courseNewsFlag) {
		this.courseNewsFlag = courseNewsFlag;
	}
	public Integer getMemberNewsFlag() {
		return memberNewsFlag;
	}
	public void setMemberNewsFlag(Integer memberNewsFlag) {
		this.memberNewsFlag = memberNewsFlag;
	}
	public List<LessonResponse> getLessonList() {
		return lessonList;
	}
	public void setLessonList(List<LessonResponse> lessonList) {
		this.lessonList = lessonList;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	
}
