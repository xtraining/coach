package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dao.TagDao;
import com.zhiqin.coach.admin.dao.TagImageDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;
import com.zhiqin.coach.admin.service.ImageService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class ImageServiceImpl implements ImageService {
	private static Log log = LogFactory.getLog(ImageServiceImpl.class);
	@Resource private ImageDao imageDao;
	@Resource private TagImageDao tagImageDao;
	@Resource private TagDao tagDao;
	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		return tagImageDao.getTagImageTotalNum(searchDto);
	}
	@Override
	public List<TagImageDTO> getTagImageList(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo) {
		List<TagImageDTO> list = tagImageDao.getTagImage(searchDto, pageInfo);
		for(TagImageDTO image : list){
			if(searchDto.getTagId() != null){
				image.setTagNameList(searchDto.getTagName());
			} else {
				List<String> tagNameList = tagImageDao.getTagNameListByImageId(image.getId());
				if(tagNameList != null && tagNameList.size() > 0){
					image.setTagNameList(tagNameList.toString());
				}
			}
			image.setUrl(Config.getProperty("QINIU_DOMAIN") + image.getFileName());
		} 
		return list;
	}
	@Override
	public void createFiles(String[] tags, MultipartFile[] imageFile) throws AuthException, JSONException, IOException {
		if(imageFile != null && imageFile.length > 0){
			String uptoken = QiniuUtils.getUptoken();
			int size = imageFile.length;
			for(int i = 0; i < size; i++){
				MultipartFile file = imageFile[i];
				if(file != null && file.getSize() > 0){
					TagImageDTO image = new TagImageDTO();
					imageDao.insert(image);
					String fileName = QiniuUtils.generateTagImageName(image.getId(), file.getOriginalFilename());
					image.setFileName(fileName);
					File localFile = DownloadUtils.getFile(file.getInputStream(), fileName);
					QiniuUtils.upload(uptoken, fileName, localFile);
					imageDao.updateFileName(image);
					localFile.delete();
					
					if(StringUtils.isNotBlank(tags[i])){
						String tag[] = StringUtils.split(tags[i], ",");
						for(String name : tag){
							Long tagId = tagDao.getTagIdByName(name);
							if(tagId != null && tagId > 0){
								tagImageDao.insert(tagId, image.getId());
							} else {
								TagDTO tagDTO = new TagDTO();
								tagDTO.setName(name);
								tagDao.create(tagDTO);
								tagImageDao.insert(tagDTO.getId(), image.getId());
							}
						}
					}
				}
			}
		}
		
	}
	@Override
	public void deleteByIds(String ids) {
		imageDao.deleteByIds(ids);
		
	}
	@Override
	public List<TagDTO> getTagByImageId(long imageId) {
		return tagImageDao.getTagByImageId(imageId);
	}
	
	@Override
	@Transactional
	public void saveAssignTag(String tags, Long imageId) {
		tagImageDao.deleteByImageId(imageId);
		if(StringUtils.isNotBlank(tags)){
			String tag[] = StringUtils.split(tags, ",");
			for(String name : tag){
				Long tagId = tagDao.getTagIdByName(name);
				if(tagId != null && tagId > 0){
					tagImageDao.insert(tagId, imageId);
				} else {
					TagDTO tagDTO = new TagDTO();
					tagDTO.setName(name);
					tagDao.create(tagDTO);
					tagImageDao.insert(tagDTO.getId(), imageId);
				}
			}
		}
	}

}
