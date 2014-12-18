package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactCategoryDaoImpl extends BaseDaoImpl implements ArtifactCategoryDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactCategoryDaoImpl.class);


	@Override
	public void save(ArtifactDTO artifact) {
		try{
			this.getSqlSession().insert("artifactcategory.insert", artifact);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}


	@Override
	public void delete(int categoryId, String artifactIds) {
		try{
			Map map = new HashMap();
			map.put("artifactIds", artifactIds);
			map.put("categoryId", categoryId);
			this.getSqlSession().insert("artifactcategory.delete", map);
		} catch(RuntimeException e){
			log.error("delete", e);
			throw e;
		}
		
	}


	@Override
	public List<CategoryDTO> getByArtifactId(long artifactId) {
		try{
			Map map = new HashMap();
			map.put("artifactId", artifactId);
			return this.getSqlSession().selectList("artifactcategory.getByArtifactId", map);
		} catch(RuntimeException e){
			log.error("getByArtifactId", e);
			throw e;
		}
	}
	
	@Override
	public void deleteByArtifactId(Long artifactId) {
		try{
			this.getSqlSession().delete("artifactcategory.deleteByArtifactId", artifactId);
		} catch(RuntimeException e){
			log.error("deleteByArtifactId", e);
			throw e;
		}
	}


	@Override
	public void delete(String artifactIds) {
		try{
			Map map = new HashMap();
			map.put("artifactIds", artifactIds);
			this.getSqlSession().insert("artifactcategory.deleteCategory", map);
		} catch(RuntimeException e){
			log.error("deleteCategory", e);
			throw e;
		}
		
	}
}
