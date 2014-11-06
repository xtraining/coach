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
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonMemberResponse;
import com.coach.response.LessonResponse;
import com.coach.response.MemberResponse;
import com.coach.response.TotalLessonResponse;
import com.coach.response.WeekLessonResponse;
@Service
public interface ILessonResolver {

	WeekLessonResponse getOneWeekLesson(Long coachId, String date);

	LessonDetailResponse getLessonDetail(Integer type, Long coachId, Long lessonId);

	List<LessonDetailResponse> getRecentLessonDetail(
			GetRecentLessonRequest request);

	LessonMemberResponse getLessonMember(Long coachId, Long lessonId);

	void saveCheckMember(CheckLessonMemberRequest request);

	void addPersonal(AddPersonalRequest request);

	void addLesson(AddLessonRequest request);

	TotalLessonResponse getTotalLesson(Long coachId);

	List<LessonResponse> getCheckLesson(GetCheckLessonRequest request, Date startDate);

	List<MemberResponse> getLessonMemberList(Long coachId, Long lessonId);

	void deleteLesson(Long lessonId);

	void updateLesson(UpdateLessonRequest request);

	void updateLife(UpdateLifeRequest request);

}
