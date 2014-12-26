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
import com.zhiqin.coach.admin.dao.AreaDao;
import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dao.ArtifactHierarchyDao;
import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.AreaService;
import com.zhiqin.coach.admin.service.ArtifactService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.QiniuUtils;
import com.zhiqin.coach.admin.util.RopUtils;

@Service
public class AreaServiceImpl implements AreaService {
	private static final Logger log = LoggerFactory
			.getLogger(AreaServiceImpl.class);
	@Resource private AreaDao areaDao;
	@Override
	public Integer getByAreaCode(String areaCode) {
		return areaDao.getByAreaCode(areaCode);
	}
	@Override
	public List<AreaDTO> getSubareaById(int areaId) {
		return areaDao.getSubareaById(areaId);
	}
	
	

}
