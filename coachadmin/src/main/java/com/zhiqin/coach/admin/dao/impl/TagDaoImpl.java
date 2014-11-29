package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dao.TagDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TagDaoImpl extends BaseDaoImpl implements TagDao
{
	private static final Logger log = LoggerFactory
			.getLogger(TagDaoImpl.class);

	@Override
	public Long getTagTotalNum(String name) {
		try{
			return  this.getSqlSession().selectOne("tag.getTagTotalNum");
		} catch(RuntimeException e){
			log.error("getTagTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<TagDTO> getTagList(String name, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("status", TASK_STATUS.ACTIVE.getValue());
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("tag.getTagList", map);
		} catch(RuntimeException e){
			log.error("getTagList", e);
			throw e;
		}
	}

	@Override
	public void create(TagDTO dto) {
		try{
			this.getSqlSession().insert("tag.insertTag", dto);
		} catch(RuntimeException e){
			log.error("insertTag", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("tag.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
	}

	@Override
	public TagDTO getById(int tagId) {
		try{
			return  this.getSqlSession().selectOne("tag.getById", tagId);
		} catch(RuntimeException e){
			log.error("getById", e);
			throw e;
		}
	}

	@Override
	public void update(TagDTO dto) {
		try{
			 this.getSqlSession().update("tag.update", dto);
		} catch(RuntimeException e){
			log.error("update", e);
			throw e;
		}
		
	}
}
