package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;


public interface CoachDao extends BaseDao{

	Long getTotalNum(SearchCoachDTO coach);

	List<CoachDTO> getCoachList(SearchCoachDTO coach, PageInfoDTO pageInfo);
}
