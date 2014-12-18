package com.zhiqin.coach.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dao.CategoryDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.service.CategoryService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;

@Service
public class CategoryServiceImpl implements CategoryService {
	private static Log log = LogFactory.getLog(CategoryServiceImpl.class);
	@Resource private CategoryDao categoryDao;
	@Resource private ArtifactCategoryDao artifactCategoryDao;
	@Override
	public Long getCategoryTotalNum(String name) {
		return categoryDao.getCategoryTotalNum(name);
	}
	@Override
	public List<CategoryDTO> getCategoryList(String name, PageInfoDTO pageInfo) {
		return categoryDao.getCategoryList(name, pageInfo);
	}
	@Override
	@Transactional
	public void create(CategoryDTO dto, MultipartFile imageFile) throws IOException, AuthException, JSONException{
		categoryDao.create(dto);
		
		String fileName = QiniuUtils.generateCategoryImageName(dto.getId(), imageFile.getOriginalFilename());
		File localFile = DownloadUtils.getFile(imageFile.getInputStream(), fileName);
		String uptoken = QiniuUtils.getUptoken();
		QiniuUtils.upload(uptoken, fileName, localFile);
		localFile.delete();
		
		dto.setImageName(fileName);
		
		categoryDao.updateImageName(dto);
		
	}
	@Override
	public void deleteByIds(String ids) {
		categoryDao.deleteByIds(ids);
		
	}
	@Override
	public CategoryDTO getById(int categoryId) {
		CategoryDTO dto = categoryDao.getById(categoryId);
		Random r = new Random();
		int t = r.nextInt(100);
		dto.setImageUrl(Config.getProperty("QINIU_DOMAIN") + dto.getImageName()+"?t="+t);
		return dto;
	}
	@Override
	public void update(CategoryDTO dto, MultipartFile imageFile) throws IOException, AuthException, JSONException {
		String uptoken = null;
		if(StringUtils.isBlank(dto.getImageUrl()) || (imageFile != null && imageFile.getSize() > 0)){ //做了修改
			uptoken = QiniuUtils.getUptoken();
			QiniuUtils.deleteFile(dto.getImageName());
			String fileName = QiniuUtils.generateCategoryImageName(dto.getId(), imageFile.getOriginalFilename());
			dto.setImageName(fileName);
			File localFile = DownloadUtils.getFile(imageFile.getInputStream(), fileName);
			QiniuUtils.upload(uptoken, fileName, localFile);
			localFile.delete();
		} 
		
		categoryDao.update(dto);
		
	}
	@Override
	public void saveOrder(long categoryId, long artifactId, int categoryOrder) {
		artifactCategoryDao.delete(artifactId+"");
		ArtifactDTO a = new ArtifactDTO();
		a.setCategoryOrder(categoryOrder);
		a.setCategoryId(categoryId);
		a.setId(artifactId);
		artifactCategoryDao.save(a);
	}
}
