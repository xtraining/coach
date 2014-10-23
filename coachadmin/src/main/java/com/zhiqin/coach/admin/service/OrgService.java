package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;

public interface OrgService{

	Long getTotalNum(SearchOrgDTO coach);

	List<OrgDTO> getOrgList(SearchOrgDTO coach, PageInfoDTO pageInfo);

	void create(OrgDTO org);

	Long getOrgIdByName(String orgName);

	OrgDTO getOrgById(Long orgId);

	void update(OrgDTO org);

	void deleteById(Long orgId);

	List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo);

	Long getOrgCoachByIdNumberAndType(CoachDTO coach);

	void updateCoach(CoachDTO coach);

	void createCoach(CoachDTO coach);

	CoachDTO getOrgCoachById(Long orgCoachId);
}
