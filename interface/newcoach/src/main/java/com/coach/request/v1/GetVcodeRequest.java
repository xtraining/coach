package com.coach.request.v1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



public class GetVcodeRequest extends BaseRequest{
	@NotNull
    @Pattern(regexp = "^[1-9]\\d{10}$")
    private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

