package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dao.CategoryDao;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao
{
	private static final Logger log = LoggerFactory
			.getLogger(CategoryDaoImpl.class);

	@Override
	public Long getCategoryTotalNum(String name) {
		try{
			return  this.getSqlSession().selectOne("category.getCategoryTotalNum");
		} catch(RuntimeException e){
			log.error("getCategoryTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<CategoryDTO> getCategoryList(String name, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("name", name);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("category.getCategoryList", map);
		} catch(RuntimeException e){
			log.error("getCategoryList", e);
			throw e;
		}
	}

	@Override
	public void create(CategoryDTO dto) {
		try{
			this.getSqlSession().insert("category.insertCategory", dto);
		} catch(RuntimeException e){
			log.error("insertCategory", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("category.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
	}

	@Override
	public CategoryDTO getById(int categoryId) {
		try{
			return  this.getSqlSession().selectOne("category.getById", categoryId);
		} catch(RuntimeException e){
			log.error("getById", e);
			throw e;
		}
		
	}

	@Override
	public void update(CategoryDTO dto) {
		try{
			 this.getSqlSession().update("category.update", dto);
		} catch(RuntimeException e){
			log.error("update", e);
			throw e;
		}
		
	}

	@Override
	public void updateImageName(CategoryDTO dto) {
		try{
			 this.getSqlSession().update("category.updateImageName", dto);
		} catch(RuntimeException e){
			log.error("updateImageName", e);
			throw e;
		}
		
		
	}
}
