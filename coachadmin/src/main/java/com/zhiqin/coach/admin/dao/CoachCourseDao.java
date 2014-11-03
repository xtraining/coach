package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;


public interface CoachCourseDao extends BaseDao{

	void insert(Long coachId, Long courseId);

	void deleteByCourseId(Long courseId);

	List<Long> getAcceptedCoachByCourseId(Long courseId);

	Long getCourseTotalNum(SearchCourseDTO searchDto);

	List<CourseDTO> getCourseList(SearchCourseDTO searchDto, PageInfoDTO pageInfo);

	


}
