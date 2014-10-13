package com.coach.resolver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.oschina.j2cache.CacheObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_FLAG;
import com.coach.common.Constants.COURSE_MEMBER_STATUS;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.dao.CoachCourseDao;
import com.coach.dao.CoachRejectCourseDao;
import com.coach.dao.CourseDao;
import com.coach.dao.CourseMemberDao;
import com.coach.dao.LessonDao;
import com.coach.dao.LessonMemberDao;
import com.coach.dao.MemberDao;
import com.coach.model.CoachCourse;
import com.coach.model.CoachRejectCourse;
import com.coach.model.Course;
import com.coach.model.CourseMember;
import com.coach.model.Lesson;
import com.coach.model.LessonMember;
import com.coach.model.Member;
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
import com.coach.response.OrgCourseResponse;
import com.coach.response.PersonalCourseResponse;
import com.coach.response.SearchMemberResponse;
import com.rop.utils.RopUtils;
@Service
public class CourseResolver extends BaseResolver implements ICourseResolver{
	@Resource private CourseDao courseDao;
	@Resource private LessonDao lessonDao;
	@Resource private MemberDao memberDao;
	@Resource private CourseMemberDao courseMemberDao;
	@Resource private LessonMemberDao lessonMemberDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Resource private CoachRejectCourseDao coachRejectCourseDao;
	
