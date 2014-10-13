package com.coach.dao;

import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.model.CoachCourse;
import com.coach.model.User;



public interface CoachCourseDao {

	void save(CoachCourse cc);

	void updateStatus(Integer coachId, Long courseId,
			COACH_COURSE_STATUS accepted);



}
