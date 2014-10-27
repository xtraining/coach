package com.coach.request;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.coach.common.Constants.ALERT_SWITCH;
import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;




public class AddPersonalRequest extends CoachBaseRequest{
	@NotNull
    private String name;
	@NotNull
	private String address;
	private Double longitude;
	private Double latitude;
	@NotNull
	private String startTime;
	@NotNull
	private String endTime;
	private Integer alertSwitch = 0;
	private String remarks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Lesson toLesson() {
		Lesson lesson = new Lesson();
		lesson.setAddress(address);
		lesson.setName(name);
		lesson.setAddress(address);
		lesson.setLatitude(latitude);
		lesson.setLongitude(longitude);
		lesson.setStartTime(DateUtils.yyyyMMddHHmmssToTimestamp(startTime));
		lesson.setEndTime(DateUtils.yyyyMMddHHmmssToTimestamp(endTime));
		double hours = (lesson.getEndTime().getTime() - lesson.getStartTime().getTime())*1.0 / 1000 / 3600 ;
		lesson.setHours((float)hours);
		lesson.setType(LESSON_TYPE.LIFE.getValue());
		lesson.setAlertSwitch(alertSwitch);
		lesson.setCoachId(getCoachId());
		return lesson;
	}

    
}

