package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ImageDaoImpl extends BaseDaoImpl implements ImageDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ImageDaoImpl.class);

	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("image.getTagImageTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTagImageTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<TagImageDTO> getTagImage(SearchTagImageDTO searchDto, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("p", pageInfo);
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectList("image.getTagImageList", map);
		} catch(RuntimeException e){
			log.error("getTagImageList", e);
			throw e;
		}
	}

	@Override
	public List<String> getTagNameListByImageId(Long imageId) {
		try{
			return this.getSqlSession().selectList("image.getTagNameListByImageId", imageId);
		} catch(RuntimeException e){
			log.error("getTagNameListByImageId", e);
			throw e;
		}
	}

}
