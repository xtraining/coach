package com.coach.request;

import javax.validation.constraints.NotNull;


public class GetMemberDetailRequest extends MemberIdRequest{
	@NotNull
    private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
