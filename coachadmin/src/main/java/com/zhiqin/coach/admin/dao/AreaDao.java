package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.AreaDTO;





public interface AreaDao extends BaseDao{

	Integer getByAreaCode(String areaCode);

	List<AreaDTO> getSubareaById(int areaId);



}
