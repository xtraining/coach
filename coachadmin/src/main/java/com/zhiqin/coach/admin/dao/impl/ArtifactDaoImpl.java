package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactDaoImpl extends BaseDaoImpl implements ArtifactDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactDaoImpl.class);

	@Override
	public Long getArtifactTotalNum(SearchArtifactDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("artifact.getArtifactTotalNum", map);
		} catch(RuntimeException e){
			log.error("getArtifactTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("artifact.getArtifactList", map);
		} catch(RuntimeException e){
			log.error("getArtifactList", e);
			throw e;
		}
	}

	@Override
	public void insert(ArtifactDTO dto) {
		try{
			this.getSqlSession().insert("artifact.insert", dto);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}

	@Override
	public void insertFromDownload(ArtifactDTO story) {
		try{
			this.getSqlSession().insert("artifact.insertFromDownload", story);
		} catch(RuntimeException e){
			log.error("insertFromDownload", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("artifact.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
		
	}
}
