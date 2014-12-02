package com.coach.request;

import javax.validation.constraints.NotNull;




public class MemberIdRequest extends TeamIdRequest{
	@NotNull
	private Long memberId;
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
}

