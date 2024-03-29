package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;


public interface OrgDao extends BaseDao{

	Long getTotalNum(SearchOrgDTO coach);

	List<OrgDTO> getOrgList(SearchOrgDTO coach, PageInfoDTO pageInfo);

	void insert(OrgDTO org);

	Long getIdByName(String orgName);

	OrgDTO getOrgById(Long orgId);

	void update(OrgDTO org);

	void deleteById(Long orgId);

}
