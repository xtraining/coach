package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_TYPE;
import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dao.ArtifactHierarchyDao;
import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.ArtifactService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;
import com.zhiqin.coach.admin.util.RopUtils;

@Service
public class ArtifactServiceImpl implements ArtifactService {
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactServiceImpl.class);
	
	
	@Resource private ArtifactDao artifactDao;
	@Resource private ArtifactHierarchyDao artifactHierarchyDao;
	@Resource private ArtifactTagDao artifactTagDao;
	@Resource private ArtifactCategoryDao artifactCategoryDao;
	@Override
	public Long getArtifactTotalNum(SearchArtifactDTO searchDto) {
		return artifactDao.getArtifactTotalNum(searchDto);
	}

	@Override
	public List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo) {
		 List<ArtifactDTO> list = artifactDao.getArtifactList(searchDto, pageInfo);
	 	 Random r = new Random();
		 int t = r.nextInt(100);
		 for(ArtifactDTO dto : list){
			dto.setFileUrl(Config.getProperty("QINIU_DOMAIN") + dto.getFileName()+"?t="+t);
			dto.setImageUrl(Config.getProperty("QINIU_DOMAIN") + dto.getImageName()+"?t="+t);
			List<CategoryDTO> tempList = artifactCategoryDao.getByArtifactId(dto.getId());
			if(tempList.size() > 0){
				dto.setCategoryName(tempList.get(0).getName());
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
		if(downloadTask.getType() == DOWNLOAD_TASK_TYPE.ARTIFACT.getValue()){ //如果是故事，需要将故事和他的封面（专辑）关联起来
			artifactDao.deleteByDownloadTaskId(downloadTask.getId());
			ArtifactDTO story = new ArtifactDTO();
			story.setType(downloadTask.getType());
			story.setDownloadTaskId(downloadTask.getId());
			artifactDao.insertFromDownload(story);
			
			story.setImageName(QiniuUtils.generateArtifactImageName(story.getId(), imageFileName));
			story.setFileName(QiniuUtils.generateArtifactMediaName(story.getId(), voiceFileName));
			artifactDao.updateFileName(story);
			
			String uptoken = QiniuUtils.getUptoken();
			if(voiceFile != null){
				QiniuUtils.upload(uptoken, story.getFileName(), voiceFile); 
				try{
					voiceFile.delete();
				}finally{}
			}
			if(imageFile != null){
				QiniuUtils.upload(uptoken, story.getImageName(), imageFile);
				try{
					imageFile.delete();
				} finally{}
			}
		} else{
			// do nothing. 如果是封面，不需要做任何事情 
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
	public void create(ArtifactDTO artifact, MultipartFile listImageFile,
			MultipartFile mediaFile) throws IOException, AuthException, JSONException {
		List<Term> wordList = ToAnalysis.parse(artifact.getTitle());
		StringBuilder tags = new StringBuilder();
		if(wordList != null && wordList.size() >0){
			for(Term word : wordList){
				tags.append(word.getName() + ",");
			}
		}
		artifact.setTags(artifact.getTags() + "," + tags);
		artifactDao.insert(artifact);
		
		String fileName = QiniuUtils.generateArtifactImageName(artifact.getId(), listImageFile.getOriginalFilename());
		File localFile = DownloadUtils.getFile(listImageFile.getInputStream(), fileName);
		String uptoken = QiniuUtils.getUptoken();
		QiniuUtils.upload(uptoken, fileName, localFile);
		localFile.delete();
		
		artifact.setImageName(fileName);
		
		fileName = QiniuUtils.generateArtifactMediaName(artifact.getId(), mediaFile.getOriginalFilename());
		artifact.setFileName(fileName);
		localFile = DownloadUtils.getFile(mediaFile.getInputStream(), fileName);
		QiniuUtils.upload(uptoken, fileName, localFile);
		artifactDao.updateFileName(artifact);
		localFile.delete();
		
		artifactCategoryDao.save(artifact);
		
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
	public void update(ArtifactDTO artifact, MultipartFile listImageFile,
			MultipartFile mediaFile) throws IOException, AuthException, JSONException {
		String uptoken = null;
		if(StringUtils.isBlank(artifact.getImageUrl()) || (listImageFile != null && listImageFile.getSize() > 0)){ //做了修改
			uptoken = QiniuUtils.getUptoken();
			QiniuUtils.deleteFile(artifact.getImageName());
			String fileName = QiniuUtils.generateArtifactImageName(artifact.getId(), listImageFile.getOriginalFilename());
			artifact.setImageName(fileName);
			File localFile = DownloadUtils.getFile(listImageFile.getInputStream(), fileName);
			QiniuUtils.upload(uptoken, fileName, localFile);
			localFile.delete();
		} 
		if(StringUtils.isBlank(artifact.getFileUrl()) || (mediaFile != null && mediaFile.getSize() > 0)){ //做了修改
			if(uptoken == null){
				uptoken = QiniuUtils.getUptoken();
			}
			QiniuUtils.deleteFile(artifact.getFileName());
			String fileName = QiniuUtils.generateArtifactMediaName(artifact.getId(), mediaFile.getOriginalFilename());
			artifact.setFileName(fileName);
			File localFile = DownloadUtils.getFile(mediaFile.getInputStream(), fileName);
			QiniuUtils.upload(uptoken, fileName, localFile);
			localFile.delete();
		} 
		artifactDao.updateArtifact(artifact);
		
		artifactCategoryDao.deleteByArtifactId(artifact.getId());
		
		artifactCategoryDao.save(artifact);
	}

	@Override
	@Transactional
	public void saveCategory(String artifactIds, int categoryId) {
		artifactCategoryDao.delete(artifactIds);
		String[] artifactIdArr = StringUtils.split(artifactIds, ",");
		for(String artifactId : artifactIdArr){
			ArtifactDTO a = new ArtifactDTO();
			a.setCategoryOrder(0);
			a.setCategoryId(Long.valueOf(categoryId));
			a.setId(Long.valueOf(artifactId));
			artifactCategoryDao.save(a);
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(RopUtils.getMD5String("123456"));
	}

}
