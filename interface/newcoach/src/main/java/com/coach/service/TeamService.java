package com.coach.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.coach.common.Constants;
import com.coach.request.AddMemberRequest;
import com.coach.request.ChangeTeamStatusRequest;
import com.coach.request.CheckMemberRequest;
import com.coach.request.CreateTeamRequest;
import com.coach.request.GetMemberDetailRequest;
import com.coach.request.GetTeamCheckHistoryRequest;
import com.coach.request.GetTeamListRequest;
import com.coach.request.MemberIdRequest;
import com.coach.request.TeamCheckIdRequest;
import com.coach.request.TeamIdRequest;
import com.coach.request.UpdateMemberRequest;
import com.coach.request.UpdateTeamRequest;
import com.coach.resolver.TeamResolver;
import com.coach.response.CheckResponse;
import com.coach.response.GetTeamCheckResponse;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberResponse;
import com.coach.response.SimpleResponse;
import com.coach.response.TeamCheckResponse;
import com.coach.response.TeamResponse;
import com.coach.utils.DateUtils;
import com.rop.annotation.HttpAction;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean
public class TeamService extends SimpleBaseService{
	@Resource private TeamResolver teamResolver;

	@ServiceMethod(method = "team.getList", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getList(GetTeamListRequest request) {
		List<TeamResponse> list = teamResolver.getListByCoachId(request.getCoachId(), request.getStatus());
		return list;
	}
	
	@ServiceMethod(method = "team.createTeam", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object createTeam(CreateTeamRequest request) {
		Long teamId = teamResolver.create(request);
		SimpleResponse r =  new SimpleResponse();
		r.setId(teamId);
		return r;
	}
	
	@ServiceMethod(method = "team.updateTeam", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object updateTeam(UpdateTeamRequest request) {
		teamResolver.updateTeam(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "team.changeStatus", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object changeStatus(ChangeTeamStatusRequest request) {
		teamResolver.updateTeamStatus(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "team.getMemberList", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getMemberList(TeamIdRequest request) {
		List<MemberResponse> list = teamResolver.getMemberListByTeamId(request);
		return list;
	}
	
	@ServiceMethod(method = "team.checkMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object checkMember(CheckMemberRequest request) {
		teamResolver.checkMember(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "team.addMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object addMember(AddMemberRequest request) {
		Long memberId = teamResolver.createMember(request); 
		SimpleResponse r =  new SimpleResponse();
		r.setId(memberId);
		return r;
	}
	
	@ServiceMethod(method = "team.deleteMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object deleteMember(MemberIdRequest request) {
		teamResolver.deleteMember(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "team.updateMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateMember(UpdateMemberRequest request) {
		teamResolver.updateMember(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "team.getMemberDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getMemberDetail(GetMemberDetailRequest request) {
		List<List<TeamCheckResponse>> r = teamResolver.getMemberDetail(request);
		return r;
	}
	
	@ServiceMethod(method = "team.getTeamCheck", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getTeamCheck(TeamIdRequest request) {
		GetTeamCheckResponse r = teamResolver.getLatestCheck(request.getCoachId(), request.getTeamId());
		if(r == null){
			List<MemberResponse> list = teamResolver.getMemberListByTeamId(request);
			r = new GetTeamCheckResponse();
			r.setMemberResponse(list);
		}
		return r;
	}
	
	@ServiceMethod(method = "team.getTeamCheckHistory", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getTeamCheckHistory(GetTeamCheckHistoryRequest request) {
		if(request.getPageNumber() == null || request.getPageNumber() <= 0){
			request.setPageNumber(1);
		} 
		if(request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 100){
			request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		}
		List<CheckResponse> lt = teamResolver.getTeamCheckHistory(request)
				; new ArrayList<CheckResponse>();
		CheckResponse r1 = new CheckResponse();
		r1.setTeamCheckId(3L);
		r1.setAddress("东大名路687号");
		r1.setCreateTime(DateUtils.dateToyyyyMMddHHmiss(new Timestamp(new Date().getTime())));
		r1.setAbsentNum(3);
		r1.setAttendNum(5);
		lt.add(r1);
		return lt;
	}
	
	
	@ServiceMethod(method = "team.getTeamCheckDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getTeamCheckDetail(TeamCheckIdRequest request) {
		List<MemberResponse> r = teamResolver.getTeamCheckById(request);
		return r;
	}
}
