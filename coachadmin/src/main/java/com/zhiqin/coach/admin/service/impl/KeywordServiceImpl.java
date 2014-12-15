package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.dao.KeywordDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.KeywordDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {
	private static Log log = LogFactory.getLog(KeywordServiceImpl.class);
	@Resource private KeywordDao keywordDao;
	@Override
	public Long getKeywordTotalNum(String name) {
		return keywordDao.getKeywordTotalNum(name);
	}
	@Override
	public List<KeywordDTO> getKeywordList(String name, PageInfoDTO pageInfo) {
		return keywordDao.getKeywordList(name, pageInfo);
	}
	@Override
	@Transactional
	public void create(KeywordDTO dto) {
		keywordDao.create(dto);
		
	}
	@Override
	public KeywordDTO getById(Long keywordId) {
		return keywordDao.getById(keywordId);
	}
	@Override
	public Long getStoryTotalNumByKeyword(String name) {
		return keywordDao.getStoryTotalNumByKeyword(name);
	}
	@Override
	public List<ArtifactDTO> getStoryListByKeyword(String name,
			PageInfoDTO pageInfo) {
		return keywordDao.getStoryListByKeyword(name, pageInfo);
	}
	@Override
	@Transactional
	public void saveOrder(int type, long objectId, long keywordId, int keywordOrder) {
		keywordDao.deleteOrder(type, objectId, keywordId);
		keywordDao.insertOrder(type, objectId, keywordId, keywordOrder);
	}
	

}
