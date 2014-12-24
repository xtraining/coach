package com.coach.request.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "members")
public class MemberSyncDataList{
	@XmlElementWrapper(name = "memberDataList") 
	@XmlElement(name = "memberData")
	private List<MemberSyncData> memberDataList;

	public List<MemberSyncData> getMemberDataList() {
		return memberDataList;
	}

	public void setMemberDataList(List<MemberSyncData> memberDataList) {
		this.memberDataList = memberDataList;
	}


}

