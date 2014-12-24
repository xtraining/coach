package com.coach.request.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "teams")
public class TeamSyncDataList{
	@XmlElementWrapper(name = "teamDataList") 
	@XmlElement(name = "teamData")
	private List<TeamSyncData> teamDataList;

	public List<TeamSyncData> getTeamDataList() {
		return teamDataList;
	}

	public void setTeamDataList(List<TeamSyncData> teamDataList) {
		this.teamDataList = teamDataList;
	}
	
}

