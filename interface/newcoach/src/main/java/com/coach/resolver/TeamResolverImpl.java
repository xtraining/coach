package com.coach.resolver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Config;
import com.coach.dao.CoachDao;
import com.coach.dao.MemberDao;
import com.coach.dao.TeamCheckDao;
import com.coach.dao.TeamCheckMemberDao;
import com.coach.dao.TeamDao;
import com.coach.dao.TeamMemberDao;
import com.coach.model.Coach;
import com.coach.model.Member;
import com.coach.model.Team;
import com.coach.model.TeamCheck;
import com.coach.model.TeamMember;
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
import com.coach.utils.HttpUtil;
import com.coach.utils.JsonBinder;
@Service
public class TeamResolverImpl implements TeamResolver{
	private static final Logger log = LoggerFactory.getLogger(TeamResolverImpl.class);
	@Resource TeamDao teamDao;
	@Resource MemberDao memberDao;
	@Resource TeamMemberDao teamMemberDao;
	@Resource TeamCheckMemberDao teamCheckMemberDao;
	@Resource TeamCheckDao teamCheckDao;
	@Resource CoachDao coachDao;
	@Resource SmsResolver smsResolver;
	@Override
	public Long create(CreateTeamRequest request) {
		Team t = new Team();
		t.setName(request.getName());
		t.setCoachId(request.getCoachId());
		t.setStatus(0);
		teamDao.insert(t);
		return t.getId();
	}

	@Override
	public void updateTeam(UpdateTeamRequest request) {
		Team t = new Team();
		t.setName(request.getName());
		t.setCoachId(request.getCoachId());
		t.setId(request.getTeamId());
		teamDao.updateTeam(t);
		
	}

	@Override
	public List<TeamResponse> getListByCoachId(Long coachId, Integer status) {
		List<Team> list = teamDao.getListByCoachId(coachId, status);
		List<TeamResponse> rList = new ArrayList<TeamResponse>();
		for(Team team : list){
			TeamResponse r = team.toResponse();
			rList.add(r);
		}
		return rList;
	}

	@Override
	public void updateTeamStatus(ChangeTeamStatusRequest request) {
		Team t = new Team();
		t.setStatus(request.getStatus());
		t.setCoachId(request.getCoachId());
		t.setId(request.getTeamId());
		teamDao.updateTeamStatus(t);
		
	}

	@Override
	@Transactional
	public Long createMember(AddMemberRequest request) {
		Member m = new Member();
		m.setName(request.getMemberName());
		m.setPhoneNumber(request.getPhoneNumber());
		m.setCoachId(request.getCoachId());
		m.setGender(0);
		m.setStatus(0);
		memberDao.insert(m);
		
		TeamMember t = new TeamMember();
		t.setMemberId(m.getId());
		t.setTeamId(request.getTeamId());
		t.setStatus(0);
		teamMemberDao.insert(t);
		return t.getId();
	}

	@Override
	public List<MemberResponse> getMemberListByTeamId(
			TeamIdRequest request) {
		List<Member> list = memberDao.getList(request.getCoachId(), request.getTeamId());
		List<MemberResponse> rList = new ArrayList<MemberResponse>();
		for(Member m : list){
			MemberResponse r = m.toResponse();
			rList.add(r);
		}
		return rList;
	}

	@Override
	public void updateMember(UpdateMemberRequest request) {
		Member m = request.toMember();
		memberDao.update(request.getCoachId(), request.getTeamId(), m);
		
	}

	@Override
	public void deleteMember(MemberIdRequest request) {
		memberDao.delete(request.getCoachId(), request.getTeamId(), request.getMemberId());
		
	}

	@Override
	public void checkMember(CheckMemberRequest request) {
		Coach c = coachDao.getBasicById(request.getCoachId());
		boolean smsSwitch = c.getSmsSwitch() != null && c.getSmsSwitch() > 0 ? true : false;
		List<Member> smsMemberList = null;
		List<Long> attendMemberIdList = new ArrayList<Long>();
		String attendIdArr[] = StringUtils.split(request.getAttendMemberId(), ",");
		for(String id : attendIdArr){
			attendMemberIdList.add(Long.valueOf(id.trim()));
		}
		if(attendMemberIdList.size() == 0){
			attendMemberIdList.add(0L);
		}
		TeamCheck check = new TeamCheck();
		if(request.getTeamCheckId() != null && request.getTeamCheckId() > 0){
			check.setId(request.getTeamCheckId());
			if(smsSwitch){
				smsMemberList = teamCheckMemberDao.getSmsMemberList(request.getTeamId(), check.getId(), attendMemberIdList);
			}
			teamCheckDao.updateTime(check.getId());
			teamCheckMemberDao.deleteByTeamCheckId(check.getId());
		} else {
			check.setLatitude(request.getLatitude());
			check.setLongitude(request.getLatitude());
			check.setStatus(0);
			check.setTeamId(request.getTeamId());
			teamCheckDao.insert(check);
			List<Member> memberList = memberDao.getList(request.getCoachId(), request.getTeamId());
			if(smsSwitch){
				smsMemberList = new ArrayList<Member>();
				for(Member m : memberList){
					Member smsMember = new Member();
					smsMember.setId(m.getId());
					smsMember.setName(m.getName());
					smsMember.setPhoneNumber(m.getPhoneNumber());
					if(attendMemberIdList.contains(m.getId())){
						smsMember.setStatus(1); //发送到达短信
					} else{
						smsMember.setStatus(0); //发送未到短信
					}
					smsMemberList.add(smsMember);
				}
			}
		}
		if(attendMemberIdList.size() > 0){
			teamCheckMemberDao.saveAttend(check.getId(), request.getTeamId(), attendMemberIdList);
		}
		teamCheckMemberDao.saveAbcent(check.getId(), request.getTeamId(), attendMemberIdList);
		
		SimpleAsyncTaskExecutor executor1 = new SimpleAsyncTaskExecutor("location-"+check.getId());
		executor1.setConcurrencyLimit(-1);
	    executor1.execute(new UpdateLocationThread(check.getId(), request.getLongitude(), request.getLatitude()), 60000L);	
		
	    if(smsSwitch){
	    	smsResolver.sendAttendSms(smsMemberList, c.getPhoneNumber());
	    }
	}
	
