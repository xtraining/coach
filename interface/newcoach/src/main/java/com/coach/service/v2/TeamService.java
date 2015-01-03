package com.coach.service.v2;

import java.util.List;

import javax.annotation.Resource;

import com.coach.request.v2.CheckMemberOfflineRequest;
import com.coach.request.v2.GetTeamListOfflineRequest;
import com.coach.resolver.TeamResolver;
import com.coach.response.v1.SimpleResponse;
import com.coach.response.v1.TeamResponse;
import com.coach.response.v2.CheckMemberResponse;
import com.coach.response.v2.GetTeamListResponse;
import com.coach.service.v1.SimpleBaseService;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean(value="TeamServiceV2", version = "2.0")
public class TeamService extends SimpleBaseService{
	@Resource private TeamResolver teamResolver;
	
	@ServiceMethod(method = "team.getList", needInSession = NeedInSessionType.YES)
    public Object getList(GetTeamListOfflineRequest request) {
		GetTeamListResponse r = new GetTeamListResponse();
		try{
			teamResolver.syncData(request.getCoachId(), request.getTeams(), request.getMembers(), request.getChecks());
		} catch(Throwable e){
			e.printStackTrace();
			r.setFlag(1);
		}
		r.setChecks(request.getChecks());
		r.setTeams(request.getTeams());
		r.setMembers(request.getMembers());
		List<TeamResponse> list = teamResolver.getListByCoachId(request.getCoachId(), request.getStatus());
		r.setTeamList(list);
		return r;
	}
	
	@ServiceMethod(method = "team.checkMember", needInSession = NeedInSessionType.YES)
    public Object checkMember(CheckMemberOfflineRequest request) {
		CheckMemberResponse r = new CheckMemberResponse();
		try{
			teamResolver.syncData(request.getCoachId(), request.getTeams(), request.getMembers(), request.getChecks());
		} catch(Throwable e){
			e.printStackTrace();
			r.setFlag(1);
		}
		r.setFlag(0);
		r.setChecks(request.getChecks());
		r.setTeams(request.getTeams());
		r.setMembers(request.getMembers());
		return r;
	}
	
}