package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_STYLE;
import com.zhiqin.coach.admin.dao.ArtifactImageDao;
import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.ImageService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class ImageServiceImpl implements ImageService {
	private static Log log = LogFactory.getLog(ImageServiceImpl.class);
	@Resource private ImageDao imageDao;
	@Resource private ArtifactImageDao artifactImageDao;
	@Override
	public Long getTagImageTotalNum(SearchTagImageDTO searchDto) {
		return artifactImageDao.getTagImageTotalNum(searchDto);
	}
	@Override
	public List<ArtifactImageDTO> getTagImageList(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo) {
		List<ArtifactImageDTO> list = artifactImageDao.getTagImage(searchDto, pageInfo);
		for(ArtifactImageDTO image : list){
			if(searchDto.getTagId() != null){
				image.setTagNameList(searchDto.getTagName());
			} else {
				List<String> tagNameList = artifactImageDao.getTagNameListByImageId(image.getId());
				if(tagNameList != null && tagNameList.size() > 0){
					image.setTagNameList(tagNameList.toString());
				}
			}
			image.setUrl(Config.getProperty("QINIU_DOMAIN") + image.getFileName());
		} 
		return list;
	}
	@Override
	public void createFiles(TagArrayDTO items, MultipartFile[] imageFile) throws AuthException, JSONException, IOException {
		if(imageFile != null && imageFile.length > 0){
			String uptoken = QiniuUtils.getUptoken();
			for(MultipartFile file : imageFile){
				if(file != null && file.getSize() > 0){
					ArtifactImageDTO image = new ArtifactImageDTO();
					image.setTagImage(1);
					imageDao.insert(image);
					String fileName = QiniuUtils.generateTagImageName(image.getId(), file.getOriginalFilename());
					image.setFileName(fileName);
					File localFile = DownloadUtils.getFile(file.getInputStream(), fileName);
					QiniuUtils.upload(uptoken, fileName, localFile);
					imageDao.updateFileName(image);
					localFile.delete();
					
					TagDTO[] tags = items.getTag();
					for(TagDTO tag : tags){
						artifactImageDao.insert(tag.getId(), image.getId(), IMAGE_FROM.TAG, IMAGE_STYLE.DETAIL);
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
		return artifactImageDao.getTagByImageId(imageId);
	}
	
	@Override
	@Transactional
	public void saveAssignTag(TagArrayDTO items, Long imageId) {
		artifactImageDao.deleteByImageId(imageId, IMAGE_FROM.TAG);
		TagDTO[] tags = items.getTag();
		Set<Long> tagIdSet = new HashSet<Long>();
		for(TagDTO tag : tags){
			if(!tagIdSet.contains(tag.getId())){
				artifactImageDao.insert(tag.getId(), imageId, IMAGE_FROM.TAG, IMAGE_STYLE.DETAIL);
				tagIdSet.add(tag.getId());
			}
		}
		
	}

}
