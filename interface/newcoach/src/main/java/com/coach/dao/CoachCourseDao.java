package com.coach.dao;

import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.model.CoachCourse;



public interface CoachCourseDao {

	void save(CoachCourse cc);

	void updateStatus(Long coachId, Long courseId,
			COACH_COURSE_STATUS accepted);



}
