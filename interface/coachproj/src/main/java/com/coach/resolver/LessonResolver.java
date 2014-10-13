package com.coach.resolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.coach.common.Constants.COURSE_TYPE;
import com.coach.common.Constants.LESSON_MEMBER_STATUS;
import com.coach.common.Constants.LESSON_STATUS;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.dao.CoachCourseDao;
import com.coach.dao.CourseDao;
import com.coach.dao.CourseMemberDao;
import com.coach.dao.LessonDao;
import com.coach.dao.LessonMemberDao;
import com.coach.dao.MemberDao;
import com.coach.model.Lesson;
import com.coach.model.Member;
import com.coach.request.AddLessonRequest;
import com.coach.request.AddPersonalRequest;
import com.coach.request.CheckLessonMemberRequest;
import com.coach.request.GetCheckLessonRequest;
import com.coach.request.GetRecentLessonRequest;
import com.coach.response.DayLessonResponse;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonMemberResponse;
import com.coach.response.LessonResponse;
import com.coach.response.MemberResponse;
import com.coach.response.MonthLessonResponse;
import com.coach.response.TotalLessonResponse;
import com.coach.response.WeekLessonResponse;
import com.coach.utils.Config;
import com.coach.utils.DateUtils;
@Service
public class LessonResolver extends BaseResolver implements ILessonResolver{
	@Resource private CourseDao courseDao;
	@Resource private LessonDao lessonDao;
	@Resource private MemberDao memberDao;
	@Resource private CourseMemberDao courseMemberDao;
	@Resource private LessonMemberDao lessonMemberDao;
	@Resource private CoachCourseDao coachCourseDao;
	@SuppressWarnings("unchecked")
	@Override
	public WeekLessonResponse getOneWeekLesson(Integer coachId, String requestDate) {
		Date date = DateUtils.yyyyMMddToDate(requestDate);
		if(date == null){
			date = new Date();
		}
		Date startDate = DateUtils.getFirstDayOfWeek(date);
		Date endDate = DateUtils.addDay(startDate, 8);
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() + coachId + "_" + DateUtils.dateToyyyyMMdd(startDate)+ "_" + DateUtils.dateToyyyyMMdd(endDate);
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
		List<LessonResponse> lessonResponseList = (List<LessonResponse>) cachObject.getValue();
		if(lessonResponseList == null){
			List<Lesson> lessonList = lessonDao.getLessonInRange(coachId, startDate, endDate);
			lessonResponseList = new ArrayList<LessonResponse>();
			for(Lesson lesson : lessonList){
				LessonResponse r =  lesson.toResponse();
				lessonResponseList.add(r);
			}
			cache.set(CACHE_REGION.ONE_DAY.getValue(), cacheKey, lessonResponseList);
		}
		WeekLessonResponse response = new WeekLessonResponse();
		response.setLessonList(lessonResponseList);
		Map<String, Object> flagMap = lessonDao.getNewsFlag(coachId);
		String avatarUrl = (String) flagMap.get("avatarUrl");
		Long courseNewsFlag = (Long)flagMap.get("courseNewsFlag");
		Long memberNewsFlag = (Long)flagMap.get("memberNewsFlag");
		if(StringUtils.isNotBlank(avatarUrl)){
			if(avatarUrl.trim().startsWith("http")){
				response.setAvatarUrl(avatarUrl);
			} else {
				response.setAvatarUrl(Config.getProperty("QINIU_DOMAIN") + avatarUrl);
			}
		} else {
			response.setAvatarUrl("");
		}
		if(courseNewsFlag != null && courseNewsFlag.intValue() > 0){
			response.setCourseNewsFlag(1);
		} else {
			response.setCourseNewsFlag(0);
		}
		if(memberNewsFlag != null && memberNewsFlag.intValue() > 0){
			response.setMemberNewsFlag(1);
		} else {
			response.setMemberNewsFlag(0);
		}
		return response;
	}
	@Override
	public LessonDetailResponse getLessonDetail(Integer coachId, Long lessonId) {
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_LESSON.getValue() + coachId + "_" + lessonId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
		LessonDetailResponse response = (LessonDetailResponse)cachObject.getValue();
		if(response != null){
			return response;
		} else {
			Lesson lesson = lessonDao.getLessonDetail(coachId, lessonId);
			response = lesson.toDetailResponse();
			cache.set(CACHE_REGION.ONE_DAY.getValue(), cacheKey, response);
			return response;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LessonDetailResponse> getRecentLessonDetail(
			GetRecentLessonRequest request) {
		Date startDate = DateUtils.yyyyMMddToDate(request.getStartDate());
		if(startDate == null){
			startDate = new Date();
		}
		int days = request.getDays() >= 10 ? 10 : request.getDays();
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_RECENT_LESSON.getValue() + request.getCoachId() + "_" + DateUtils.dateToyyyyMMdd(startDate) + "_" + days;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
		List<LessonDetailResponse> response = (List<LessonDetailResponse>)cachObject.getValue();
		if(response != null){
			return response;
		} else {
			Date endDate = DateUtils.addDay(startDate, days + 1);
			List<Lesson> list = lessonDao.getDetailRecentInRange(request.getCoachId(), startDate, endDate);
			response = new ArrayList<LessonDetailResponse>();
			for(Lesson lesson : list){
				LessonDetailResponse r = lesson.toDetailResponse();
				response.add(r);
			}
			cache.set(CACHE_REGION.ONE_DAY.getValue(), cacheKey, response);
			return response;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberResponse> getLessonMemberList(Integer coachId,
			Long lessonId) {
		String cacheKey = ONE_HOUR_CACHE_KEY.COACH_LESSON_MEMBER_LIST.getValue() + "_" + coachId + "_" + lessonId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_HOUR.getValue(), cacheKey);
		List<MemberResponse> response = (List<MemberResponse>)cachObject.getValue();
		if(response != null){
			return response;
		} else {
			response = new ArrayList<MemberResponse>();
			List<Member> list = lessonMemberDao.getLessonMember(lessonId);
			for(Member m :list){
				MemberResponse r = m.toResponse();
				response.add(r);
			}
			cache.set(CACHE_REGION.ONE_HOUR.getValue(), cacheKey, response);
			return response;
		}
	}
	
	@Override
	public LessonMemberResponse getLessonMember(Integer coachId, Long lessonId) {
		String cacheKey = ONE_HOUR_CACHE_KEY.COACH_LESSON_MEMBER.getValue() + "_" + coachId + "_" + lessonId;
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_HOUR.getValue(), cacheKey);
		LessonMemberResponse response = (LessonMemberResponse)cachObject.getValue();
		if(response != null){
			return response;
		} else {
			List<Member> list = lessonMemberDao.getLessonMember(lessonId);
			response = new LessonMemberResponse();
			List<MemberResponse> mList = new ArrayList<MemberResponse>();
			int attendNum = 0;
			int absentNum = 0;
			for(Member m :list){
				if(mList.size() == 0){
					response.setGroundName(m.getGroundName());
					response.setStartTime(DateUtils.dateToyyyyMMddHHmiss(m.getStartTime()));
				}
				MemberResponse r = m.toResponse();
				mList.add(r);
				if(m.getStatus() == 0){
					absentNum++;
				}else {
					attendNum++;
				}
			}
			response.setAbsentNum(absentNum);
			response.setAttendNum(attendNum);
			response.setTotalNum(list.size());
			response.setMemberList(mList);
			response.setLessonId(lessonId);
			cache.set(CACHE_REGION.ONE_HOUR.getValue(), cacheKey, response);
			return response;
		}
	}
	
	@Override
	@Transactional
	public void saveCheckMember(CheckLessonMemberRequest request) {
		String[]attendMemberIds = StringUtils.split(request.getAttendMemberId(), ",");
		if(attendMemberIds != null && attendMemberIds.length > 0){
			lessonMemberDao.updateStatus(request.getLessonId(), attendMemberIds, LESSON_MEMBER_STATUS.CHECK);
		}
		
		String[]absentMemberIds = StringUtils.split(request.getAbsentMemberId(), ",");
		if(absentMemberIds != null && absentMemberIds.length > 0){
			lessonMemberDao.updateStatus(request.getLessonId(), absentMemberIds, LESSON_MEMBER_STATUS.MISS);
		}
		lessonDao.changeCheckFlag(request.getLessonId());
		String cacheKey = ONE_HOUR_CACHE_KEY.COACH_LESSON_MEMBER.getValue() + "_" + request.getCoachId() + "_" + request.getLessonId();
		cache.evict(CACHE_REGION.ONE_HOUR.getValue(), cacheKey);
	}
	
	@Override
	public void addPersonal(AddPersonalRequest request) {
		Lesson lesson = request.toLesson();
		lessonDao.insert(lesson);
		Date startDate = DateUtils.getFirstDayOfWeek(DateUtils.yyyyMMddHHmmssToTimestamp(request.getStartTime()));
		Date endDate = DateUtils.addDay(startDate, 8);
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() + request.getCoachId() + "_" + DateUtils.dateToyyyyMMdd(startDate)+ "_" + DateUtils.dateToyyyyMMdd(endDate);
		cache.evict(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
	}
	
	@Override
	public void addLesson(AddLessonRequest request) {
		Lesson lesson = request.toLesson();
		lessonDao.insert(lesson);
		Date startDate = DateUtils.getFirstDayOfWeek(DateUtils.yyyyMMddHHmmssToTimestamp(request.getStartTime()));
		Date endDate = DateUtils.addDay(startDate, 8);
		String cacheKey = ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() + request.getCoachId() + "_" + DateUtils.dateToyyyyMMdd(startDate)+ "_" + DateUtils.dateToyyyyMMdd(endDate);
		cache.evict(CACHE_REGION.ONE_DAY.getValue(), cacheKey);
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public TotalLessonResponse getTotalLesson(Integer coachId) {
		Date startDate = DateUtils.getFirstDayOfMonth(new Date(), -3);
		TotalLessonResponse response = new TotalLessonResponse();
		Map<String, MonthLessonResponse> map = new HashMap<String, MonthLessonResponse>();
		List<Map> list = lessonDao.getTotalLessonNum(coachId, startDate);
		if(list != null && list.size() >0){
			for(Map record : list){
				String month = (String)record.get("monthStr");
				Integer type = (Integer)record.get("type");
				Double num = (Double)record.get("num");
				MonthLessonResponse r = null;
				if(map.containsKey(month)){
					r = map.get(month);
				} else {
					r = new MonthLessonResponse();
					map.put(month, r);
				}
				r.setMonth(month);
				if(type.intValue() == COURSE_TYPE.ORG.getValue()){
					r.setOrgHours(num.intValue());
				} else {
					r.setPersonalHours(num.intValue());
				}
			}
		}
		List<String> monthList = getLatest4Month();
		for(String month : monthList){
			if(!map.containsKey(month)){
				MonthLessonResponse r = new MonthLessonResponse();
				r.setMonth(month);
				map.put(month, r);
			}
		}
		List<MonthLessonResponse> monthLessonList = new ArrayList<MonthLessonResponse>();
		Iterator<String> it = map.keySet().iterator();  
		while (it.hasNext()) {  
			String key = it.next();  
			MonthLessonResponse r = map.get(key);
			monthLessonList.add(r);  
			response.setTotalHours(response.getTotalHours() + r.getOrgHours() + r.getPersonalHours());
		}  
		Collections.sort(monthLessonList, new Comparator<MonthLessonResponse>() {
			@Override
			public int compare(MonthLessonResponse o1, MonthLessonResponse o2) {
				if(o1.getMonth() != null){
					return o1.getMonth().compareTo(o2.getMonth());
				} else {
					return 0;
				}
			}
		});
		
		response.setMonthLessonList(monthLessonList);
		Double percentValue = lessonDao.getPercent(response.getTotalHours(), startDate) * 100;
		percentValue = percentValue < 1 ? 1 : percentValue;
		response.setPercent(100 - percentValue.intValue()); 
		/*List<SysParameter> list = systemParameterResolver.getByType(SYS_PARAMETER_TYPE.COACH_WORDING), "desc");
		for(SysParameter sys : list){
			if(percentValue > sys.getOrder()){
				response.setWording(sys.getValue());
			}
		}*/
		return response;
	}
	private List<String> getLatest4Month() {
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 4; i++){
			Date month = DateUtils.getFirstDayOfMonth(new Date(), 0-i);
			list.add(DateUtils.dateToyyyyMM(month));
		}
		return list;
	}
	@Override
	public List<LessonResponse> getCheckLesson(GetCheckLessonRequest request,
			Date startDate) {
		List<Lesson> list = lessonDao.getCheckLesson(request, startDate);
		List<LessonResponse> response = new ArrayList<LessonResponse>();
		for(Lesson s : list){
			LessonResponse lr = new LessonResponse();
			lr.setLessonId(s.getId());
			lr.setName(s.getName());
			lr.setHours(s.getHours());
			lr.setStartTime(DateUtils.dateToyyyyMMddHHmiss(s.getStartTime()));
			response.add(lr);
		}
		return response;
	}
	/*public List<DayLessonResponse> getCheckLesson(GetCheckLessonRequest request,
			Date startDate) {
		Integer coachId = request.getCoachId();
		String cacheKey = ONE_HOUR_CACHE_KEY.COACH_CHECK_LESSON.getValue() + "_" + coachId + "_" + DateUtils.dateToyyyyMMdd(startDate);
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_HOUR.getValue(), cacheKey);
		List<DayLessonResponse> response = (List<DayLessonResponse>)cachObject.getValue();
		if(response != null){
			return response;
		} else {
			List<Lesson> list = lessonDao.getCheckLesson(request, startDate);
			Map<String, DayLessonResponse> map = new HashMap<String, DayLessonResponse>();
			for(Lesson s : list){
				String date = DateUtils.dateToyyyyMMdd(s.getStartTime());
				DayLessonResponse r = null;
				if(map.containsKey(date)){
					r = map.get(date);
				} else {
					r = new DayLessonResponse();
					r.setDate(date);
					r.setDay(DateUtils.getDayOfWeek(s.getStartTime()));
					map.put(date, r);
				}
				r.setHours(r.getHours() + s.getHours());
				LessonResponse lr = new LessonResponse();
				lr.setLessonId(s.getId());
				lr.setName(s.getName());
				lr.setHours(s.getHours());
				r.getLessonList().add(lr);
			}
			
			response = new ArrayList<DayLessonResponse>();
			Iterator<String> it = map.keySet().iterator();  
			while (it.hasNext()) {  
				String key = it.next();  
				DayLessonResponse r = map.get(key);
				response.add(r);  
			}  
			Collections.sort(response, new Comparator<DayLessonResponse>() {
				@Override
				public int compare(DayLessonResponse o1, DayLessonResponse o2) {
					if(o2.getDate() != null){
						return o2.getDate().compareTo(o1.getDate());
					} else {
						return 0;
					}
				}
			});
			cache.set(CACHE_REGION.ONE_HOUR.getValue(), cacheKey, response);
		}
		return response;
	}*/
	@Override
	public void deleteLesson(Long lessonId) {
		lessonDao.updateLessonStatus(lessonId, LESSON_STATUS.DELETED);
		
	}

	
 
 
	
}
