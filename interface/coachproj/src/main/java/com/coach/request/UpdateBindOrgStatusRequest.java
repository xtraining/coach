package com.coach.request;

import javax.validation.constraints.NotNull;

public class UpdateBindOrgStatusRequest extends CoachBaseRequest{
	@NotNull
    private Integer orgId;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	
}
