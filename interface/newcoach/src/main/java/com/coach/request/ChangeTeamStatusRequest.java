package com.coach.request;

import javax.validation.constraints.NotNull;




public class ChangeTeamStatusRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;
	@NotNull
	private Integer status;   //-1:删除  0：正常  1：结课
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    
}

