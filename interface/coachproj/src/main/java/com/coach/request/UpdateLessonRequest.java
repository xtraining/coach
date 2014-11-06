package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;

public class UpdateLessonRequest extends CoachBaseRequest{
	@NotNull
	private String startTime;
	@NotNull
	private String endTime;
	@NotNull
	private Integer alertSwitch = 0;
	private String remarks;
	@NotNull
	private Long lessonId;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getAlertSwitch() {
		return alertSwitch;
	}
	public void setAlertSwitch(Integer alertSwitch) {
		this.alertSwitch = alertSwitch;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getLessonId() {
		return lessonId;
	}
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	
}

