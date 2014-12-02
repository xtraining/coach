package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;

public class GetTeamListRequest extends CoachBaseRequest{
	@NotNull
    private Integer status;  //0:正常 1：结课 2：全部

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
