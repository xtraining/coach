package com.coach.dao;

import java.util.List;

import com.coach.model.Course;
import com.coach.request.GetOrgCourseRequest;
import com.coach.response.CourseResponse;
import com.coach.response.PersonalCourseResponse;



public interface CourseDao {

	List<Course> getChiefCourse(Integer coachId, int maxNum);

	Long checkNewCourse(Integer coachId);

	List<Course> getNewCourse(Integer coachId, Integer pageNumber,
			Integer pageSize);

	void insert(Course c);

	Course getCourseDetail(Integer coachId, Long courseId);

	List<Course> getOrgCourse(GetOrgCourseRequest request);

	List<PersonalCourseResponse> getPersonalCourseList(Integer coachId);

	Long checkCourse(Integer coachId, Long courseId);

	void deleteCourse(Integer coachId, Long courseId);

	List<Course> getUnassignedCourse(Integer coachId);
	

}
