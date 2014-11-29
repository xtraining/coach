package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_TYPE;
import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_TYPE;
import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dao.ArtifactHierarchyDao;
import com.zhiqin.coach.admin.dao.ArtifactImageDao;
import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dao.impl.ArtifactImageDaoImpl;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.service.ArtifactService;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class ArtifactServiceImpl implements ArtifactService {
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactImageDaoImpl.class);
	
	
	@Resource private ArtifactDao artifactDao;
	@Resource private ArtifactImageDao artifactImageDao;
	@Resource private ArtifactHierarchyDao artifactHierarchyDao;
	@Resource private ArtifactTagDao artifactTagDao;
	@Override
	public Long getArtifactTotalNum(SearchArtifactDTO searchDto) {
		return artifactDao.getArtifactTotalNum(searchDto);
	}

	@Override
	public List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo) {
		return artifactDao.getArtifactList(searchDto, pageInfo);
	}

	@Override
	@Transactional
	public void create(ArtifactDTO dto) {
		artifactDao.insert(dto);
		
	}

	@Override
	@Transactional
	public void saveAssign(String artifactIds, int tagId) {
		artifactTagDao.delete(tagId, artifactIds);
		String[] artifactIdArr = StringUtils.split(artifactIds, ",");
		for(String artifactId : artifactIdArr){
			artifactTagDao.save(artifactId, tagId);
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
		
		artifactImageDao.insertFromDownload(story.getId(), imageFileName, IMAGE_FROM.ARTIFACT, IMAGE_TYPE.DETAIL);
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
	

}
