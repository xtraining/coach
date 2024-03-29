package com.coach.resolver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Constants;
import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_FLAG;
import com.coach.common.Constants.COURSE_MEMBER_STATUS;
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
import com.coach.resolver.cacheaction.CacheAction;
import com.coach.resolver.cacheaction.ChiefCourseCacheAction;
import com.coach.resolver.cacheaction.ClearCacheAction;
import com.coach.resolver.cacheaction.CourseDetailCacheAction;
import com.coach.resolver.cacheaction.CourseMemberCacheAction;
import com.coach.response.ChiefCourseResponse;
import com.coach.response.CoachCourseStatusResponse;
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
	private static final Logger log = LoggerFactory
			.getLogger(CourseResolver.class);
	@Resource private CourseDao courseDao;
	@Resource private LessonDao lessonDao;
	@Resource private MemberDao memberDao;
	@Resource private CourseMemberDao courseMemberDao;
	@Resource private LessonMemberDao lessonMemberDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Resource private CoachRejectCourseDao coachRejectCourseDao;
	
	public ChiefCourseResponse getChiefCourse(Long coachId, Integer type) {
		CacheAction<ChiefCourseResponse> cachAction = new ChiefCourseCacheAction(coachId, type);
		ChiefCourseResponse response = cachAction.getValue();
		if(response != null){
			return response;
		} else {
			List<Course> list = null;
			if(type == 0){
				list = courseDao.getOrgChiefCourse(coachId, Constants.COURSE_OFFSET);
			} else if(type == 1){
				list = courseDao.getPersonalChiefCourse(coachId, Constants.COURSE_OFFSET);
			} else {
				list = courseDao.getChiefCourse(coachId, Constants.COURSE_OFFSET);
			}
			List<Map<String, Object>> courseNumberList = courseDao.getCourseNum(coachId);
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
		        OrgCourseResponse cur = map.get(key);
		        for(Map<String, Object> courseNumberMap : courseNumberList){
		        	Integer courseType = (Integer) courseNumberMap.get("type");
		        	if(cur.getType() == courseType.intValue()){
		        		cur.setCourseNum(((Long) courseNumberMap.get("courseNum")).intValue());
		        		break;
		        	}
		        }
		        listValue.add(cur);  
		    }  
		    response.setOrgCoureResponseList(listValue);
		    cachAction.setValue(response);
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
	
	@Override
	public List<CourseResponse> getPersonalCourse(GetOrgCourseRequest request) {
		List<Course> list = courseDao.getPersonalCourse(request);
		List<CourseResponse> response = new ArrayList<CourseResponse>();
		for(Course c : list){
			response.add(c.toCourseResonse());
		}
		return response;
	}



	public int checkNewCourse(Long coachId) {
		Long result = courseDao.checkNewCourse(coachId);
		if(result != null && result > 0){
			return 1;
		}
		return 0;
	}

	public List<CourseResponse> getNewCourse(Long coachId, Integer pageNumber, Integer pageSize) {
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
					Timestamp oldEnd = oldLesson.getEndTime();
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
		
		if(StringUtils.isBlank(request.getRecycleDay())){
			createFirstLessonAndMember(phoneNumberArr, memberNameArr, c);
		} else{
			createLessonAndMember(phoneNumberArr, memberNameArr, c);
		}
		ClearCacheAction.clearAddCourseCache(request.getCoachId());
		ConflictLessonResponse r = new ConflictLessonResponse();
		r.setId(c.getId());
		return r;
	}
	
	/*private void createCourseMember(String[] phoneNumberArr,
			String[] memberNameArr, Course c) {
		List<CourseMember> cmList = new ArrayList<CourseMember>();
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
		}
		if(cmList.size() >0){
			courseMemberDao.save(cmList);
		}
	}*/

	private void createFirstLessonAndMember(String[] phoneNumberArr,
			String[] memberNameArr, Course c) {
		Lesson firstLesson = null;
		if(c.getLessonList() != null && c.getLessonList().size() > 0){
			firstLesson = c.getLessonList().get(0);
			firstLesson.setCourseId(c.getId());
			lessonDao.insert(firstLesson);
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
			
			if(firstLesson != null){
				LessonMember lm = new LessonMember();
				lm.setLessonId(firstLesson.getId());
				lm.setMemberId(m.getId());
				lm.setStatus(COURSE_MEMBER_STATUS.ACTIVE.getValue());
				lmList.add(lm);
			}
		}
		if(cmList.size() >0){
			courseMemberDao.save(cmList);
		}
		if(c.getLessonList() != null && c.getLessonList().size() > 0 && lmList.size() >0){
			lessonMemberDao.save(lmList);
		}
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
	@Transactional
	public ConflictLessonResponse updateCourese(UpdateCourseRequest request,
			String[] modifyMemberIdArr, String[] modifyPhoneNumberArr,
			String[] modifyMemberNameArr, String[] newPhoneNumberArr,
			String[] newMemberNameArr, String[] deletedMemberIdArr) {
		float assignedHour = lessonDao.getAssignedHour(request.getCourseId());
		Course c = request.toCourse(assignedHour);
		if(c.getLessonList() != null && c.getLessonList().size() > 0){
			List<Lesson> lessonList = lessonDao.getLessonFrom(request.getCoachId(), c.getStartTime());
			List<Lesson>newLessonList = c.getLessonList();
			Lesson conflictLess = null;
			for(Lesson newLesson : newLessonList){
				for(Lesson oldLesson : lessonList){
					Timestamp newStart = newLesson.getStartTime();
					Timestamp newEnd = newLesson.getEndTime();
					Timestamp oldStart = oldLesson.getStartTime();
					Timestamp oldEnd = oldLesson.getEndTime();
					if((newStart.getTime() >= oldStart.getTime() && newStart.getTime() < oldEnd.getTime())
							|| (newEnd.getTime() > oldStart.getTime() && newEnd.getTime() <= oldEnd.getTime())
				            || (newStart.getTime() <= oldStart.getTime() && newEnd.getTime() >= oldEnd.getTime())){
						conflictLess = oldLesson;
						return conflictLess.toConflictResponse();
					}
				}
			}
		}
		courseDao.updateCourse(c);
		//modify
		if(modifyMemberNameArr != null){
			int modifyMemberNum = modifyMemberNameArr.length;
			for(int i = 0; i < modifyMemberNum; i++){
				try{
					Member m = new Member();
					m.setId(Long.valueOf(modifyMemberIdArr[i]));
					m.setName(modifyMemberNameArr[i]);
					m.setPhoneNumber(modifyPhoneNumberArr[i]);
					memberDao.updateBasicMember(m);
				}catch(Throwable ignore){
					log.error("updateCourese", ignore);
				}
			}
		}
		
		//new 
		if(newMemberNameArr != null){
			int newMemberNum = newMemberNameArr.length;
			List<CourseMember> cmList = new ArrayList<CourseMember>();
			for(int i = 0; i < newMemberNum; i++){
				Member m = new Member();
				m.setName(newMemberNameArr[i]);
				m.setPhoneNumber(newPhoneNumberArr[i]);
				m.setCode(StringUtils.replace(RopUtils.getUUID(), "-", ""));
				memberDao.save(m);
				
				CourseMember cm = new CourseMember();
				cm.setCourseId(request.getCourseId());
				cm.setMemberId(m.getId());
				cm.setStatus(COURSE_MEMBER_STATUS.ACTIVE.getValue());
				cmList.add(cm);
				
			}
			if(cmList.size() >0){
				courseMemberDao.save(cmList);
			}
		}
		// delete
		if(deletedMemberIdArr != null && deletedMemberIdArr.length > 0){
			try{
				courseMemberDao.deleteMember(request.getCourseId(), deletedMemberIdArr);
			}catch(Throwable ignore){
				log.error("deleteMember", ignore);
			}
		}
		
		ClearCacheAction.clearAddCourseCache(request.getCoachId());
		return new ConflictLessonResponse();
	}

	@Override
	public CourseDetailResponse getCourseDetail(Long coachId, Long courseId) {
		CacheAction<CourseDetailResponse> cachAction = new CourseDetailCacheAction(coachId, courseId);
		CourseDetailResponse response = cachAction.getValue();
		if(response != null){
			return response;
		} else {
			Course c = courseDao.getCourseDetail(coachId, courseId);
			if(c == null){
				return new CourseDetailResponse();
			}
			response = c.toDetailResponse();
			List<MemberResponse> list = getCourseMember(coachId, courseId);
			response.setMemberList(list);
			cachAction.setValue(response);
			return response;
		}
	}


	@Override
	public List<PersonalCourseResponse> getPersonalCourseList(Long coachId) {
		List<PersonalCourseResponse> response = courseDao.getPersonalCourseList(coachId);
		return response;
	}


	@Override
	public Long checkCourse(Long coachId, Long courseId) {
		Long id = courseDao.checkCourse(coachId, courseId);
		return id;
	}

	@Override
	public List<CourseMemberResponse> getCourseMember(Long coachId) {
		Map<Long, CourseMemberResponse> map = new HashMap<Long, CourseMemberResponse>();
		CacheAction<List<CourseMemberResponse>> cacheAction = new CourseMemberCacheAction(coachId);
		List<CourseMemberResponse> listValue = cacheAction.getValue();
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
			cacheAction.setValue(listValue);
		}
		return listValue;
	}

	@Override
	public List<SearchMemberResponse> searchMember(Long coachId,
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
	public List<MemberResponse> getCourseMember(Long coachId, Long courseId) {
		List<MemberResponse> response = new ArrayList<MemberResponse>();
		List<Member> list = memberDao.getMemberByCourseId(courseId);
		for(Member m : list){
			response.add(m.toResponse());
		}
		return response;
	}


	@Override
	@Transactional
	public void deleteCourse(Long coachId, Long courseId) {
		courseDao.deleteCourse(coachId, courseId);
		lessonDao.deleteLesson(coachId, courseId);
	}


	@Override
	@Transactional
	public CoachCourseStatusResponse updateCoachCourseStatus(Long coachId, Long courseId,
			Integer status) {
		if(status == COACH_COURSE_STATUS.ACCEPTED.getValue()){
			coachCourseDao.updateStatus(coachId, courseId, COACH_COURSE_STATUS.ACCEPTED);
		} else if(status == COACH_COURSE_STATUS.REJECT.getValue()){
			coachCourseDao.updateStatus(coachId, courseId, COACH_COURSE_STATUS.REJECT);
			coachRejectCourseDao.saveRejectCourse(coachId, courseId);
		}
		CoachCourseStatusResponse r = new CoachCourseStatusResponse();
		Map<String, Object> flagMap = lessonDao.getNewsFlag(coachId);
		Long courseNewsFlag = (Long)flagMap.get("courseNewsFlag");
		if(courseNewsFlag != null && courseNewsFlag.intValue() > 0){
			r.setCourseNewsFlag(1);
		} else {
			r.setCourseNewsFlag(0);
		}
		return r;
	}


	@Override
	public CourseDetailResponse getRejectCourseDetail(Long coachId,
			Long courseId) {
		CacheAction<CourseDetailResponse> cacheAction = new CourseDetailCacheAction(coachId, courseId);
		CourseDetailResponse response = cacheAction.getValue();
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
			cacheAction.setValue(response);
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
