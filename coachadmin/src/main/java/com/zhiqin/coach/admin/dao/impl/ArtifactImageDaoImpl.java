package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_TYPE;
import com.zhiqin.coach.admin.dao.ArtifactImageDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ArtifactImageDaoImpl extends BaseDaoImpl implements ArtifactImageDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactImageDaoImpl.class);

	@Override
	public void insertFromDownload(Long objectId, String fileName, IMAGE_FROM soureFrom, IMAGE_TYPE detail) {
		try{
			Map map = new HashMap();
			map.put("objectId", objectId);
			map.put("imageName", fileName);
			map.put("imageType", detail.getValue());
			map.put("type", soureFrom.getValue());
			this.getSqlSession().insert("artifactimage.insertFromDownload", map);
		} catch(RuntimeException e){
			log.error("insertFromDownload", e);
			throw e;
		}
	}
}
