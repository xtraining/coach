package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.common.HtmlParserFactory;
import com.zhiqin.coach.admin.common.MyHtmlParser;
import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dao.TaskDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;
import com.zhiqin.coach.admin.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Resource private TaskDao taskDao;

	@Override
	public Long getTotalNum() {
		return taskDao.getTotalNum();
	}

	@Override
	public List<TaskDTO> getTaskList(PageInfoDTO pageInfo) {
		return taskDao.getTaskList(pageInfo);
	}

	@Override
	@Transactional
	public void create(int sourceFrom, String url) {
		String[] urlArr = StringUtils.split(url, ";");
		for(String htmlUrl : urlArr){
			MyHtmlParser parser = HtmlParserFactory.getParser(sourceFrom);
			TaskDTO dto = parser.parse(htmlUrl);
			taskDao.create(dto);
		}
		
	}
	

}
