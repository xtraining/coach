package com.coach.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.common.Constants.TEAM_TYPE;
import com.coach.request.v1.AddMemberRequest;
import com.coach.request.v1.ChangeMemberTeamRequest;
import com.coach.request.v1.ChangeTeamStatusRequest;
import com.coach.request.v1.CheckMemberRequest;
import com.coach.request.v1.CreateTeamRequest;
import com.coach.request.v1.GetMemberDetailRequest;
import com.coach.request.v1.GetTeamCheckHistoryRequest;
import com.coach.request.v1.MemberIdListRequest;
import com.coach.request.v1.MemberIdRequest;
import com.coach.request.v1.TeamCheckIdRequest;
import com.coach.request.v1.TeamIdRequest;
import com.coach.request.v1.UpdateMemberRequest;
import com.coach.request.v1.UpdateTeamRequest;
import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncDataList;
import com.coach.response.v1.CheckResponse;
import com.coach.response.v1.GetTeamCheckResponse;
import com.coach.response.v1.MemberResponse;
import com.coach.response.v1.TeamCheckResponse;
import com.coach.response.v1.TeamResponse;
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

	Long checkMember(CheckMemberRequest request);

	GetTeamCheckResponse getLatestCheck(Long coachId, Long teamId);

	List<CheckResponse> getTeamCheckHistory(GetTeamCheckHistoryRequest request);

	List<MemberResponse> getTeamCheckById(TeamCheckIdRequest request);

	List<List<TeamCheckResponse>> getMemberDetail(GetMemberDetailRequest request);

	Long getDoneNumber(Long coachId, TEAM_TYPE normal);

	void deleteMemberInBatch(MemberIdListRequest request);

	void changeMemberTeamInBatch(ChangeMemberTeamRequest request);

	void syncData(Long coachId, TeamSyncDataList teams, MemberSyncDataList members, CheckSyncDataList checkSyncDataList);


}
