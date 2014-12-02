package com.coach.request;

import javax.validation.constraints.NotNull;




public class UpdateMemberRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;
	@NotNull
	private Long memberId;
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
}

