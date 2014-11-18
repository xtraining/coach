package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;

public class UpdateLifeRequest extends UpdateLessonRequest{
	@NotNull
	private String name;
	@NotNull
	private String address;
	private Double longitude;
	private Double latitude;
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
	
}

