package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;

public interface OrgService{

	Long getTotalNum(SearchOrgDTO coach);

	List<OrgDTO> getOrgList(SearchOrgDTO coach, PageInfoDTO pageInfo);

	void create(OrgDTO org);
}
