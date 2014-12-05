package com.coach.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.AddMemberRequest;
import com.coach.request.ChangeTeamStatusRequest;
import com.coach.request.CheckMemberRequest;
import com.coach.request.CreateTeamRequest;
import com.coach.request.GetMemberDetailRequest;
import com.coach.request.GetTeamCheckHistoryRequest;
import com.coach.request.MemberIdRequest;
import com.coach.request.TeamCheckIdRequest;
import com.coach.request.TeamIdRequest;
import com.coach.request.UpdateMemberRequest;
import com.coach.request.UpdateTeamRequest;
import com.coach.response.CheckResponse;
import com.coach.response.GetTeamCheckResponse;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberResponse;
import com.coach.response.TeamCheckResponse;
import com.coach.response.TeamResponse;
@Service
public interface TeamResolver {

	Long create(CreateTeamRequest request);

	void updateTeam(UpdateTeamRequest request);

	List<TeamResponse> getListByCoachId(Long coachId, Integer status);

	void updateTeamStatus(ChangeTeamStatusRequest request);

	Long createMember(AddMemberRequest request);

	List<MemberResponse> getMemberListByTeamId(TeamIdRequest request);

	void updateMember(UpdateMemberRequest request);

	void deleteMember(MemberIdRequest request);

	void checkMember(CheckMemberRequest request);

	GetTeamCheckResponse getLatestCheck(Long coachId, Long teamId);

	List<CheckResponse> getTeamCheckHistory(GetTeamCheckHistoryRequest request);

	List<MemberResponse> getTeamCheckById(TeamCheckIdRequest request);

	List<List<TeamCheckResponse>> getMemberDetail(GetMemberDetailRequest request);


}
