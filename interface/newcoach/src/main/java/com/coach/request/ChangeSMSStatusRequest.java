package com.coach.request;

import javax.validation.constraints.NotNull;



public class ChangeSMSStatusRequest extends CoachBaseRequest{
	@NotNull
    private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}

