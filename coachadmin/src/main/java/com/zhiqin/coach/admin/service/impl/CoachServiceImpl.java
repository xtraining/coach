package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.CoachCourseDao;
import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {
	
	@Resource private CoachDao coachDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Resource private OrgCoachDao orgCoachDao;
	
	@Override
	public Long getTotalNum(SearchCoachDTO coach) {
		return coachDao.getTotalNum(coach);
	}

	@Override
	public List<CoachDTO> getCoachList(SearchCoachDTO coach,
			PageInfoDTO pageInfo) {
		List<CoachDTO> list = coachDao.getCoachList(coach, pageInfo);
		return list;
		
	}

	@Override
	public Long getCourseTotalNum(SearchCourseDTO searchDto) {
		return coachCourseDao.getCourseTotalNum(searchDto);
	}

	@Override
	public List<CourseDTO> getCourseList(SearchCourseDTO searchDto,
			PageInfoDTO pageInfo) {
		return coachCourseDao.getCourseList(searchDto, pageInfo);
	}

	@Override
	public Long getUnbindCoachTotalNum(SearchOrgDTO searchDto) {
		return orgCoachDao.getUnbindCoachTotalNum(searchDto);
	}

	@Override
	public List<CoachDTO> getUnbindCoachList(SearchOrgDTO searchDto,
			PageInfoDTO pageInfo) {
		return orgCoachDao.getUnbindCoachList(searchDto, pageInfo);
	}

}
