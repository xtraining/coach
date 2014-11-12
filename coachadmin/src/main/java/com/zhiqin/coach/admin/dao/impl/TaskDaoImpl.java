package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dao.TaskDao;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
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
		try{
			this.getSqlSession().insert("task.insertTask", dto);
		} catch(RuntimeException e){
			log.error("insertTask", e);
			throw e;
		}
		
	}

	@Override
	public void saveDownloadTask(DownloadTaskDTO downloadDto) {
		try{
			this.getSqlSession().insert("task.insertDownloadTask", downloadDto);
		} catch(RuntimeException e){
			log.error("insertDownloadTask", e);
			throw e;
		}
		
	}

	@Override
	public TaskDTO getById(Integer taskId) {
		try{
			return  this.getSqlSession().selectOne("task.getById", taskId);
		} catch(RuntimeException e){
			log.error("getById", e);
			throw e;
		}
	}

	@Override
	public void updateStatus(Integer taskId, TASK_STATUS status) {
		try{
			Map map = new HashMap();
			map.put("taskId", taskId);
			map.put("status", status.getValue());
			this.getSqlSession().update("task.updateStatus", map);
		} catch(RuntimeException e){
			log.error("getById", e);
			throw e;
		}
		
	}

	@Override
	public Long getDownloadTaskTotalNum(int taskId) {
		try{
			return  this.getSqlSession().selectOne("task.getDownloadTaskTotalNum", taskId);
		} catch(RuntimeException e){
			log.error("getDownloadTaskTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<DownloadTaskDTO> getDownloadTaskList(int taskId,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("taskId", taskId);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("task.getDownloadTaskList", map);
		} catch(RuntimeException e){
			log.error("getDownloadTaskList", e);
			throw e;
		}
	}


	
	
}
