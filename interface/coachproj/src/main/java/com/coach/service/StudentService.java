package com.coach.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Constants.NEWS_TYPE;
import com.coach.request.CoachBaseRequest;
import com.coach.request.GetMemberDetailRequest;
import com.coach.request.GetMemberRequest;
import com.coach.request.SearchMemberRequest;
import com.coach.request.UpdateMemberDetailRequest;
import com.coach.request.UpdateMemberStatusRequest;
import com.coach.resolver.ICourseResolver;
import com.coach.resolver.ILessonResolver;
import com.coach.resolver.IMemberResolver;
import com.coach.resolver.INewsHistoryResolver;
import com.coach.response.CourseMemberResponse;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberNewsResponse;
import com.coach.response.SearchMemberResponse;
import com.coach.response.SimpleResponse;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean
public class StudentService extends SimpleBaseService{
	@Autowired private IMemberResolver memberResolver;
	@Autowired private INewsHistoryResolver newsHistoryResolver;
	@Resource private ILessonResolver lessonResolver;
	@Resource private ICourseResolver courseResolver;
	@ServiceMethod(method = "student.getCourseList", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getCourseList(CoachBaseRequest request) {
		List<CourseMemberResponse> r = courseResolver.getCourseMember(request.getCoachId());
		return r;
	}
	
	@ServiceMethod(method = "student.searchMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object searchMember(SearchMemberRequest request) {
		List<SearchMemberResponse> r = courseResolver.searchMember(request.getCoachId(), request.getKeyword());
		return r;
	}
	
	@ServiceMethod(method = "student.getMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getMember(GetMemberRequest request) {
		List<MemberDetailResponse> r = memberResolver.getMember(request.getCoachId(), request.getCourseId());
		return r;
	}
	
	@ServiceMethod(method = "student.getMemberDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getMemberDetail(GetMemberDetailRequest request) {
		MemberDetailResponse r = memberResolver.getMemberDetail(request.getCoachId(), request.getCourseId(), request.getMemberId());
		return r;
	}
	
	@ServiceMethod(method = "student.getMemberNews", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getMemberNews(CoachBaseRequest request) {
		List<MemberNewsResponse> r = memberResolver.getMemberNews(request.getCoachId());
		newsHistoryResolver.updateHistory(NEWS_TYPE.MEMBER_NEWS, request.getCoachId());
		return r;
	}
	
	@ServiceMethod(method = "student.updateMemberDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateMemberDetail(UpdateMemberDetailRequest request) {
		memberResolver.updateMember(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "student.updateMemberStatus", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateMemberStatus(UpdateMemberStatusRequest request) {
		memberResolver.updateMemberStatus(request);
		return new SimpleResponse();
	}
	
}
