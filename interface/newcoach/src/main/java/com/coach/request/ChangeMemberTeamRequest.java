package com.coach.request;

import javax.validation.constraints.NotNull;




public class ChangeMemberTeamRequest extends TeamIdRequest{
	@NotNull
	private String memberIds;
	@NotNull
	private Long targetTeamId;

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public Long getTargetTeamId() {
		return targetTeamId;
	}

	public void setTargetTeamId(Long targetTeamId) {
		this.targetTeamId = targetTeamId;
	}
	
}

