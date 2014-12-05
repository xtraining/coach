package com.coach.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.AddMemberRequest;
import com.coach.request.ChangeTeamStatusRequest;
import com.coach.request.CheckMemberRequest;
import com.coach.request.CreateTeamRequest;
import com.coach.request.GetMemberListRequest;
import com.coach.request.MemberIdRequest;
import com.coach.request.UpdateMemberRequest;
import com.coach.request.UpdateTeamRequest;
import com.coach.response.MemberResponse;
import com.coach.response.TeamResponse;
@Service
public interface TeamResolver {

	Long create(CreateTeamRequest request);

	void updateTeam(UpdateTeamRequest request);

	List<TeamResponse> getListByCoachId(Long coachId, Integer status);

	void updateTeamStatus(ChangeTeamStatusRequest request);

	Long createMember(AddMemberRequest request);

	List<MemberResponse> getMemberListByTeamId(GetMemberListRequest request);

	void updateMember(UpdateMemberRequest request);

	void deleteMember(MemberIdRequest request);

	void checkMember(CheckMemberRequest request);


}
