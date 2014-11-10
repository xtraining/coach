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
	public ChiefCourseResponse getChiefCourse(Long coachId, Integer type);

	public int checkNewCourse(Long coachId);

	public List<CourseResponse> getNewCourse(Long coachId, Integer pageNumber, Integer pageSize);

	public ConflictLessonResponse addCourese(AddCourseRequest request, String[] phoneNumberArr, String[] memberNameArr) ;

	public CourseDetailResponse getCourseDetail(Long coachId, Long courseId);

	public List<CourseResponse> getOrgCourse(GetOrgCourseRequest request);

	public List<PersonalCourseResponse> getPersonalCourseList(Long coachId);

	public Long checkCourse(Long coachId, Long courseId);

	public List<CourseMemberResponse> getCourseMember(Long coachId);

	public List<SearchMemberResponse> searchMember(Long coachId,
			String keyword);

	public List<MemberResponse> getCourseMember(Long coachId, Long courseId);

	public void deleteCourse(Long coachId, Long courseId);

	public void updateCoachCourseStatus(Long coachId, Long courseId,
			Integer status);

	public CourseDetailResponse getRejectCourseDetail(Long coachId,
			Long courseId);

	public List<CourseResponse> getUnassignedCourse(CoachBaseRequest request);

	public List<CourseResponse> getPersonalCourse(GetOrgCourseRequest request);

	public ConflictLessonResponse updateCourese(UpdateCourseRequest request,
			String[] modifyMemberIdArr, String[] modifyPhoneNumberArr,
			String[] modifyMemberNameArr, String[] newPhoneNumberArr,
			String[] newMemberNameArr, String[] deletedMemberIdArr);

}
