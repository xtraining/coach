package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dao.OrgDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.OrgService;
import com.zhiqin.coach.admin.util.RopUtils;

@Service
public class OrgServiceImpl implements OrgService {
	
	@Resource private OrgDao orgDao;
	@Resource private OrgCoachDao orgCoachDao;
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
		org.setCode(RopUtils.getUUID());
		org.setLevel("0000000000000000");
		orgDao.insert(org);
		
	}

	@Override
	public Long getOrgIdByName(String orgName) {
		return orgDao.getIdByName(orgName);
	}

	@Override
	public OrgDTO getOrgById(Long orgId) {
		return orgDao.getOrgById(orgId);
	}

	@Override
	public void update(OrgDTO org) {
		orgDao.update(org);
		
	}

	@Override
	public void deleteById(Long orgId) {
		orgDao.deleteById(orgId);
		
	}

	@Override
	public List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo) {
		return orgCoachDao.getCoachByOrgId(orgId, pageInfo);
	}

	@Override
	public Long getOrgCoachByIdNumberAndType(CoachDTO coach) {
		return orgCoachDao.getOrgCoachByIdNumberAndType(coach);
	}

	@Override
	public void updateCoach(CoachDTO coach) {
		orgCoachDao.updateCoach(coach);
		
	}

	@Override
	public void createCoach(CoachDTO coach) {
		orgCoachDao.createCoach(coach);
	}

	@Override
	public CoachDTO getOrgCoachById(Long orgCoachId) {
		return orgCoachDao.getOrgCoachById(orgCoachId);
	}

}
