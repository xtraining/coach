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
	@NotNull
	private String address;
	private Double longitude;
	private Double latitude;
	@NotNull
    private String groundName;
	private Integer groundId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	
}

