package com.coach.request.v1;

import javax.validation.constraints.NotNull;



public class UnbindThirdPartyRequest extends CoachBaseRequest{
    @NotNull
    private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
}

