package com.coach.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.coach.common.Constants;
import com.coach.request.AddLessonRequest;
import com.coach.request.AddPersonalRequest;
import com.coach.request.CheckLessonMemberRequest;
import com.coach.request.GetCheckLessonRequest;
import com.coach.request.GetLessonDetailRequest;
import com.coach.request.GetOneWeekLessonRequest;
import com.coach.request.GetRecentLessonRequest;
import com.coach.request.UpdateLessonRequest;
import com.coach.request.UpdateLifeRequest;
import com.coach.resolver.ICourseResolver;
import com.coach.resolver.ILessonResolver;
import com.coach.response.ConflictLessonResponse;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonMemberResponse;
import com.coach.response.LessonResponse;
import com.coach.response.MemberResponse;
import com.coach.response.SimpleResponse;
import com.coach.response.WeekLessonResponse;
import com.coach.utils.DateUtils;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean
public class LessonService extends SimpleBaseService{
	@Resource private ILessonResolver lessonResolver;
	@Resource private ICourseResolver courseResolver;
	@ServiceMethod(method = "lesson.getOneWeekLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getOneWeekLesson(GetOneWeekLessonRequest request) {
		WeekLessonResponse r = lessonResolver.getOneWeekLesson(request.getCoachId(), request.getDate());
		return r;
	}
	
	@ServiceMethod(method = "lesson.getLessonDetail", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getLessonDetail(GetLessonDetailRequest request) {
		LessonDetailResponse r = lessonResolver.getLessonDetail(request.getType(), request.getCoachId(), request.getLessonId());
		return r;
	}
	
	@ServiceMethod(method = "lesson.getRecentLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getRecentLesson(GetRecentLessonRequest request) {
		List<LessonDetailResponse> r = lessonResolver.getRecentLessonDetail(request);
		return r;
	}
	
	@ServiceMethod(method = "lesson.getLessonMemberList", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getLessonMemberList(GetLessonDetailRequest request) {
		List <MemberResponse> r = lessonResolver.getLessonMemberList(request.getCoachId(), request.getLessonId());
		return r;
	}
	
	@ServiceMethod(method = "lesson.getLessonMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getLessonMember(GetLessonDetailRequest request) {
		LessonMemberResponse r = lessonResolver.getLessonMember(request.getCoachId(), request.getLessonId());
		return r;
	}
	
	@ServiceMethod(method = "lesson.checkMember", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object checkMember(CheckLessonMemberRequest request) {
		lessonResolver.saveCheckMember(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "lesson.addLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object addLesson(AddLessonRequest request) {
		Long courseId = courseResolver.checkCourse(request.getCoachId(), request.getCourseId());
		if(courseId != null && courseId > 0){
			ConflictLessonResponse r = lessonResolver.addLesson(request);
			return r;
		} else {
			SimpleResponse r = new SimpleResponse();
			r.setFlag(1);
			r.setMsg("The course not existing.");
			return r;
		}
	}
	
	@ServiceMethod(method = "lesson.updateLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateLesson(UpdateLessonRequest request) {
		lessonResolver.updateLesson(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "lesson.addPersonal", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object addPersonal(AddPersonalRequest request) {
		ConflictLessonResponse r = lessonResolver.addPersonal(request);
		return r;
	}
	
	@ServiceMethod(method = "lesson.updateLife", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateLife(UpdateLifeRequest request) {
		lessonResolver.updateLife(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "lesson.getCheckLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getCheckLesson(GetCheckLessonRequest request) {
		if(request.getPageNumber() == null || request.getPageNumber() <= 0){
			request.setPageNumber(1);
		} 
		if(request.getPageSize() == null || request.getPageSize() <= 0 || request.getPageSize() > 100){
			request.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		}
		Date startDate = DateUtils.getFirstDayOfMonth(new Date(), -3);
		List<LessonResponse> response = lessonResolver.getCheckLesson(request, startDate);
		return response;
	}
	
	@ServiceMethod(method = "lesson.deleteLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object deleteLesson(GetLessonDetailRequest request) {
		lessonResolver.deleteLesson(request.getLessonId());
		return new SimpleResponse();
	}
	
}
