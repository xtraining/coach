package com.coach.resolver;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Constants.NEWS_TYPE;
import com.coach.dao.CoachCourseDao;
import com.coach.dao.CourseMemberDao;
import com.coach.dao.LessonDao;
import com.coach.dao.LessonMemberDao;
import com.coach.dao.MemberDao;
import com.coach.dao.NewsHistoryDao;
import com.coach.model.CoachNewsHistory;
@Service
public class NewsHistoryResolver extends BaseResolver implements INewsHistoryResolver{
	@Resource private NewsHistoryDao newsHistoryDao;
	@Resource private LessonDao lessonDao;
	@Resource private MemberDao memberDao;
	@Resource private CourseMemberDao courseMemberDao;
	@Resource private LessonMemberDao lessonMemberDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Override
	@Transactional
	public void updateHistory(NEWS_TYPE type, Long coachId) {
		newsHistoryDao.delete(type, coachId);
		CoachNewsHistory h = new CoachNewsHistory();
		h.setCoachId(coachId);
		h.setType(type.getValue());
		newsHistoryDao.insert(h);
	}
 
	
}
