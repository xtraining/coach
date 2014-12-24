package com.coach.response.v1;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getTeamCheckResponse")
public class GetTeamCheckResponse implements Serializable {
	@XmlElement
	private Long teamCheckId;
	@XmlElement
	private List<MemberResponse> memberResponse;

	public List<MemberResponse> getMemberResponse() {
		return memberResponse;
	}

	public void setMemberResponse(List<MemberResponse> memberResponse) {
		this.memberResponse = memberResponse;
	}

	public Long getTeamCheckId() {
		return teamCheckId;
	}

	public void setTeamCheckId(Long teamCheckId) {
		this.teamCheckId = teamCheckId;
	}



	
}
