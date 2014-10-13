package com.coach.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coach.request.AddCourseRequest;
import com.coach.request.CoachBaseRequest;
import com.coach.request.GetOrgCourseRequest;
import com.coach.request.UpdateCourseRequest;
import com.coach.response.ChiefCourseResponse;
import com.coach.response.ConflictLessonResponse;
import com.coach.response.CourseDetailResponse;
import com.coach.response.CourseMemberResponse;
import com.coach.response.CourseResponse;
import com.coach.response.MemberResponse;
import com.coach.response.PersonalCourseResponse;
import com.coach.response.SearchMemberResponse;
@Service
public interface ICourseResolver {
	public ChiefCourseResponse getChiefCourse(Integer coachId);

	public int checkNewCourse(Integer coachId);

	public List<CourseResponse> getNewCourse(Integer coachId, Integer pageNumber, Integer pageSize);

	public ConflictLessonResponse addCourese(AddCourseRequest request, String[] phoneNumberArr, String[] memberNameArr) ;

	public CourseDetailResponse getCourseDetail(Integer coachId, Long courseId);

	public void updateCourese(UpdateCourseRequest request,
			String[] memberIdArr, String[] phoneNumberArr,
			String[] memberNameArr);

	public List<CourseResponse> getOrgCourse(GetOrgCourseRequest request);

	public List<PersonalCourseResponse> getPersonalCourseList(Integer coachId);

	public Long checkCourse(Integer coachId, Long courseId);

	public List<CourseMemberResponse> getCourseMember(Integer coachId);

	public List<SearchMemberResponse> searchMember(Integer coachId,
			String keyword);

	public List<MemberResponse> getCourseMember(Integer coachId, Long courseId);

	public void deleteCourse(Integer coachId, Long courseId);

	public void updateCoachCourseStatus(Integer coachId, Long courseId,
			Integer status);

	public CourseDetailResponse getRejectCourseDetail(Integer coachId,
			Long courseId);

	public List<CourseResponse> getUnassignedCourse(CoachBaseRequest request);

}
