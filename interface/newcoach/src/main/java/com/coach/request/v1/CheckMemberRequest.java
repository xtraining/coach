package com.coach.request.v1;

import javax.validation.constraints.NotNull;




public class CheckMemberRequest extends TeamIdRequest{
	private Long teamCheckId;
	private String attendMemberId;
	private Double latitude;
	private Double longitude;


	public String getAttendMemberId() {
		return attendMemberId;
	}

	public Long getTeamCheckId() {
		return teamCheckId;
	}

	public void setTeamCheckId(Long teamCheckId) {
		this.teamCheckId = teamCheckId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setAttendMemberId(String attendMemberId) {
		this.attendMemberId = attendMemberId;
	}

	
}

