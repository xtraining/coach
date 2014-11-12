package com.coach.response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "coachCourseStatusResponse")
public class CoachCourseStatusResponse extends SimpleResponse{
	@XmlElement
	private Integer courseNewsFlag = 0;

	public Integer getCourseNewsFlag() {
		return courseNewsFlag;
	}

	public void setCourseNewsFlag(Integer courseNewsFlag) {
		this.courseNewsFlag = courseNewsFlag;
	}
	
}
