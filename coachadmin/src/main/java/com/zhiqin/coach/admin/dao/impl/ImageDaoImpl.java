package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ImageDaoImpl extends BaseDaoImpl implements ImageDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ImageDaoImpl.class);


	@Override
	public void insert(ArtifactImageDTO image) {
		try{
			this.getSqlSession().insert("image.insert", image);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}

	@Override
	public void updateFileName(ArtifactImageDTO image) {
		try{
			this.getSqlSession().update("image.updateFileName", image);
		} catch(RuntimeException e){
			log.error("updateFileName", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().insert("image.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
		
	}
}
