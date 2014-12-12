package com.zhiqin.coach.admin.dao;



import java.util.Date;
import java.util.List;

import com.zhiqin.coach.admin.dto.LessonDTO;


public interface LessonDao extends BaseDao{

	void insert(LessonDTO lesson);

	List<LessonDTO> getLessonByCourseId(Long courseId);

	List<LessonDTO> getLessonFrom(Long coachId, Date startTime);

}
