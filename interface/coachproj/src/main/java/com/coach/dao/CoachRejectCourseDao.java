package com.coach.dao;

import com.coach.model.CoachRejectCourse;



public interface CoachRejectCourseDao {
	

	public void saveRejectCourse(Long coachId, Long courseId);

	public CoachRejectCourse getRejectCourseDetail(Long coachId, Long courseId);

}