	public ChiefCourseResponse getChiefCourse(Integer coachId) {
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_HOUR.getValue(), ONE_HOUR_CACHE_KEY.CHIEF_COURSE.getValue() + coachId);
		ChiefCourseResponse response = (ChiefCourseResponse) cachObject.getValue();
		if(response != null){
			return response;
		} else {
			List<Course> list = courseDao.getChiefCourse(coachId, 3);
			Map<Integer, OrgCourseResponse> map = new HashMap<Integer, OrgCourseResponse>();
			for(Course c : list){
				OrgCourseResponse orgC = map.get(c.getOrganizationId());
				if(orgC == null){
					orgC = c.getOrgCoureseResponse();
					map.put(c.getOrganizationId(), orgC);
				} 
				orgC.getCourseResponseList().add(c.toCourseResonse());
			}
			response = new ChiefCourseResponse();
			List<OrgCourseResponse> listValue = new ArrayList<OrgCourseResponse>();
			Iterator<Integer> it = map.keySet().iterator();  
		    while (it.hasNext()) {  
		        Integer key = it.next();  
		        listValue.add(map.get(key));  
		    }  
		    response.setOrgCoureResponseList(listValue);
		    cache.set(CACHE_REGION.ONE_HOUR.getValue(), ONE_HOUR_CACHE_KEY.CHIEF_COURSE.getValue() + coachId, response);
			return response;
		}
	}
	

	@Override
	public List<CourseResponse> getOrgCourse(GetOrgCourseRequest request) {
		List<Course> list = courseDao.getOrgCourse(request);
		List<CourseResponse> response = new ArrayList<CourseResponse>();
		for(Course c : list){
			response.add(c.toCourseResonse());
		}
		return response;
	}


	public int checkNewCourse(Integer coachId) {
		Long result = courseDao.checkNewCourse(coachId);
		if(result != null && result > 0){
			return 1;
		}
		return 0;
	}

	public List<CourseResponse> getNewCourse(Integer coachId, Integer pageNumber, Integer pageSize) {
		List<Course> list = courseDao.getNewCourse(coachId, pageNumber, pageSize);
		List<CourseResponse> r = new ArrayList<CourseResponse>();
		for(Course c : list){
			r.add(c.toCourseResonse());
		}
		return r;
	}

	@Override
	@Transactional
	public ConflictLessonResponse addCourese(AddCourseRequest request, String[] phoneNumberArr,
			String[] memberNameArr) {
		Course c = request.toCourse();
		if(c.getLessonList() != null && c.getLessonList().size() > 0){
			List<Lesson> lessonList = lessonDao.getLessonFrom(request.getCoachId(), c.getStartTime());
			List<Lesson>newLessonList = c.getLessonList();
			Lesson conflictLess = null;
			for(Lesson newLesson : newLessonList){
				for(Lesson oldLesson : lessonList){
					Timestamp newStart = newLesson.getStartTime();
					Timestamp newEnd = newLesson.getEndTime();
					Timestamp oldStart = oldLesson.getStartTime();
					Timestamp oldEnd = oldLesson.getStartTime();
					if((newStart.getTime() >= oldStart.getTime() && newStart.getTime() < oldEnd.getTime())
							|| (newEnd.getTime() > oldStart.getTime() && newEnd.getTime() <= oldEnd.getTime())
				            || (newStart.getTime() <= oldStart.getTime() && newEnd.getTime() >= oldEnd.getTime())){
						conflictLess = oldLesson;
						return conflictLess.toConflictResponse();
					}
				}
			}
		}
		courseDao.insert(c);
		CoachCourse cc = new CoachCourse();
		cc.setCoachId(request.getCoachId());
		cc.setCourseId(c.getId());
		cc.setFlag(COURSE_FLAG.MODIFIED.getValue());
		cc.setStatus(COACH_COURSE_STATUS.ACCEPTED.getValue());
		coachCourseDao.save(cc);
		
		createLessonAndMember(phoneNumberArr, memberNameArr, c);
		cache.evictPrefix(CACHE_REGION.ONE_DAY.getValue(), Arrays.asList(
				new String[]{ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() +  request.getCoachId(),
						ONE_DAY_CACHE_KEY.COACH_RECENT_LESSON.getValue() + request.getCoachId(),
						ONE_DAY_CACHE_KEY.COACH_STUDENT.getValue() + request.getCoachId()}));
		return new ConflictLessonResponse();
	}

	private void createLessonAndMember(String[] phoneNumberArr,
			String[] memberNameArr, Course c) {
		for(Lesson lesson : c.getLessonList()){
			lesson.setCourseId(c.getId());
			lessonDao.insert(lesson);
		}
		List<CourseMember> cmList = new ArrayList<CourseMember>();
		List<LessonMember> lmList = new ArrayList<LessonMember>();
		for(int i = 0; i < phoneNumberArr.length; i++){
			Member m = new Member();
			m.setPhoneNumber(phoneNumberArr[i]);
			m.setName(memberNameArr[i]);
			m.setCode(StringUtils.replace(RopUtils.getUUID(), "-", ""));
			memberDao.save(m);
			
			CourseMember cm = new CourseMember();
			cm.setCourseId(c.getId());
			cm.setMemberId(m.getId());
			cm.setStatus(COURSE_MEMBER_STATUS.ACTIVE.getValue());
			cmList.add(cm);
			
			if(c.getLessonList() != null && c.getLessonList().size() > 0){
				for(Lesson lesson : c.getLessonList()){
					LessonMember lm = new LessonMember();
					lm.setLessonId(lesson.getId());
					lm.setMemberId(m.getId());
					lm.setStatus(COURSE_MEMBER_STATUS.ACTIVE.getValue());
					lmList.add(lm);
				}
			}
		}
		if(cmList.size() >0){
			courseMemberDao.save(cmList);
		}
		if(c.getLessonList() != null && c.getLessonList().size() > 0 && lmList.size() >0){
			lessonMemberDao.save(lmList);
		}
	}
	
	@Override
	public void updateCourese(UpdateCourseRequest request,
			String[] memberIdArr, String[] phoneNumberArr,
			String[] memberNameArr) {
		Course c = request.toCourse();
//		courseDao.update(c);
		
	}

	@Override
	public CourseDetailResponse getCourseDetail(Integer coachId, Long courseId) {
		String key = ONE_DAY_CACHE_KEY.COACH_COURSE.getValue() + coachId + "_" + courseId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), key);
		CourseDetailResponse response = (CourseDetailResponse) cachObject.getValue();
		if(response != null){
			return response;
		} else {
			Course c = courseDao.getCourseDetail(coachId, courseId);
			if(c == null){
				return new CourseDetailResponse();
			}
			response = c.toDetailResponse();
//			List<Member> list = memberDao.getMemberByCourseId(courseId);
//			for(Member m : list){
//				response.getMemberList().add(m.toResponse());
//			}
			cache.set(CACHE_REGION.ONE_DAY.getValue(), key, response);
			return response;
		}
	}


	@Override
	public List<PersonalCourseResponse> getPersonalCourseList(Integer coachId) {
		List<PersonalCourseResponse> response = courseDao.getPersonalCourseList(coachId);
		return response;
	}


	@Override
	public Long checkCourse(Integer coachId, Long courseId) {
		Long id = courseDao.checkCourse(coachId, courseId);
		return id;
	}




	@Override
	@SuppressWarnings("unchecked")
	public List<CourseMemberResponse> getCourseMember(Integer coachId) {
		Map<Long, CourseMemberResponse> map = new HashMap<Long, CourseMemberResponse>();
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_STUDENT.getValue() + coachId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
		List<CourseMemberResponse> listValue = (List<CourseMemberResponse>) cachObject.getValue();
		if(listValue != null){
			return listValue;
		} else {
			List<Member> memberList = memberDao.getMemberByCoachId(coachId);
			for(Member m : memberList){
				Long key = m.getCourseId();
				if(!map.containsKey(key)){
					CourseMemberResponse cmResponse = new CourseMemberResponse();
					cmResponse.setCourseId(m.getCourseId());
					cmResponse.setName(m.getCourseName());
//					cmResponse.setDate(DateUtils.dateToyyyyMMddHHmiss(m.getDate()));
					cmResponse.setMemberNum(m.getMemberNum());
					cmResponse.setCourseType(m.getCourseType());
					map.put(key, cmResponse);
				}
			}
			listValue = new ArrayList<CourseMemberResponse>();
			Iterator<Long> it = map.keySet().iterator();  
			while (it.hasNext()) {  
				Long key = it.next();  
				listValue.add(map.get(key));  
			}  
			Collections.sort(listValue, new Comparator<CourseMemberResponse>() {
				@Override
				public int compare(CourseMemberResponse o1, CourseMemberResponse o2) {
					if(o2.getDate() != null){
						return o2.getDate().compareTo(o1.getDate());
					} else {
						return 0;
					}
				}
			});
			cache.set(CACHE_REGION.ONE_DAY.getValue(), cacheKey, listValue);
		}
		return listValue;
	}

	@Override
	public List<SearchMemberResponse> searchMember(Integer coachId,
			String keyword) {
		if(StringUtils.isBlank(keyword)){
			return new ArrayList<SearchMemberResponse>();
		}
		List<Member> list = memberDao.getMemberByCoachIdAndKeyword(coachId, keyword);
		List<SearchMemberResponse> response = new ArrayList<SearchMemberResponse>();
		for(Member m : list){
			SearchMemberResponse r = m.toSearhcMemberResponse();
			response.add(r);
		}
		return response;
	}


	@Override
	public List<MemberResponse> getCourseMember(Integer coachId, Long courseId) {
		List<MemberResponse> response = new ArrayList<MemberResponse>();
		List<Member> list = memberDao.getMemberByCourseId(courseId);
		for(Member m : list){
			response.add(m.toResponse());
		}
		return response;
	}


	@Override
	@Transactional
	public void deleteCourse(Integer coachId, Long courseId) {
		courseDao.deleteCourse(coachId, courseId);
		lessonDao.deleteLesson(coachId, courseId);
	}


	@Override
	@Transactional
	public void updateCoachCourseStatus(Integer coachId, Long courseId,
			Integer status) {
		if(status == COACH_COURSE_STATUS.ACCEPTED.getValue()){
			coachCourseDao.updateStatus(coachId, courseId, COACH_COURSE_STATUS.ACCEPTED);
		} else if(status == COACH_COURSE_STATUS.REJECT.getValue()){
			coachCourseDao.updateStatus(coachId, courseId, COACH_COURSE_STATUS.REJECT);
			coachRejectCourseDao.saveRejectCourse(coachId, courseId);
		}
		
	}


	@Override
	public CourseDetailResponse getRejectCourseDetail(Integer coachId,
			Long courseId) {
		String key = ONE_DAY_CACHE_KEY.COACH_COURSE.getValue() + coachId + "_" + courseId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), key);
		CourseDetailResponse response = (CourseDetailResponse) cachObject.getValue();
		if(response != null){
			return response;
		} else {
			CoachRejectCourse c = coachRejectCourseDao.getRejectCourseDetail(coachId, courseId);
			if(c == null){
				return new CourseDetailResponse();
			}
			response = c.toDetailResponse();
//			List<Member> list = memberDao.getMemberByCourseId(courseId);
//			for(Member m : list){
//				response.getMemberList().add(m.toResponse());
//			}
			cache.set(CACHE_REGION.ONE_DAY.getValue(), key, response);
			return response;
		}
	}


	@Override
	public List<CourseResponse> getUnassignedCourse(CoachBaseRequest request) {
		List<Course> list = courseDao.getUnassignedCourse(request.getCoachId());
		List<CourseResponse> response = new ArrayList<CourseResponse>();
		for(Course c : list){
			CourseResponse r = c.toCourseResonse();
			response.add(r);
		}
		return response;
	}
}
