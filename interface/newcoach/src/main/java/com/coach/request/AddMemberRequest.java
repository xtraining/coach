package com.coach.request;

import javax.validation.constraints.NotNull;




public class AddMemberRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;
	@NotNull
	private String memberName;
	@NotNull
	private String phoneNumber;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}

