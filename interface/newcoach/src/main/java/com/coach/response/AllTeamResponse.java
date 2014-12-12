package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "allTeamResponse")
public class AllTeamResponse implements Serializable {
	@XmlElement
	private List<TeamResponse> teamResponseList;
	@XmlElement
	private Long doneTeamNumber;
	public List<TeamResponse> getTeamResponseList() {
		return teamResponseList;
	}
	public void setTeamResponseList(List<TeamResponse> teamResponseList) {
		this.teamResponseList = teamResponseList;
	}
	public Long getDoneTeamNumber() {
		return doneTeamNumber;
	}
	public void setDoneTeamNumber(Long doneTeamNumber) {
		this.doneTeamNumber = doneTeamNumber;
	}
	
}
