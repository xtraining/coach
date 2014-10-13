package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.model.Member;


public class UpdateMemberDetailRequest extends CoachBaseRequest{
	@NotNull
    private Long memberId;
	private String memberName;
	private String phoneNumber;
	private Integer gender;
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Member toMember() {
		Member m = new Member();
		m.setId(getMemberId());
		m.setName(getMemberName());
		m.setPhoneNumber(getPhoneNumber());
		return m;
	}

	
}
