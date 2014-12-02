package com.coach.resolver;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.AddLessonRequest;
import com.coach.request.AddPersonalRequest;
import com.coach.request.CheckLessonMemberRequest;
import com.coach.request.GetCheckLessonRequest;
import com.coach.request.GetRecentLessonRequest;
import com.coach.request.UpdateLessonRequest;
import com.coach.request.UpdateLifeRequest;
import com.coach.response.ConflictLessonResponse;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonMemberResponse;
import com.coach.response.LessonResponse;
import com.coach.response.MemberResponse;
import com.coach.response.TotalLessonResponse;
import com.coach.response.WeekLessonResponse;
@Service
public class LessonResolver implements ILessonResolver{

	@Override
	public WeekLessonResponse getOneWeekLesson(Long coachId, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LessonDetailResponse getLessonDetail(Integer type, Long coachId,
			Long lessonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LessonDetailResponse> getRecentLessonDetail(
			GetRecentLessonRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LessonMemberResponse getLessonMember(Long coachId, Long lessonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCheckMember(CheckLessonMemberRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConflictLessonResponse addPersonal(AddPersonalRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConflictLessonResponse addLesson(AddLessonRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TotalLessonResponse getTotalLesson(Long coachId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LessonResponse> getCheckLesson(GetCheckLessonRequest request,
			Date startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberResponse> getLessonMemberList(Long coachId, Long lessonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLesson(Long lessonId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConflictLessonResponse updateLesson(UpdateLessonRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConflictLessonResponse updateLife(UpdateLifeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}
