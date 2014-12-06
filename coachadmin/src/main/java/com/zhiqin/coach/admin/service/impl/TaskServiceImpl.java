package com.zhiqin.coach.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.common.Constants;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.HtmlParserFactory;
import com.zhiqin.coach.admin.common.MyHtmlParser;
import com.zhiqin.coach.admin.dao.ArtifactCategoryDao;
import com.zhiqin.coach.admin.dao.ArtifactDao;
import com.zhiqin.coach.admin.dao.ArtifactTagDao;
import com.zhiqin.coach.admin.dao.TaskDao;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;
import com.zhiqin.coach.admin.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private static Log log = LogFactory.getLog(TaskServiceImpl.class);
	@Resource private TaskDao taskDao;
	@Resource private ArtifactTagDao artifactTagDao;
	@Resource private ArtifactDao artifactDao;
	@Resource private ArtifactCategoryDao artifactCategoryDao;
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
		List<Integer> taskIdList = new ArrayList<Integer>();
		if(urlArr != null && urlArr.length > 0){
			for(String htmlUrl : urlArr){
				MyHtmlParser parser = HtmlParserFactory.getParser(sourceFrom);
				TaskDTO dto = parser.getTask(StringUtils.trim(htmlUrl));
				taskDao.create(dto);
				taskIdList.add(dto.getId());
			}
		}
		if(taskIdList.size() > 0){
			SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("download-task-" + taskIdList);
			executor.setConcurrencyLimit(-1);
		    executor.execute(new CreateDownloadTaskThread(taskIdList), 60000L);	
		}
	}

	class CreateDownloadTaskThread implements Runnable {
		private List<Integer> taskIdList;
		public CreateDownloadTaskThread(List<Integer> taskIdList){
			this.taskIdList = taskIdList;
		}
	    public void run() {
	    	try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
    		if(taskIdList != null & taskIdList.size() > 0){
    			for(Integer taskId : taskIdList){
    				try {
	    				TaskDTO task = taskDao.getById(taskId);
	    				MyHtmlParser parser = HtmlParserFactory.getParser(task.getSourceFrom());
	    				List<DownloadTaskDTO> downloadDtoList = parser.getDownloadTask(task);
	    				if(downloadDtoList != null && downloadDtoList.size() > 0){
	    					for(DownloadTaskDTO dto : downloadDtoList){
	    						taskDao.saveDownloadTask(dto);
	    					}
	    				} 
    				} catch (Throwable e) {
    					log.error("CreateDownloadTaskThread error", e);
    				}
    			}
    		}
	    }
	}

	@Override
	public Long getDownloadTaskTotalNum(int taskId) {
		return taskDao.getDownloadTaskTotalNum(taskId);
	}

	@Override
	public List<DownloadTaskDTO> getDownloadTaskList(int taskId,
			PageInfoDTO pageInfo) {
		return taskDao.getDownloadTaskList(taskId, pageInfo);
	}

	@Override
	public Long getDownloadingTaskNum() {
		return taskDao.getDownloadingTaskNum();
	}

	@Override
	public List<DownloadTaskDTO> getDownloadTask(int maxNum) {
		return taskDao.getDownloadTask(maxNum);
	}

	@Override
	public void updateDownloadStatus(Long downloadTaskId, DOWNLOAD_TASK_STATUS status) {
		taskDao.updateDownloadStatus(downloadTaskId, status);
		
	}
	
	@Override
	public void updateDownloadStatus(Long downloadTaskId, DOWNLOAD_TASK_STATUS status, String fileName) {
		taskDao.updateDownloadStatus(downloadTaskId, status, fileName);
		
	}

	@Override
	@Transactional
	public void deleteByIds(String ids) {
		taskDao.deleteByIds(ids);
	}

	@Override
	@Transactional
	public void saveAccept(int taskId, String downloadTaskIds, int categoryId) {
		if(taskId > 0){
			List<ArtifactDTO> artifactList = taskDao.getArtifactByTaskId(taskId);
			saveAccept(categoryId, artifactList);
		} else if(StringUtils.isNotBlank(downloadTaskIds)){
			String [] downloadTaskId = StringUtils.split(downloadTaskIds, ",");
			List<Long>downloadTaskIdList = new ArrayList<Long>();
			for(String id : downloadTaskId){
				downloadTaskIdList.add(Long.valueOf(StringUtils.trim(id)));
			}
			List<ArtifactDTO> artifactList = taskDao.getArtifactByDownloadTaskId(downloadTaskIdList);
			saveAccept(categoryId, artifactList);
		}
		
	}

	private void saveAccept(int categoryId,
			List<ArtifactDTO> artifactList) {
		if(artifactList != null && artifactList.size() > 0){
			List<Long> artifactIdList = new ArrayList<Long>();
			List<Long> downloadIdList = new ArrayList<Long>();
			for(ArtifactDTO dto : artifactList){
				artifactIdList.add(dto.getId());
				downloadIdList.add(dto.getDownloadTaskId());
			}
			artifactDao.updateStatus(artifactIdList, Constants.ARTIFACT_STATUS.ACTIVE.getValue());
			taskDao.updateStatus(downloadIdList, Constants.DOWNLOAD_TASK_STATUS.UPLOAD.getValue());
			for(Long artifactId : artifactIdList){
				ArtifactDTO artifact = new ArtifactDTO();
				artifact.setId(artifactId);
				artifact.setCategoryId(Long.valueOf(categoryId));
				artifact.setCategoryOrder(0);
				artifactCategoryDao.save(artifact);
			}
		}
	}
	

}
