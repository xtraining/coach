package com.coach.request.v1;

import javax.validation.constraints.NotNull;




public class TeamCheckIdRequest extends CoachBaseRequest{
	@NotNull
    private Long teamCheckId;

	public Long getTeamCheckId() {
		return teamCheckId;
	}

	public void setTeamCheckId(Long teamCheckId) {
		this.teamCheckId = teamCheckId;
	}
	
}

