package com.coach.response.v2;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "syncDataResponse")
public class SyncDataResponse implements Serializable {
	@XmlElement
	private List<SyncSimpleResponse> teamSyncResponse;
	@XmlElement
	private List<SyncSimpleResponse> memberSyncResponse;
	@XmlElement
	private List<SyncSimpleResponse> checkyncResponse;
	public List<SyncSimpleResponse> getTeamSyncResponse() {
		return teamSyncResponse;
	}
	public void setTeamSyncResponse(List<SyncSimpleResponse> teamSyncResponse) {
		this.teamSyncResponse = teamSyncResponse;
	}
	public List<SyncSimpleResponse> getMemberSyncResponse() {
		return memberSyncResponse;
	}
	public void setMemberSyncResponse(List<SyncSimpleResponse> memberSyncResponse) {
		this.memberSyncResponse = memberSyncResponse;
	}
	public List<SyncSimpleResponse> getCheckyncResponse() {
		return checkyncResponse;
	}
	public void setCheckyncResponse(List<SyncSimpleResponse> checkyncResponse) {
		this.checkyncResponse = checkyncResponse;
	}
	
}

