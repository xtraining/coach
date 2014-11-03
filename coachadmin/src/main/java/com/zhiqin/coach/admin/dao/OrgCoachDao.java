package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;


public interface OrgCoachDao extends BaseDao{

	Long getOrgCoachByIdNumberAndType(CoachDTO coach);

	Object createCoach(CoachDTO coach);

	void updateCoach(CoachDTO coach);

	CoachDTO getOrgCoachById(Long orgCoachId);
	
	List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo);

	Long getTotalNum(Long orgId);

	List<CoachDTO> getBindCoachById(Long orgId);
	
	Long getUnbindCoachTotalNum(SearchOrgDTO searchDto);

	List<CoachDTO> getUnbindCoachList(SearchOrgDTO searchDto,
			PageInfoDTO pageInfo);
	
	void updateBindStatus(Long coachId, Long orgCoachId, Integer status);

	
}
