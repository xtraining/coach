package com.coach.request;

import javax.validation.constraints.NotNull;




public class CheckMemberRequest extends CoachBaseRequest{
	@NotNull
    private Long teamId;
	private String attendMemberId;
//	private String absentMemberId;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getAttendMemberId() {
		return attendMemberId;
	}

	public void setAttendMemberId(String attendMemberId) {
		this.attendMemberId = attendMemberId;
	}

/*	public String getAbsentMemberId() {
		return absentMemberId;
	}

	public void setAbsentMemberId(String absentMemberId) {
		this.absentMemberId = absentMemberId;
	}*/
	
}

