package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;

public interface CoachService{

	Long getTotalNum(SearchCoachDTO coach);

	List<CoachDTO> getCoachList(SearchCoachDTO coach, PageInfoDTO pageInfo);
}
