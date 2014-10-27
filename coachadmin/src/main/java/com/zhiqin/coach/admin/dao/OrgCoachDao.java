package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;


public interface OrgCoachDao extends BaseDao{

	Long getOrgCoachByIdNumberAndType(CoachDTO coach);

	Object createCoach(CoachDTO coach);

	void updateCoach(CoachDTO coach);

	CoachDTO getOrgCoachById(Long orgCoachId);
	
	List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo);

	Long getTotalNum(Long orgId);

	List<CoachDTO> getBindCoachById(Long orgId);
}
