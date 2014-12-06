package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dao.TopDao;
import com.zhiqin.coach.admin.dto.ArtifactArrayDTO;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TopDTO;
import com.zhiqin.coach.admin.service.TopService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DateUtils;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class TopServiceImpl implements TopService {
	private static Log log = LogFactory.getLog(TopServiceImpl.class);
	@Resource private TopDao topDao;
	@Override
	@Transactional
	public void create(TopDTO dto, ArtifactArrayDTO artifacts, MultipartFile imageFile) throws IOException, AuthException, JSONException{
		List<Term> wordList = ToAnalysis.parse(dto.getName());
		StringBuilder tags = new StringBuilder();
		if(wordList != null && wordList.size() >0){
			for(Term word : wordList){
				tags.append(word.getName() + ",");
			}
		}
		dto.setTags(dto.getTags() + "," + tags);
		dto.setStartTime(DateUtils.yyyyMMddHHmmToTimestamp(dto.getStartTimeStr()));
		topDao.save(dto);
		
		String fileName = QiniuUtils.generateTopListImageName(dto.getId(), imageFile.getOriginalFilename());
		dto.setListImageFileName(fileName);
		File localFile = DownloadUtils.getFile(imageFile.getInputStream(), fileName);
		String uptoken = QiniuUtils.getUptoken();
		QiniuUtils.upload(uptoken, fileName, localFile);
		topDao.updateListImageFileName(dto);
		localFile.delete();
		
		for(ArtifactDTO a : artifacts.getArtifact()){
			topDao.insertArtifact(dto.getId(), a.getId(), a.getArtifactOrder());
		}
	}
	
	@Override
	@Transactional
	public void update(TopDTO dto, ArtifactArrayDTO artifacts,
			MultipartFile listImageFile) throws AuthException, JSONException, IOException {
		if(StringUtils.isBlank(dto.getListImageFileUrl()) || (listImageFile != null && listImageFile.getSize() > 0)){ //做了修改
			String uptoken = QiniuUtils.getUptoken();
			QiniuUtils.deleteFile(dto.getListImageFileName());
			
			String fileName = QiniuUtils.generateTopListImageName(dto.getId(), listImageFile.getOriginalFilename());
			dto.setListImageFileName(fileName);
			File localFile = DownloadUtils.getFile(listImageFile.getInputStream(), fileName);
			QiniuUtils.upload(uptoken, fileName, localFile);
			topDao.updateListImageFileName(dto);
			localFile.delete();
		}
		dto.setStartTime(DateUtils.yyyyMMddHHmmToTimestamp(dto.getStartTimeStr()));
		topDao.update(dto);
		
		topDao.deleteArtifactById(dto.getId());
		if(artifacts != null && artifacts.getArtifact() != null){
			for(ArtifactDTO a : artifacts.getArtifact()){
				if(a.getId() != null)
					topDao.insertArtifact(dto.getId(), a.getId(), a.getArtifactOrder());
			}
		}
		
	}
	@Override
	public Long getTopTotalNum(String name) {
		return topDao.getTopTotalNum(name);
	}
	@Override
	public List<TopDTO> getTopList(String name, PageInfoDTO pageInfo) {
		return topDao.getTopList(name, pageInfo);
	}
	@Override
	public void deleteByIds(String ids) {
		topDao.deleteByIds(ids);
		
	}
	@Override
	public TopDTO getById(int topId) {
		TopDTO dto = topDao.getById(topId);
		dto.setStartTimeStr(DateUtils.dateToyyyyMMddHHmi(dto.getStartTime()));
		dto.setListImageFileUrl(Config.getProperty("QINIU_DOMAIN") + dto.getListImageFileName());
		return dto;
	}
	@Override
	public List<ArtifactDTO> getArtifactByTopId(int topId) {
		List<ArtifactDTO> list = topDao.getArtifactByTopId(topId);
		return list;
	}

}
