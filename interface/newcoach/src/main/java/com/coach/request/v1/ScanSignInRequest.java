package com.coach.request.v1;

import javax.validation.constraints.NotNull;



public class ScanSignInRequest extends CoachBaseRequest{
	@NotNull
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

