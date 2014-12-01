package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_STYLE;
import com.zhiqin.coach.admin.dao.ArtifactImageDao;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactImageDaoImpl extends BaseDaoImpl implements ArtifactImageDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactImageDaoImpl.class);

	@Override
	public void insert(Long artifactId, Long imageId, IMAGE_FROM imageFrom, IMAGE_STYLE imageStyle) {
		try{
			Map map = new HashMap();
			map.put("objectId", artifactId);
			map.put("imageId", imageId);
			map.put("imageFrom", imageFrom.getValue());
			map.put("imageStyle", imageStyle.getValue());
			this.getSqlSession().insert("artifactimage.insert", map);
		} catch(RuntimeException e){
			log.error("insertFromDownload", e);
			throw e;
		}
		
	}

	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("artifactimage.getTagImageTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTagImageTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<ArtifactImageDTO> getTagImage(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("pageInfo", pageInfo);
			return this.getSqlSession().selectList("artifactimage.getTagImage", map);
		} catch(RuntimeException e){
			log.error("getTagImage", e);
			throw e;
		}
	}

	@Override
	public List<String> getTagNameListByImageId(Long imageId) {
		try{
			return this.getSqlSession().selectList("artifactimage.getTagNameListByImageId", imageId);
		} catch(RuntimeException e){
			log.error("getTagNameListByImageId", e);
			throw e;
		}
	}

	@Override
	public List<TagDTO> getTagByImageId(long imageId) {
		try{
			return this.getSqlSession().selectList("artifactimage.getTagByImageId", imageId);
		} catch(RuntimeException e){
			log.error("getTagByImageId", e);
			throw e;
		}
	}

	@Override
	public void deleteByImageId(Long imageId, IMAGE_FROM imageFrom) {
		try{
			Map map = new HashMap();
			map.put("imageId", imageId);
			map.put("imageFrom", imageFrom.getValue());
			this.getSqlSession().delete("artifactimage.deleteByImageId", map);
		} catch(RuntimeException e){
			log.error("getTagByImageId", e);
			throw e;
		}
		
	}

	@Override
	public List<String> getArtifactImageByObjectId(Long objectId, IMAGE_FROM imageFrom, IMAGE_STYLE imageStyle) {
		try{
			Map map = new HashMap();
			map.put("objectId", objectId);
			map.put("imageFrom", imageFrom.getValue());
			map.put("imageStyle", imageStyle.getValue());
			return this.getSqlSession().selectList("artifactimage.getArtifactImageByAritifactId", map);
		} catch(RuntimeException e){
			log.error("getArtifactImageByAritifactId", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByArtifactId(Long artifactId, IMAGE_FROM imageFrom,
			IMAGE_STYLE imageStyle) {
		try{
			Map map = new HashMap();
			map.put("objectId", artifactId);
			map.put("imageFrom", imageFrom.getValue());
			map.put("imageStyle", imageStyle.getValue());
			this.getSqlSession().delete("artifactimage.deleteByArtifactId", map);
		} catch(RuntimeException e){
			log.error("deleteByArtifactId", e);
			throw e;
		}
		
	}
}
