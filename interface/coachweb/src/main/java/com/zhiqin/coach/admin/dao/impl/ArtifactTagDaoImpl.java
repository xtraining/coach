package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactTagDaoImpl extends BaseDaoImpl implements ArtifactTagDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactTagDaoImpl.class);


	@Override
	public void save(Long artifactId, Long tagId, int artifactTagOrder) {
		try{
			Map map = new HashMap();
			map.put("artifactId", artifactId);
			map.put("tagId", tagId);
			map.put("artifactTagOrder", artifactTagOrder);
			this.getSqlSession().insert("artifacttag.insert", map);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}


	@Override
	public void delete(int tagId, String artifactIds) {
		try{
			Map map = new HashMap();
			map.put("artifactIds", artifactIds);
			map.put("tagId", tagId);
			this.getSqlSession().insert("artifacttag.delete", map);
		} catch(RuntimeException e){
			log.error("delete", e);
			throw e;
		}
		
	}


	@Override
	public List<TagDTO> getByArtifactId(long artifactId) {
		try{
			Map map = new HashMap();
			map.put("artifactId", artifactId);
			return this.getSqlSession().selectList("artifacttag.getByArtifactId", map);
		} catch(RuntimeException e){
			log.error("getByArtifactId", e);
			throw e;
		}
	}


	@Override
	public void deleteByArtifactId(Long artifactId) {
		try{
			this.getSqlSession().delete("artifacttag.deleteByArtifactId", artifactId);
		} catch(RuntimeException e){
			log.error("deleteByArtifactId", e);
			throw e;
		}
	}
}
