package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TopDTO;


public interface TopDao extends BaseDao{

	void save(TopDTO dto);

	Long getTopTotalNum(String name);

	List<TopDTO> getTopList(String name, PageInfoDTO pageInfo);

	void deleteByIds(String ids);

	void deleteVersionByIds(String ids);



}
