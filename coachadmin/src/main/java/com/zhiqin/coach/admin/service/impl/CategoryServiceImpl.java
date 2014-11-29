package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.dao.CategoryDao;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private static Log log = LogFactory.getLog(CategoryServiceImpl.class);
	@Resource private CategoryDao categoryDao;
	@Override
	public Long getCategoryTotalNum(String name) {
		return categoryDao.getCategoryTotalNum(name);
	}
	@Override
	public List<CategoryDTO> getCategoryList(String name, PageInfoDTO pageInfo) {
		return categoryDao.getCategoryList(name, pageInfo);
	}
	@Override
	@Transactional
	public void create(CategoryDTO dto) {
		categoryDao.create(dto);
		
	}
	@Override
	public void deleteByIds(String ids) {
		categoryDao.deleteByIds(ids);
		
	}
	@Override
	public CategoryDTO getById(int categoryId) {
		return categoryDao.getById(categoryId);
	}
	@Override
	public void update(CategoryDTO dto) {
		categoryDao.update(dto);
		
	}
	

}
