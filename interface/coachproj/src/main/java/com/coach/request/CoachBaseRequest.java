package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;

public class CoachBaseRequest extends BaseRequest{
	@NotNull
    private Long coachId;

	public Long getCoachId() {
		return coachId;
	}

	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}

	
}
