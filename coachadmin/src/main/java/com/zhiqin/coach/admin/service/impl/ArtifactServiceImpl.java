package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_TYPE;
import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_STYLE;
import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dao.ArtifactHierarchyDao;
import com.zhiqin.coach.admin.dao.ArtifactImageDao;
import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dao.ImageDao;
import com.zhiqin.coach.admin.dao.impl.ArtifactImageDaoImpl;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.ArtifactService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class ArtifactServiceImpl implements ArtifactService {
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactImageDaoImpl.class);
	
	
	@Resource private ArtifactDao artifactDao;
	@Resource private ArtifactImageDao artifactImageDao;
	@Resource private ArtifactHierarchyDao artifactHierarchyDao;
	@Resource private ArtifactTagDao artifactTagDao;
	@Resource private ImageDao imageDao;
	@Resource private ArtifactCategoryDao artifactCategoryDao;
	@Override
	public Long getArtifactTotalNum(SearchArtifactDTO searchDto) {
		return artifactDao.getArtifactTotalNum(searchDto);
	}

	@Override
	public List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo) {
		 List<ArtifactDTO> list = artifactDao.getArtifactList(searchDto, pageInfo);
		 for(ArtifactDTO dto : list){
			 dto.setFileUrl(Config.getProperty("QINIU_DOMAIN") + dto.getFileName());
			 List<String> imageList = artifactImageDao.getArtifactImageByObjectId(dto.getId(), IMAGE_FROM.ARTIFACT, IMAGE_STYLE.LIST);
			 if(imageList != null && imageList.size() > 0){
				 dto.setImageUrl(Config.getProperty("QINIU_DOMAIN") + imageList.get(0));
			 }
		 }
		 return list;
	}

	@Override
	@Transactional
	public void saveAssign(String artifactIds, int tagId) {
		artifactTagDao.delete(tagId, artifactIds);
		String[] artifactIdArr = StringUtils.split(artifactIds, ",");
		for(String artifactId : artifactIdArr){
			artifactTagDao.save(Long.valueOf(artifactId), Long.valueOf(tagId), 0);
		}
		
	}

	@Override
	@Transactional
	public void addNewStoryFromDownload(DownloadTaskDTO downloadTask, String imageFileName, File imageFile,
			String voiceFileName, File voiceFile) throws JSONException, AuthException {
		
		ArtifactDTO story = new ArtifactDTO();
		story.setType(downloadTask.getType());
		story.setDownloadTaskId(downloadTask.getId());
		artifactDao.insertFromDownload(story);
		
		String uptoken = QiniuUtils.getUptoken();
		if(downloadTask.getType() == DOWNLOAD_TASK_TYPE.ARTIFACT.getValue()){ //如果是故事，需要将故事和他的封面（专辑）关联起来
			artifactHierarchyDao.insertFromDownload(story.getId(), downloadTask.getTaskId());
			if(voiceFile != null){
				QiniuUtils.upload(uptoken, voiceFileName, voiceFile);
			}
		} else{
			// do nothing. 如果是封面，不需要做上下级的关联 
		}
		
		ArtifactImageDTO  image = new ArtifactImageDTO();
		imageDao.insert(image);
		artifactImageDao.insert(story.getId(), image.getId(), IMAGE_FROM.ARTIFACT, IMAGE_STYLE.DETAIL);
		try{
			if(imageFile != null){
				QiniuUtils.upload(uptoken, imageFileName, imageFile);
			}
		}catch(Throwable e){
			log.error("上传图片失败", e);
		}
	}

	@Override
	@Transactional
	public void deleteByIds(String ids) {
		artifactDao.deleteByIds(ids);
		artifactHierarchyDao.deleteByArtifactIds(ids);
	}

	@Override
	@Transactional
	public void create(ArtifactDTO artifact, CategoryArrayDTO categorys,
			TagArrayDTO tags, MultipartFile listImageFile,
			MultipartFile mediaFile) throws IOException, AuthException, JSONException {
		ArtifactImageDTO  image = new ArtifactImageDTO();
		image.setTagImage(0);
		imageDao.insert(image);
		
		String fileName = QiniuUtils.generateArtifactImageName(image.getId(), listImageFile.getOriginalFilename());
		image.setFileName(fileName);
		File localFile = DownloadUtils.getFile(listImageFile.getInputStream(), fileName);
		String uptoken = QiniuUtils.getUptoken();
		QiniuUtils.upload(uptoken, fileName, localFile);
		imageDao.updateFileName(image);
		localFile.delete();
		
		artifactDao.insert(artifact);
		
		artifactImageDao.insert(artifact.getId(), image.getId(), IMAGE_FROM.ARTIFACT, IMAGE_STYLE.LIST);
		
		fileName = QiniuUtils.generateArtifactMediaName(image.getId(), mediaFile.getOriginalFilename());
		artifact.setFileName(fileName);
		localFile = DownloadUtils.getFile(mediaFile.getInputStream(), fileName);
		QiniuUtils.upload(uptoken, fileName, localFile);
		artifactDao.updateFileName(artifact);
		localFile.delete();
		
		TagDTO[] tagArr = tags.getTag();
		for(TagDTO tag : tagArr){
			artifactTagDao.save(artifact.getId(), tag.getId(), tag.getTagOrder());
		}
		
		CategoryDTO[] catArr = categorys.getCategory();
		for(CategoryDTO cat : catArr){
			artifactCategoryDao.save(artifact.getId(), cat.getId(), cat.getCategoryOrder());
		}
		
	}

	@Override
	public ArtifactDTO getById(long artifactId) {
		ArtifactDTO dto = artifactDao.getById(artifactId);
		dto.setFileUrl(Config.getProperty("QINIU_DOMAIN") + dto.getFileName());
		dto.setImageUrl(Config.getProperty("QINIU_DOMAIN") + dto.getImageName());
		return dto;
	}

	@Override
	public List<TagDTO> getTagByArtifactId(long artifactId) {
		return artifactTagDao.getByArtifactId(artifactId);
	}

	@Override
	public List<CategoryDTO> getCategoryByArtifactId(long artifactId) {
		return artifactCategoryDao.getByArtifactId(artifactId);
	}

	@Override
	public List<ArtifactDTO> getSublistById(long artifactId) {
		return artifactDao.getSubListByArtifactId(artifactId);
	}

	@Override
	public void update(ArtifactDTO artifact, CategoryArrayDTO categorys,
			TagArrayDTO tags, MultipartFile listImageFile,
			MultipartFile mediaFile) throws IOException, AuthException, JSONException {
		artifactDao.updateArtifact(artifact);
		artifactTagDao.deleteByArtifactId(artifact.getId());
		TagDTO[] tagArr = tags.getTag();
		for(TagDTO tag : tagArr){
			artifactTagDao.save(artifact.getId(), tag.getId(), tag.getTagOrder());
		}
		artifactCategoryDao.deleteByArtifactId(artifact.getId());
		
		CategoryDTO[] catArr = categorys.getCategory();
		for(CategoryDTO cat : catArr){
			artifactCategoryDao.save(artifact.getId(), cat.getId(), cat.getCategoryOrder());
		}
		
		if(StringUtils.isBlank(artifact.getImageUrl()) || (listImageFile != null && listImageFile.getSize() > 0)){ //做了修改
			imageDao.deleteByIds(artifact.getImageId()+"");
			artifactImageDao.deleteByImageId(artifact.getImageId(), IMAGE_FROM.ARTIFACT);
			QiniuUtils.deleteFile(artifact.getImageName());
			
			ArtifactImageDTO  image = new ArtifactImageDTO();
			image.setTagImage(0);
			imageDao.insert(image);
			
			String fileName = QiniuUtils.generateArtifactImageName(image.getId(), listImageFile.getOriginalFilename());
			image.setFileName(fileName);
			File localFile = DownloadUtils.getFile(listImageFile.getInputStream(), fileName);
			String uptoken = QiniuUtils.getUptoken();
			QiniuUtils.upload(uptoken, fileName, localFile);
			imageDao.updateFileName(image);
			localFile.delete();
			
			artifactImageDao.deleteByArtifactId(artifact.getId(), IMAGE_FROM.ARTIFACT, IMAGE_STYLE.LIST);
			artifactImageDao.insert(artifact.getId(), image.getId(), IMAGE_FROM.ARTIFACT, IMAGE_STYLE.LIST);
		} 
		if(StringUtils.isBlank(artifact.getFileUrl()) || (mediaFile != null && mediaFile.getSize() > 0)){ //做了修改
			QiniuUtils.deleteFile(artifact.getFileName());
			
			String fileName = QiniuUtils.generateArtifactImageName(artifact.getId(), mediaFile.getOriginalFilename());
			artifact.setFileName(fileName);
			File localFile = DownloadUtils.getFile(mediaFile.getInputStream(), fileName);
			String uptoken = QiniuUtils.getUptoken();
			QiniuUtils.upload(uptoken, fileName, localFile);
			artifactDao.updateFileName(artifact);
			localFile.delete();
		} 
	}
	

}
