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

public interface SysUserService{

	SysUser getUserByCredentials(String username, String password);

	SysUser getUserByUsername(String username);

}
