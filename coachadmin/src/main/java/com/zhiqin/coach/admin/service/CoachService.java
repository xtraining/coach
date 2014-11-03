package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;

public interface CoachService{

	Long getTotalNum(SearchCoachDTO coach);

	List<CoachDTO> getCoachList(SearchCoachDTO coach, PageInfoDTO pageInfo);

	Long getCourseTotalNum(SearchCourseDTO searchDto);

	List<CourseDTO> getCourseList(SearchCourseDTO searchDto, PageInfoDTO pageInfo);

	Long getUnbindCoachTotalNum(SearchOrgDTO searchDto);

	List<CoachDTO> getUnbindCoachList(SearchOrgDTO searchDto,
			PageInfoDTO pageInfo);
}
