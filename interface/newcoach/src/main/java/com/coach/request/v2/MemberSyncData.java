package com.coach.request.v2;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.coach.model.Member;
import com.coach.request.v1.BaseSyncData;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "syncData")
public class MemberSyncData extends BaseSyncData{
	@XmlAttribute
	private Integer appTeamId;
	@XmlAttribute
	private Integer appMemberId;

	
	//0:新增 1：修改 2：删除
	@XmlAttribute
	private Long teamId;
	@XmlAttribute
	private String memberName;
	@XmlAttribute
	private String phoneNumber;
	@XmlAttribute
	private Long memberId;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public Integer getAppTeamId() {
		return appTeamId;
	}

	public void setAppTeamId(Integer appTeamId) {
		this.appTeamId = appTeamId;
	}

	public Integer getAppMemberId() {
		return appMemberId;
	}

	public void setAppMemberId(Integer appMemberId) {
		this.appMemberId = appMemberId;
	}
	public Member toMember() {
		Member m = new Member();
		m.setStatus(0);
		m.setId(memberId);
		m.setName(memberName);
		m.setPhoneNumber(phoneNumber);
		m.setAppMemberId(appMemberId);
		return m;
	}
	
}

