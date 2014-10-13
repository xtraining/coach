package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;

public class CoachBaseRequest extends BaseRequest{
	@NotNull
    private Integer coachId;

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

	
}
