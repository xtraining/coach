package com.coach.response.v2;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncDataList;
import com.coach.response.v1.TeamResponse;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getTeamListResponse")
public class GetTeamListResponse implements Serializable {
	@XmlElement
	private Integer flag = 0; //0:离线同步成功  1：离线同步失败 
	@XmlElement
	private List<TeamResponse> teamList;
	@XmlElement
	private TeamSyncDataList teams;
	@XmlElement
	private MemberSyncDataList members;
	@XmlElement
	private CheckSyncDataList checks;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public List<TeamResponse> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<TeamResponse> teamList) {
		this.teamList = teamList;
	}
	public TeamSyncDataList getTeams() {
		return teams;
	}
	public void setTeams(TeamSyncDataList teams) {
		this.teams = teams;
	}
	public MemberSyncDataList getMembers() {
		return members;
	}
	public void setMembers(MemberSyncDataList members) {
		this.members = members;
	}
	public CheckSyncDataList getChecks() {
		return checks;
	}
	public void setChecks(CheckSyncDataList checks) {
		this.checks = checks;
	}

	
}
