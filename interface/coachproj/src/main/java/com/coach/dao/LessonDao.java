package com.coach.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coach.common.Constants.LESSON_STATUS;
import com.coach.model.Lesson;
import com.coach.request.GetCheckLessonRequest;



public interface LessonDao {
	

	public int insert(Lesson lesson);

	public List<Lesson> getLessonInRange(Long coachId, Date startDate,
			Date endDate);

	public Map<String, Object> getNewsFlag(Long coachId);

	public Lesson getLessonDetail(Long coachId, Long lessonId);

	public List<Lesson> getDetailRecentInRange(Long coachId, Date startDate,
			Date endDate);

	public void changeCheckFlag(Long lessonId);

	public List<Map> getTotalLessonNum(Long coachId, Date startDate);

	public Double getPercent(Integer percent, Date startDate) ;

	public List<Lesson> getCheckLesson(GetCheckLessonRequest request, Date startDate);

	public List<Lesson> getLessonFrom(Long coachId, Timestamp startTime);

	public void deleteLesson(Long coachId, Long courseId);

	public void updateLessonStatus(Long lessonId, LESSON_STATUS deleted);


}
