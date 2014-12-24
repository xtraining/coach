package com.coach.request.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncDataList;

public class GetTeamListRequest extends CoachBaseRequest{
	@NotNull
    private Integer status;  //0:正常 1：结课 2：全部
	@Valid
    private TeamSyncDataList teams;
	@Valid
    private MemberSyncDataList members;
	@Valid
	private CheckSyncDataList checks;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
