package com.coach.resolver;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.AddLessonRequest;
import com.coach.request.AddPersonalRequest;
import com.coach.request.CheckLessonMemberRequest;
import com.coach.request.GetCheckLessonRequest;
import com.coach.request.GetRecentLessonRequest;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonMemberResponse;
import com.coach.response.LessonResponse;
import com.coach.response.MemberResponse;
import com.coach.response.TotalLessonResponse;
import com.coach.response.WeekLessonResponse;
@Service
public interface ILessonResolver {

	WeekLessonResponse getOneWeekLesson(Integer coachId, String date);

	LessonDetailResponse getLessonDetail(Integer coachId, Long lessonId);

	List<LessonDetailResponse> getRecentLessonDetail(
			GetRecentLessonRequest request);

	LessonMemberResponse getLessonMember(Integer coachId, Long lessonId);

	void saveCheckMember(CheckLessonMemberRequest request);

	void addPersonal(AddPersonalRequest request);

	void addLesson(AddLessonRequest request);

	TotalLessonResponse getTotalLesson(Integer coachId);

	List<LessonResponse> getCheckLesson(GetCheckLessonRequest request, Date startDate);

	List<MemberResponse> getLessonMemberList(Integer coachId, Long lessonId);

	void deleteLesson(Long lessonId);

}