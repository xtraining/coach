package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;


public interface CourseDao extends BaseDao{

	List<CourseDTO> getByOrgId(SearchCourseDTO searchDto, PageInfoDTO pageInfo);

	Long getTotalNum(SearchCourseDTO searchDto);

	void insert(CourseDTO c);

	void deleteById(Long courseId);

}
