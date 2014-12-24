package com.coach.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.coach.model.Team;
import com.coach.request.v1.BaseSyncData;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "teamSyncData")
public class TeamSyncData extends BaseSyncData{
	@XmlAttribute
	private Integer appTeamId;
	//0:新增 1：修改 2：删除  3：结课
	private Long teamId;
	private String teamName;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getAppTeamId() {
		return appTeamId;
	}
	public void setAppTeamId(Integer appTeamId) {
		this.appTeamId = appTeamId;
	}
	public Team toTeam() {
		Team t = new Team();
		t.setStatus(0);
		t.setId(teamId);
		t.setName(teamName);
		t.setAppTeamId(Long.valueOf(appTeamId));
		return t;
	}
	
}

