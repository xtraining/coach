package com.coach.request;

import javax.validation.constraints.NotNull;




public class UpdateTeamRequest extends CoachBaseRequest{
	@NotNull
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
