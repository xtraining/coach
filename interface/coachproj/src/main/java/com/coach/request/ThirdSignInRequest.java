package com.coach.request;

import javax.validation.constraints.NotNull;


public class ThirdSignInRequest extends BaseRequest{
    @NotNull
    private String thirdPartyId;
    @NotNull
    private Integer type;
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}

