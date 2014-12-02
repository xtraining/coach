package com.coach.request;

import javax.validation.constraints.NotNull;




public class GetMemberListRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
}

