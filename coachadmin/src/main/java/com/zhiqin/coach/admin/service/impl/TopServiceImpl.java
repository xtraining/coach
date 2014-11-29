package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.TopDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TopDTO;
import com.zhiqin.coach.admin.service.TopService;

@Service
public class TopServiceImpl implements TopService {
	private static Log log = LogFactory.getLog(TopServiceImpl.class);
	@Resource private TopDao topDao;
	@Override
	public void create(TopDTO dto) {
		topDao.save(dto);
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
}
