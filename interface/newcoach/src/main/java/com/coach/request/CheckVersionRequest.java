package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;

public class CheckVersionRequest extends BaseRequest{
	@NotNull
    private String versionNum;

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}


	
}
