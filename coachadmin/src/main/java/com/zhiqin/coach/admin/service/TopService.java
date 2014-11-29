package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TopDTO;

public interface TopService{

	void create(TopDTO dto);

	Long getTopTotalNum(String name);

	List<TopDTO> getTopList(String name, PageInfoDTO pageInfo);

	void deleteByIds(String ids);

}
