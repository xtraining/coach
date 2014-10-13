package com.coach.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.coach.common.Constants;
import com.coach.request.AddCourseRequest;
import com.coach.request.CoachBaseRequest;
import com.coach.request.GetCourseDetailRequest;
import com.coach.request.GetNewCourseRequest;
import com.coach.request.GetOrgCourseRequest;
import com.coach.request.UpdateCourseRequest;
import com.coach.request.UpdateCourseStatusRequest;
import com.coach.resolver.ICourseResolver;
import com.coach.response.ChiefCourseResponse;
import com.coach.response.ConflictLessonResponse;
import com.coach.response.CourseDetailResponse;
import com.coach.response.CourseResponse;
import com.coach.response.MemberResponse;
import com.coach.response.PersonalCourseResponse;
import com.coach.response.SimpleResponse;
import com.rop.annotation.HttpAction;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;
import com.rop.response.BusinessServiceErrorResponse;

@ServiceMethodBean
public class CourseService extends SimpleBaseService{
	@Resource private ICourseResolver courseResolver;
	
	@ServiceMethod(method = "course.getChiefCourse", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getChiefCourse(CoachBaseRequest request) {
		ChiefCourseResponse r = courseResolver.getChiefCourse(request.getCoachId());
		int result = courseResolver.checkNewCourse(request.getCoachId());
		if(result > 0){
			r.setHasNew(1);
		}
		return r;
	}
	
	@ServiceMethod(method = "course.getOrgCourse", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getOrgCourse(GetOrgCourseRequest request) {
		if(request.getPageNumber() == null || request.getPageNumber() <= 0){
			request.setPageNumber(1);
		} 
		if(request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 100){
			request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		}
		List<CourseResponse> r = courseResolver.getOrgCourse(request);
		return r;
	}
	
	@ServiceMethod(method = "course.getNewCourse", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getNewCourse(GetNewCourseRequest request) {
		if(request.getPageNumber() == null || request.getPageNumber() <= 0){
			request.setPageNumber(1);
		} 
		if(request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 100){
			request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		}
		List<CourseResponse> r = courseResolver.getNewCourse(request.getCoachId(), request.getPageNumber(), request.getPageSize());
		return r;
	}
	
	@ServiceMethod(method = "course.addCourse", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object addCourse(AddCourseRequest request) {
		if(request.getCourseHour() < request.getLessonHour() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COURSE_HOUR_LESS_THAN_LESSON_HOUR",
        			request.getRopRequestContext().getLocale());
		}
		String[]phoneNumberArr = StringUtils.split(request.getPhoneNumberList(), "||");
		String[]memberNameArr = StringUtils.split(request.getMemberNameList(), "||");
		if(phoneNumberArr.length != memberNameArr.length){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "MEMBER_NAME_PHONE_NUMBER_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		ConflictLessonResponse r = courseResolver.addCourese(request, phoneNumberArr, memberNameArr);
		return r;
	}
	
	@ServiceMethod(method = "course.updateCourse", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object updateCourse(UpdateCourseRequest request) {
		if(request.getCourseHour() < request.getLessonHour() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COURSE_HOUR_LESS_THAN_LESSON_HOUR",
        			request.getRopRequestContext().getLocale());
		}
		String[]memberIdArr = StringUtils.split(request.getMemberNameList(), "||");
		String[]phoneNumberArr = StringUtils.split(request.getPhoneNumberList(), "||");
		String[]memberNameArr = StringUtils.split(request.getMemberNameList(), "||");
		if(memberIdArr.length != phoneNumberArr.length || phoneNumberArr.length != memberNameArr.length
				|| memberIdArr.length != memberNameArr.length){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "MEMBER_NAME_PHONE_NUMBER_MEMBER_ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		SimpleResponse r = new SimpleResponse();
		courseResolver.updateCourese(request, memberIdArr, phoneNumberArr, memberNameArr);
		return r;
	}
	
	@ServiceMethod(method = "course.getCourseDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getCourseDetail(GetCourseDetailRequest request) {
		CourseDetailResponse r = courseResolver.getCourseDetail(request.getCoachId(), request.getCourseId());
		return r;
	}
	
	@ServiceMethod(method = "course.getRejectCourseDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getRejectCourseDetail(GetCourseDetailRequest request) {
		CourseDetailResponse r = courseResolver.getRejectCourseDetail(request.getCoachId(), request.getCourseId());
		return r;
	}
	
	@ServiceMethod(method = "course.getCourseMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getCourseMember(GetCourseDetailRequest request) {
		List<MemberResponse> r = courseResolver.getCourseMember(request.getCoachId(), request.getCourseId());
		return r;
	}
	
	@ServiceMethod(method = "course.deleteCourse", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object deleteCourse(GetCourseDetailRequest request) {
		courseResolver.deleteCourse(request.getCoachId(), request.getCourseId());
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "course.updateCoachCourseStatus", version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object updateCoachCourseStatus(UpdateCourseStatusRequest request) {
		courseResolver.updateCoachCourseStatus(request.getCoachId(), request.getCourseId(), request.getStatus());
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "course.getPersonalCourseList", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getPersonalCourseList(CoachBaseRequest request) {
		List<PersonalCourseResponse> r = courseResolver.getPersonalCourseList(request.getCoachId());
		return r;
	}
	
	@ServiceMethod(method = "course.getUnassignedCourse", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getUnassignedCourse(CoachBaseRequest request) {
		List<CourseResponse> r = courseResolver.getUnassignedCourse(request);
		return r;
	}
}