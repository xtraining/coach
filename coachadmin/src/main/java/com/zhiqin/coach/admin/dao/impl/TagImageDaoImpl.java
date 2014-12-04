package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.TagImageDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TagImageDaoImpl extends BaseDaoImpl implements TagImageDao
{
	private static final Logger log = LoggerFactory
			.getLogger(TagImageDaoImpl.class);

	@Override
	public void insert(Long tagId, Long imageId) {
		try{
			Map map = new HashMap();
			map.put("tagId", tagId);
			map.put("imageId", imageId);
			this.getSqlSession().insert("tagimage.insert", map);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}

	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("tagimage.getTagImageTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTagImageTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<TagImageDTO> getTagImage(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("pageInfo", pageInfo);
			return this.getSqlSession().selectList("tagimage.getTagImage", map);
		} catch(RuntimeException e){
			log.error("getTagImage", e);
			throw e;
		}
	}

	@Override
	public List<String> getTagNameListByImageId(Long imageId) {
		try{
			return this.getSqlSession().selectList("tagimage.getTagNameListByImageId", imageId);
		} catch(RuntimeException e){
			log.error("getTagNameListByImageId", e);
			throw e;
		}
	}

	@Override
	public List<TagDTO> getTagByImageId(long imageId) {
		try{
			return this.getSqlSession().selectList("tagimage.getTagByImageId", imageId);
		} catch(RuntimeException e){
			log.error("getTagByImageId", e);
			throw e;
		}
	}

	@Override
	public void deleteByImageId(Long imageId) {
		try{
			Map map = new HashMap();
			map.put("imageId", imageId);
			this.getSqlSession().delete("tagimage.deleteByImageId", map);
		} catch(RuntimeException e){
			log.error("getTagByImageId", e);
			throw e;
		}
		
	}

}
