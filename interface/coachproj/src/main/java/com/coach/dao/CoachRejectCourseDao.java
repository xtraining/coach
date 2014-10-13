package com.coach.dao;

import com.coach.model.CoachRejectCourse;



public interface CoachRejectCourseDao {
	

	public void saveRejectCourse(Integer coachId, Long courseId);

	public CoachRejectCourse getRejectCourseDetail(Integer coachId, Long courseId);

}
