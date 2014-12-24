package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dao.TaskDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
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

	@Override
	public Long getDownloadingTaskNum() {
		try{
			Map map = new HashMap();
			map.put("status", DOWNLOAD_TASK_STATUS.INPROGRESS.getValue());
			return this.getSqlSession().selectOne("task.getDownloadingTaskNum", map);
		} catch(RuntimeException e){
			log.error("getDownloadingTaskNum", e);
			throw e;
		}
	}

	@Override
	public List<DownloadTaskDTO> getDownloadTask(int maxNum) {
		try{
			Map map = new HashMap();
			map.put("status", DOWNLOAD_TASK_STATUS.DRAFT.getValue());
			map.put("maxNum", maxNum);
			return this.getSqlSession().selectList("task.getDownloadTask", map);
		} catch(RuntimeException e){
			log.error("getDownloadTask", e);
			throw e;
		}
	}

	@Override
	public void updateDownloadStatus(Long downloadTaskId, DOWNLOAD_TASK_STATUS status) {
		try{
			Map map = new HashMap();
			map.put("downloadTaskId", downloadTaskId);
			map.put("status", status.getValue());
			this.getSqlSession().update("task.updateDownloadStatus", map);
		} catch(RuntimeException e){
			log.error("updateDownloadStatus", e);
			throw e;
		}
		
	}

	@Override
	public void updateDownloadStatus(Long downloadTaskId,
			DOWNLOAD_TASK_STATUS status, String fileName) {
		try{
			Map map = new HashMap();
			map.put("downloadTaskId", downloadTaskId);
			map.put("status", status.getValue());
			map.put("fileName", fileName);
			this.getSqlSession().update("task.updateDownloadStatus", map);
		} catch(RuntimeException e){
			log.error("updateDownloadStatus", e);
			throw e;
		}
		
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("task.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
		
	}

	@Override
	public List<ArtifactDTO> getArtifactByTaskId(int taskId) {
		try{
			return this.getSqlSession().selectList("task.getArtifactByTaskId", taskId);
		} catch(RuntimeException e){
			log.error("getArtifactIdByTaskId", e);
			throw e;
		}
	}

	@Override
	public void updateStatus(List<Long> downloadIdList, int status) {
		try{
			Map map = new HashMap();
			map.put("downloadIdList", downloadIdList);
			map.put("status", status);
			this.getSqlSession().update("task.updateDownloadTaskStatus", map);
		} catch(RuntimeException e){
			log.error("updateDownloadTaskStatus", e);
			throw e;
		}
		
	}

	@Override
	public List<ArtifactDTO> getArtifactByDownloadTaskId(int downloadTaskId) {
		try{
			return this.getSqlSession().selectList("task.getArtifactByDownloadTaskId", downloadTaskId);
		} catch(RuntimeException e){
			log.error("getArtifactIdByDownloadTaskId", e);
			throw e;
		}
	}

	@Override
	public List<ArtifactDTO> getArtifactByDownloadTaskId(
			List<Long> downloadTaskIdList) {
		try{
			Map map = new HashMap();
			map.put("downloadTaskIdList", downloadTaskIdList);
			return this.getSqlSession().selectList("task.getArtifactByDownloadTaskIdList", map);
		} catch(RuntimeException e){
			log.error("getArtifactIdByDownloadTaskIdList", e);
			throw e;
		}
	}

	@Override
	public void redownloadAll(int taskId) {
		try{
			Map map = new HashMap();
			map.put("taskId", taskId);
			map.put("status", DOWNLOAD_TASK_STATUS.DRAFT.getValue());
			this.getSqlSession().update("task.redownloadAll", map);
		} catch(RuntimeException e){
			log.error("redownloadAll", e);
			throw e;
		}
		
	}

	@Override
	public List<ArtifactDTO> getArtifactByTaskIds(String taskIds) {
		try{
			return this.getSqlSession().selectList("task.getArtifactByTaskIds", taskIds);
		} catch(RuntimeException e){
			log.error("getArtifactByTaskIds", e);
			throw e;
		}
	}

	@Override
	public boolean checkExisting(String url) {
		try{
			Long id = this.getSqlSession().selectOne("task.checkExisting", url);
			if(id != null && id > 0){
				return true;
			} else {
				return false;
			}
		} catch(RuntimeException e){
			log.error("checkExisting", e);
			throw e;
		}
	}


	
	
}
