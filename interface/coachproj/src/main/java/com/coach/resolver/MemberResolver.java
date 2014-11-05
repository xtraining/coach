package com.coach.resolver;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.oschina.j2cache.CacheObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.dao.MemberDao;
import com.coach.dao.MemberNewsDao;
import com.coach.model.Member;
import com.coach.request.UpdateMemberDetailRequest;
import com.coach.request.UpdateMemberStatusRequest;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberNewsResponse;
import com.coach.utils.DateUtils;
@Service
public class MemberResolver extends BaseResolver implements IMemberResolver{
	@Resource private MemberDao memberDao;
	@Resource private MemberNewsDao memberNewsDao;
	@SuppressWarnings("unchecked")
	public List<MemberDetailResponse> getMember(Long coachId,
			Long courseId) {
//		String cacheKey = ONE_DAY_CACHE_KEY.COACH_COURSE_MEMBER.getValue() + coachId + "_" + courseId;
//		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
//		List<MemberDetailResponse> response = (List<MemberDetailResponse>) cachObject.getValue();
//		if(response != null){
//			return response;
//		} else{
			List<Member> list = memberDao.getMemberByCoachId(coachId, courseId);
			List<MemberDetailResponse> response = new ArrayList<MemberDetailResponse>();
			for(Member m : list){
				MemberDetailResponse r = m.toMemberDetailResponse();
				response.add(r);
			}
//			cache.set(CACHE_REGION.ONE_DAY.getValue(), cacheKey, response);
//		}
		return response;
	}
	
	public MemberDetailResponse getMemberDetail(Long coachId, Long courseId, Long memberId) {
		List<Member> memberList = memberDao.getMemberWithAttendHistory(coachId, courseId, memberId);
		MemberDetailResponse r = null;
		List<String> attendHistory = new ArrayList<String>();
		if(memberList != null && memberList.size() > 0){
			for(Member m : memberList){
				if(r == null){
					r = m.toMemberDetailResponse();
				}
				attendHistory.add(DateUtils.dateToyyyyMMdd(m.getDate()));
			}
		} else {
			Member m = memberDao.getMemberByCourseIdAndMemberId(courseId, memberId);
			r = m.toMemberDetailResponse();
		}
		r.setAttendHistory(attendHistory);
		return r;
	}
	
	public List<MemberNewsResponse> getMemberNews(Long coachId) {
		List<MemberNewsResponse> response = memberNewsDao.getNews(coachId);
		return response;
	}
	
	@Override
	public void updateMember(UpdateMemberDetailRequest request) {
		Member m = request.toMember();
		memberDao.updateMember(m);
		
	}

	@Override
	public void updateMemberStatus(UpdateMemberStatusRequest request) {
		memberDao.updateMemberStatus(request.getMemberId(), request.getCourseId(), request.getStatus());
	}
}
