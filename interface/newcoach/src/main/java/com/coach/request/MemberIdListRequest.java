package com.coach.request;

import javax.validation.constraints.NotNull;




public class MemberIdListRequest extends TeamIdRequest{
	@NotNull
	private String memberIds;

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}
	
}

