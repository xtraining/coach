package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.dao.TagDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.TagService;

@Service
public class TagServiceImpl implements TagService {
	private static Log log = LogFactory.getLog(TagServiceImpl.class);
	@Resource private TagDao tagDao;
	@Override
	public Long getTagTotalNum(String name) {
		return tagDao.getTagTotalNum(name);
	}
	@Override
	public List<TagDTO> getTagList(String name, PageInfoDTO pageInfo) {
		return tagDao.getTagList(name, pageInfo);
	}
	@Override
	@Transactional
	public void create(TagDTO dto) {
		tagDao.create(dto);
		
	}
	@Override
	public void deleteByIds(String ids) {
		tagDao.deleteByIds(ids);
		
	}
	@Override
	public TagDTO getById(int tagId) {
		return tagDao.getById(tagId);
	}
	@Override
	public void update(TagDTO dto) {
		tagDao.update(dto);
		
	}
	

}
