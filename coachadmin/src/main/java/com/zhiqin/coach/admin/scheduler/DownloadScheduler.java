/**
 * @author Zhang Zhipeng
 *
 * 2013-12-22
 */
package com.zhiqin.coach.admin.scheduler;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhiqin.coach.admin.common.Constants;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.FILE_NAME_PREFIX;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.service.TaskService;
import com.zhiqin.coach.admin.util.DownloadUtils;

/**
 * @author Lenovo
 * 
 */
@Component
public class DownloadScheduler {
	private static final Logger log = LoggerFactory.getLogger(DownloadScheduler.class);
	private static final int MAX_NUM = 5;
	@Autowired
	private TaskService taskService;
	@Scheduled(fixedRate = 60000)
	void downloadFiles() {
		log.info("检查是否有需要下载的内容");
		int num = taskService.getDownloadingTaskNum().intValue();
		if(num >= MAX_NUM){
			log.info("已有" + MAX_NUM + "个任务正在下载，等待中。。。");
			return;
		}
		List<DownloadTaskDTO> list = taskService.getDownloadTask(5);
		for(DownloadTaskDTO dto : list){
			try{
				taskService.updateDownloadStatus(dto.getId(), DOWNLOAD_TASK_STATUS.INPROGRESS);
				if(StringUtils.isNotBlank(dto.getImageUrl())){
					String extName = getExtName(dto.getImageUrl());
					String fileName = FILE_NAME_PREFIX.IMAGE.getValue() + dto.getId() + extName;
					DownloadUtils.download(dto.getImageUrl(), fileName);
				}
				if(StringUtils.isNotBlank(dto.getFileUrl())){
					String extName = getExtName(dto.getFileUrl());
					String fileName = FILE_NAME_PREFIX.VOICE.getValue() + dto.getId() + extName;
					DownloadUtils.download(dto.getFileUrl(), fileName);
				}
				taskService.updateDownloadStatus(dto.getId(), DOWNLOAD_TASK_STATUS.SUCCESS);
			} catch(Throwable e){
				log.info("下载任务错误，任务Download ID : " +  dto.getId());
				taskService.updateDownloadStatus(dto.getId(), DOWNLOAD_TASK_STATUS.FAILURE);
			}
		}
	}
	private String getExtName(String fileUrl) {
		String extName = null;
		int endInd = fileUrl.indexOf("?");
		if(endInd > 0){
			fileUrl = fileUrl.substring(0, endInd);
		}
		endInd = fileUrl.lastIndexOf(".");
		extName = fileUrl.substring(endInd);
		return extName;
	}
	
	public static void main(String[] args) {
		DownloadScheduler t = new DownloadScheduler();
		String fileUrl = "http://fdfs.xmcdn.com/group4/M09/65/74/wKgDtFQ6rvqhHXcLABLV8fT82wM019.jpg";
		System.out.println(t.getExtName(fileUrl));
	}
}
