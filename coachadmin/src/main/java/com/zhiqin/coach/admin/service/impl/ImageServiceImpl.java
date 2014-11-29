package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;
import com.zhiqin.coach.admin.service.ImageService;
import com.zhiqin.coach.admin.util.Config;

@Service
public class ImageServiceImpl implements ImageService {
	private static Log log = LogFactory.getLog(ImageServiceImpl.class);
	@Resource private ImageDao imageDao;
	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		return imageDao.getTagImageTotalNum(searchDto);
	}
	@Override
	public List<TagImageDTO> getTagImageList(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo) {
		List<TagImageDTO> list = imageDao.getTagImage(searchDto, pageInfo);
		for(TagImageDTO image : list){
			if(searchDto.getTagId() != null){
				image.setTagNameList(searchDto.getTagName());
			} else {
				List<String> tagNameList = imageDao.getTagNameListByImageId(image.getId());
				if(tagNameList != null && tagNameList.size() > 0){
					image.setTagNameList(tagNameList.toString());
				}
			}
			image.setUrl(Config.getProperty("QINIU_DOMAIN") + image.getFileName());
		} 
		return list;
	}

}
