package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.LessonDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.entity.SysUser;

public interface SysResourceService{

	List<com.zhiqin.coach.admin.entity.SysResource> getUserResources(int id);
	List<com.zhiqin.coach.admin.entity.SysResource> getAll();
}
