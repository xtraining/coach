package com.coach.dao;

import java.util.List;

import com.coach.model.Course;
import com.coach.request.GetOrgCourseRequest;
import com.coach.response.CourseResponse;
import com.coach.response.PersonalCourseResponse;



public interface CourseDao {

	List<Course> getChiefCourse(Long coachId, int maxNum);
	
	List<Course> getOrgChiefCourse(Long coachId, int maxNum);
	
	List<Course> getPersonalChiefCourse(Long coachId, int maxNum);

	Long checkNewCourse(Long coachId);

	List<Course> getNewCourse(Long coachId, Integer pageNumber,
			Integer pageSize);

	void insert(Course c);

	Course getCourseDetail(Long coachId, Long courseId);

	List<Course> getOrgCourse(GetOrgCourseRequest request);

	List<PersonalCourseResponse> getPersonalCourseList(Long coachId);

	Long checkCourse(Long coachId, Long courseId);

	void deleteCourse(Long coachId, Long courseId);

	List<Course> getUnassignedCourse(Long coachId);
	

}
