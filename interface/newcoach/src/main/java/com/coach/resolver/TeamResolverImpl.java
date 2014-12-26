package com.coach.resolver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Config;
import com.coach.common.Constants;
import com.coach.common.Constants.TEAM_TYPE;
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
import com.coach.request.v2.CheckSyncData;
import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncData;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncData;
import com.coach.request.v2.TeamSyncDataList;
import com.coach.response.v1.CheckResponse;
import com.coach.response.v1.GetTeamCheckResponse;
import com.coach.response.v1.MemberResponse;
import com.coach.response.v1.TeamCheckResponse;
import com.coach.response.v1.TeamResponse;
import com.coach.utils.DateUtils;
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
			check.setLongitude(request.getLongitude());
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
        			url = StringUtils.replace(url, "{location}", latitude + "," + longitude);
        			String response = HttpUtil.getServer(url, "");
        			log.info("url = " + url);
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
		Date date = DateUtils.yyyyMMddToDate(DateUtils.dateToyyyyMMdd(new Date()));
		Timestamp startTime = new Timestamp(date.getTime() + 3 * 3600 * 1000);
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
		List<Member> memberList = teamCheckDao.getAllMemberByCheckId(request.getCoachId(), request.getTeamCheckId());
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
		if(rList.size() > 0){
			response.add(rList);
		}
		
		List<TeamMember> teamMemberList = teamCheckDao.getTeamMemberListByPhoneNumber(request.getCoachId(), request.getPhoneNumber());
		for(TeamMember teamMember : teamMemberList){
			if(teamMember.getMemberId().longValue() != request.getMemberId()){
				List<TeamCheck> tempList = teamCheckDao.getMemberCheckHistory(request.getCoachId(), teamMember.getTeamId(), teamMember.getMemberId());
				List<TeamCheckResponse> tempRlist = new ArrayList<TeamCheckResponse>();
				for(TeamCheck teamCheck : tempList){
					tempRlist.add(teamCheck.toTeamCheckResponse());
				}
				if(tempRlist.size() > 0){
					response.add(tempRlist);
				}
			}
		}
		
		return response;
	}

	@Override
	public Long getDoneNumber(Long coachId, TEAM_TYPE type) {
		Long num = teamDao.getDoneNumber(coachId, type);
		return num;
	}

	@Override
	public void deleteMemberInBatch(MemberIdListRequest request) {
		String []ids = StringUtils.split(request.getMemberIds(), ",");
		List<Long> list = new ArrayList<Long>();
		for(String id : ids){
			list.add(Long.valueOf(StringUtils.trim(id)));
		}
		if(list.size() > 0){
			memberDao.delete(request.getCoachId(), request.getTeamId(), list);
		}
	}

	@Override
	@Transactional
	public void changeMemberTeamInBatch(ChangeMemberTeamRequest request) {
		String []ids = StringUtils.split(request.getMemberIds(), ",");
		List<Long> list = new ArrayList<Long>();
		for(String id : ids){
			list.add(Long.valueOf(StringUtils.trim(id)));
		}
		if(list.size() > 0){
			teamMemberDao.changeMemberToDone(request.getTeamId(), list);
			for(Long memberId : list){
				TeamMember m = new TeamMember();
				m.setMemberId(memberId);
				m.setTeamId(request.getTargetTeamId());
				m.setStatus(0);
				teamMemberDao.insert(m);
			}
		}
		
	}

	@Override
	@Transactional
	public void syncData(Long coachId, TeamSyncDataList teams, MemberSyncDataList members, CheckSyncDataList checks) {
		List<Team> teamList = new ArrayList<Team>();
		if(teams != null){
			List<TeamSyncData> teamDataList = teams.getTeamDataList();
			if(teamDataList != null){
				for(TeamSyncData teamData : teamDataList){
					Integer operationType = teamData.getOperationType();
					Team team = teamData.toTeam();
					team.setCoachId(coachId);
					if(operationType == Constants.OPERATTION_TYPE.ADD.getValue()){
						teamDao.insert(team);
					} else if(operationType == Constants.OPERATTION_TYPE.EDIT.getValue()){
						boolean isOverDue = teamDao.checkOverDue(team.getId(), DateUtils.yyyyMMddHHmmssToTimestamp(teamData.getOperationTime()));
						if(!isOverDue){
							teamDao.updateTeam(team);
						} else {
							log.info("Team Name " + team.getName() + " is over due");
						}
					}  else if(operationType == Constants.OPERATTION_TYPE.DELETE.getValue()){
						team.setStatus(-1);
						if(team.getId() != null && team.getId() > 0){
							teamDao.updateTeamStatus(team);
						} else {
							teamDao.insert(team);
						}
					}  else if(operationType == Constants.OPERATTION_TYPE.DONE.getValue()){
						team.setStatus(1);
						if(team.getId() != null && team.getId() > 0){
							teamDao.updateTeamStatus(team);
						} else {
							teamDao.insert(team);
						}
					}  else if(operationType == Constants.OPERATTION_TYPE.REDO.getValue()){
						team.setStatus(0);
						if(team.getId() != null && team.getId() > 0){
							teamDao.updateTeamStatus(team);
						} else {
							teamDao.insert(team);
						}
					}
					if(teamData.getTeamId() == null || teamData.getTeamId() == 0){
						teamData.setTeamId(team.getId());
					}
					teamList.add(team);
				}
			}
		}
		List<Member> memberList = new ArrayList<Member>();
		if(members != null){
			List<MemberSyncData> memberDataList = members.getMemberDataList();
			if(memberDataList != null){
				for(MemberSyncData memberData : memberDataList){
					Integer operationType = memberData.getOperationType();
					Member member = memberData.toMember();
					member.setCoachId(coachId);
					if(operationType == Constants.OPERATTION_TYPE.ADD.getValue()){
						memberDao.insert(member);
						TeamMember teamMember = new TeamMember();
						teamMember.setStatus(0);
						if(memberData.getTeamId() != null && memberData.getTeamId() > 0){
							teamMember.setTeamId(memberData.getTeamId());
						} else {
							for(Team team : teamList){
								if(team.getAppTeamId().intValue() == memberData.getAppTeamId()){
									teamMember.setTeamId(team.getId());
								}
							}
						}
						teamMember.setMemberId(member.getId());
						if(teamMember.getTeamId() != null){
							teamMemberDao.insert(teamMember);
						} else {
							log.error("team id error");
						}
					} else if(operationType == Constants.OPERATTION_TYPE.EDIT.getValue()){//对于修改学员，学员和班级肯定是已经同步过的数据
						memberDao.update(coachId, memberData.getTeamId(), member);
					}  else if(operationType == Constants.OPERATTION_TYPE.DELETE.getValue()){
						if(memberData.getTeamId() != null && memberData.getTeamId() > 0){
							if(member.getId() != null && member.getId() > 0){
								memberDao.delete(coachId, memberData.getTeamId(), member.getId());
							} else {
								memberDao.insert(member);
								TeamMember teamMember = new TeamMember();
								teamMember.setStatus(-1);
								teamMember.setTeamId(memberData.getTeamId());
								teamMember.setMemberId(member.getId());
								teamMemberDao.insert(teamMember);
							}
						} else {
							memberDao.insert(member);
							TeamMember teamMember = new TeamMember();
							teamMember.setStatus(-1);
							for(Team team : teamList){
								if(team.getAppTeamId().intValue() == memberData.getAppTeamId()){
									teamMember.setTeamId(team.getId());
								}
							}
							teamMember.setMemberId(member.getId());
							if(teamMember.getTeamId() != null){
								teamMemberDao.insert(teamMember);
							} else {
								log.error("team id error");
							}
						}
					} 
					if(memberData.getMemberId() == null || memberData.getMemberId() == 0){
						memberData.setMemberId(member.getId());
					}
					memberList.add(member);
				}
			}
		}
		List<TeamCheck> teamCheckList = new ArrayList<TeamCheck>();
		if(checks != null){
			List<CheckSyncData> checkDataList = checks.getCheckDataList();
			if(checkDataList != null){
				for(CheckSyncData checkData : checkDataList){
					Integer operationType = checkData.getOperationType();
					TeamCheck teamCheck = checkData.toTeamCheck();
					String []attendMemberIds = StringUtils.split(checkData.getAttendAppMemberId(), ",");
					Set<Long> attendMemberIdList = new HashSet<Long>();
					if(attendMemberIds != null){
						for(String attendMemberId : attendMemberIds){
							attendMemberIdList.add(Long.valueOf(StringUtils.trim(attendMemberId)));
						}
					}
					String []attendAppMemberIds = StringUtils.split(checkData.getAttendAppMemberId(), ",");
					if(attendAppMemberIds != null){
						for(String attendAppMemberId : attendAppMemberIds){
							for(Member member : memberList){
								if(!attendMemberIdList.contains(Long.valueOf(member.getAppMemberId())) && member.getAppMemberId() == Integer.valueOf(attendAppMemberId)){
									attendMemberIdList.add(member.getId());
									break;
								}
							}
						}
					}
					
					if(teamCheck.getTeamId() == null || teamCheck.getTeamId() == 0){
						for(Team team : teamList){
							if(team.getAppTeamId().intValue() == checkData.getAppTeamId()){
								teamCheck.setTeamId(team.getId());
							}
						}
					}
					boolean valid = false;
					if(operationType == Constants.OPERATTION_TYPE.ADD.getValue()){
						valid = true;
						teamCheckDao.insert(teamCheck);
					} else if(operationType == Constants.OPERATTION_TYPE.EDIT.getValue()){
						valid = teamCheckDao.validTeamCheckId(teamCheck.getTeamId(), teamCheck.getId());
						if(valid){
							teamCheckDao.updateTime(teamCheck.getId());
							teamCheckMemberDao.deleteByTeamCheckId(teamCheck.getId());
						}
					} 
					if(checkData.getTeamId() == null || checkData.getTeamId() <= 0){
						checkData.setTeamId(teamCheck.getTeamId());
					}
					if(checkData.getTeamCheckId() == null || checkData.getTeamCheckId() <= 0){
						checkData.setTeamCheckId(teamCheck.getId());
					}
					if(valid){
						if(attendMemberIdList.size() > 0){
							teamCheckMemberDao.saveAttend(teamCheck.getId(), teamCheck.getTeamId(), new ArrayList<Long>(attendMemberIdList));
						}
						teamCheckMemberDao.saveAbcent(teamCheck.getId(), teamCheck.getTeamId(), new ArrayList<Long>(attendMemberIdList));
						teamCheckList.add(teamCheck);
						
						SimpleAsyncTaskExecutor executor1 = new SimpleAsyncTaskExecutor("location-"+teamCheck.getId());
						executor1.setConcurrencyLimit(-1);
					    executor1.execute(new UpdateLocationThread(teamCheck.getId(), teamCheck.getLongitude(), teamCheck.getLatitude()), 60000L);	
					}
				}
			}
		}
	}

}
