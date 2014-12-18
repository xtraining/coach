package com.zhiqin.coach.admin.service;


import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;

public interface CategoryService{

	Long getCategoryTotalNum(String name);

	List<CategoryDTO> getCategoryList(String name, PageInfoDTO pageInfo);

	void create(CategoryDTO dto, MultipartFile imageFile) throws IOException, AuthException, JSONException;

	void deleteByIds(String ids);

	CategoryDTO getById(int categoryId);

	void update(CategoryDTO dto, MultipartFile imageFile) throws IOException, AuthException, JSONException;

	void saveOrder(long categoryId, long itemId, int categoryOrder);


}
