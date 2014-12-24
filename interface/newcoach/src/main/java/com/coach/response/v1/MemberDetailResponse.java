package com.coach.response.v1;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "memberDetailResponse")
public class MemberDetailResponse extends MemberResponse implements Serializable {
	@XmlElement
	private List<TeamCheckResponse> teamCheckResponse;

	public List<TeamCheckResponse> getTeamCheckResponse() {
		return teamCheckResponse;
	}

	public void setTeamCheckResponse(List<TeamCheckResponse> teamCheckResponse) {
		this.teamCheckResponse = teamCheckResponse;
	}
	
}
