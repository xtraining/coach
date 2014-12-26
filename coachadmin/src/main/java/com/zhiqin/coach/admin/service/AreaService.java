package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.AreaDTO;

public interface AreaService{

	Integer getByAreaCode(String areaCode);

	List<AreaDTO> getSubareaById(int areaId);


}
