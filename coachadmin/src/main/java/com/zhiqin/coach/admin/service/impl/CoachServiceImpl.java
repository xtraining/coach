package com.zhiqin.coach.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {
	
	@Resource private CoachDao coachDao;
	
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

}
