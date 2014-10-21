package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.OrgDao;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService {
	
	@Resource private OrgDao orgDao;
	
	@Override
	public Long getTotalNum(SearchOrgDTO coach) {
		return orgDao.getTotalNum(coach);
	}

	@Override
	public List<OrgDTO> getOrgList(SearchOrgDTO coach,
			PageInfoDTO pageInfo) {
		List<OrgDTO> list = orgDao.getOrgList(coach, pageInfo);
		return list;
		
	}

	@Override
	public void create(OrgDTO org) {
		orgDao.insert(org);
		
	}

}
