package com.coach.request.v1;

import javax.validation.constraints.NotNull;




public class UpdateTeamRequest extends TeamIdRequest{
	@NotNull
    private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

