package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;

public interface CategoryService{

	Long getCategoryTotalNum(String name);

	List<CategoryDTO> getCategoryList(String name, PageInfoDTO pageInfo);

	void create(CategoryDTO dto);

	void deleteByIds(String ids);

	CategoryDTO getById(int categoryId);

	void update(CategoryDTO dto);


}
