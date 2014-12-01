package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.IMAGE_STYLE;
import com.zhiqin.coach.admin.dao.ArtifactHierarchyDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactHierarchyDaoImpl extends BaseDaoImpl implements ArtifactHierarchyDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactHierarchyDaoImpl.class);

	@Override
	public void insertFromDownload(Long artifactId, Integer taskId) {
		try{
			Map map = new HashMap();
			map.put("artifactId", artifactId);
			map.put("taskId", taskId);
			this.getSqlSession().insert("artifacthierarchy.insertFromDownload", map);
		} catch(RuntimeException e){
			log.error("insertFromDownload", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByArtifactIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("artifacthierarchy.deleteByArtifactIds", map);
		} catch(RuntimeException e){
			log.error("deleteByArtifactIds", e);
			throw e;
		}
	}
}
