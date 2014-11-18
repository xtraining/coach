package com.coach.dao;

import java.util.List;

import com.coach.common.Constants.LESSON_MEMBER_STATUS;
import com.coach.model.LessonMember;
import com.coach.model.Member;



public interface LessonMemberDao {

	public void save(List<LessonMember> cmList);

	public List<Member> getLessonMember(Long lessonId);

	public void updateStatus(Long lessonId, String[] memberIds, LESSON_MEMBER_STATUS check);

	public void insertLessonMember(Long courseId, Long id);

	public void updateStatusByCourseId(Long memberId, Long courseId,
			LESSON_MEMBER_STATUS deleted);

}
