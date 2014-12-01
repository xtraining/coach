package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactCategoryDaoImpl extends BaseDaoImpl implements ArtifactCategoryDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactCategoryDaoImpl.class);


	@Override
	public void save(Long artifactId, Long categoryId, int artifactCategoryOrder) {
		try{
			Map map = new HashMap();
			map.put("artifactId", artifactId);
			map.put("categoryId", categoryId);
			map.put("artifactCategoryOrder", artifactCategoryOrder);
			this.getSqlSession().insert("artifactcategory.insert", map);
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
			map.put("tagId", categoryId);
			this.getSqlSession().insert("artifactcategory.delete", map);
		} catch(RuntimeException e){
			log.error("delete", e);
			throw e;
		}
		
	}
}