	class UpdateLocationThread implements Runnable {
		private Long teamCheckId;
		private Double latitude;
		private Double longitude;
		public UpdateLocationThread(Long teamCheckId, Double longitude, Double latitude){
			this.teamCheckId = teamCheckId;
			this.longitude = longitude;
			this.latitude = latitude;
		}
        @SuppressWarnings("unchecked")
		public void run() {
        	try {
        		if(latitude != null && longitude != null){
        			String url = "http://api.map.baidu.com/geocoder/v2/?ak={ak}&location={location}&output=json&pois=0";
        			url = StringUtils.replace(url, "{ak}", Config.getProperty("ak"));
        			url = StringUtils.replace(url, "{location}", longitude + "," + latitude);
        			String response = HttpUtil.getServer(url, "");
        			Map <String, Object>addressMap = (Map<String, Object>) JsonBinder.buildNormalBinder().getValue(response, "result");
        			Map <String, Object>addressComponent = (Map<String, Object>) addressMap.get("addressComponent");
        			String province = (String)addressComponent.get("province");
        			String city = (String)addressComponent.get("city");
        			String district = (String)addressComponent.get("district");
        			String street = (String)addressComponent.get("street");
        			String streetNumber = (String)addressComponent.get("street_number");
        			teamCheckDao.updateLoaction(teamCheckId, province, city, district, street, streetNumber);
        		}
			} catch (Throwable e) {
				log.error("UpdateLocationThread error", e);
			}
        }
	}

	@Override
	public GetTeamCheckResponse getLatestCheck(Long coachId, Long teamId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Timestamp startTime = new Timestamp(calendar.getTimeInMillis());
		Long teamCheckId = teamCheckDao.getLatestCheck(teamId, startTime);
		if(teamCheckId == null){
			return null;
		}
		GetTeamCheckResponse response = new GetTeamCheckResponse();
		response.setTeamCheckId(teamCheckId);
		List<Member> memberList = teamCheckDao.getMemberByCheckId(coachId, teamCheckId);
		List<MemberResponse> rList = new ArrayList<MemberResponse>();
		for(Member m : memberList){
			MemberResponse r = m.toResponse();
			rList.add(r);
		}
		response.setMemberResponse(rList);
		return response;
	}

	@Override
	public List<CheckResponse> getTeamCheckHistory(GetTeamCheckHistoryRequest request) {
		List<TeamCheck> list = teamCheckDao.getTeamCheckHistory(request.getTeamId(), request.getPageNumber(), request.getPageSize());
		List<CheckResponse> lt = new ArrayList<CheckResponse>();
		for(TeamCheck check : list){
			CheckResponse r1 = check.toResponse();
			lt.add(r1);
		}
		return lt;
	}

	@Override
	public List<MemberResponse> getTeamCheckById(TeamCheckIdRequest request) {
		List<Member> memberList = teamCheckDao.getMemberByCheckId(request.getCoachId(), request.getTeamCheckId());
		List<MemberResponse> rList = new ArrayList<MemberResponse>();
		for(Member m : memberList){
			MemberResponse r = m.toResponse();
			rList.add(r);
		}
		return rList;
	}

	@Override
	public List<List<TeamCheckResponse>> getMemberDetail(GetMemberDetailRequest request) {
		List<List<TeamCheckResponse>> response = new ArrayList<List<TeamCheckResponse>>();
		List<TeamCheck> list = teamCheckDao.getMemberCheckHistory(request.getCoachId(), request.getTeamId(), request.getMemberId());
		List<TeamCheckResponse> rList = new ArrayList<TeamCheckResponse>();
		for(TeamCheck teamCheck : list){
			rList.add(teamCheck.toTeamCheckResponse());
		}
		response.add(rList);
		
		List<TeamMember> teamMemberList = teamCheckDao.getTeamMemberListByPhoneNumber(request.getCoachId(), request.getPhoneNumber());
		for(TeamMember teamMember : teamMemberList){
			if(teamMember.getMemberId().longValue() != request.getMemberId()){
				List<TeamCheck> tempList = teamCheckDao.getMemberCheckHistory(request.getCoachId(), teamMember.getTeamId(), teamMember.getMemberId());
				List<TeamCheckResponse> tempRlist = new ArrayList<TeamCheckResponse>();
				for(TeamCheck teamCheck : tempList){
					tempRlist.add(teamCheck.toTeamCheckResponse());
				}
				response.add(tempRlist);
			}
		}
		
		return response;
	}
	
}
