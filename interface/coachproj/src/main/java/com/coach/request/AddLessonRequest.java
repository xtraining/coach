package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;

public class AddLessonRequest extends AddPersonalRequest{
	@NotNull
	private Long courseId;
	@NotNull
    private String groundName;
	private Integer groundId;
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
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
	
	public Lesson toLesson() {
		Lesson lesson = new Lesson();
		lesson.setAddress(getAddress());
		lesson.setName(getName());
		lesson.setAddress(getAddress());
		lesson.setLatitude(getLatitude());
		lesson.setLongitude(getLongitude());
		lesson.setStartTime(DateUtils.yyyyMMddHHmmssToTimestamp(getStartTime()));
		lesson.setEndTime(DateUtils.yyyyMMddHHmmssToTimestamp(getEndTime()));
		double hours = (lesson.getEndTime().getTime() - lesson.getStartTime().getTime())*1.0 / 1000 / 3600 ;
		lesson.setHours((float)hours);
		lesson.setType(LESSON_TYPE.JOB.getValue());
		lesson.setAlertSwitch(getAlertSwitch());
		lesson.setCourseId(getCourseId());
		lesson.setGroundId(getGroundId());
		lesson.setGroundName(getGroundName());
		return lesson;
	}
	
}

