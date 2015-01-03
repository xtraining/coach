package com.coach.request.v2;

import javax.validation.Valid;

import com.coach.request.v1.CheckMemberRequest;




public class CheckMemberOfflineRequest extends CheckMemberRequest{
	@Valid
    private TeamSyncDataList teams;
	@Valid
    private MemberSyncDataList members;
	@Valid
	private CheckSyncDataList checks;
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

