package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.TaskDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TaskDaoImpl extends BaseDaoImpl implements TaskDao
{
	private static final Logger log = LoggerFactory
			.getLogger(TaskDaoImpl.class);

	@Override
	public Long getTotalNum() {
		try{
			return  this.getSqlSession().selectOne("task.getTotalNum");
		} catch(RuntimeException e){
			log.error("getTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<TaskDTO> getTaskList(PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("task.getTaskList", map);
		} catch(RuntimeException e){
			log.error("getTaskList", e);
			throw e;
		}
	}

	@Override
	public void create(TaskDTO dto) {
		// TODO Auto-generated method stub
		
	}


	
	
}
