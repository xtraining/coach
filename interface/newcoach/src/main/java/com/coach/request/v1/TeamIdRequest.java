package com.coach.request.v1;

import javax.validation.constraints.NotNull;




public class TeamIdRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
}

