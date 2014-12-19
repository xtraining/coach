package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;

public interface TaskService{

	Long getTotalNum();

	List<TaskDTO> getTaskList(PageInfoDTO pageInfo);

	void create(int soureFrom, String url);

	Long getDownloadTaskTotalNum(int taskId);

	List<DownloadTaskDTO> getDownloadTaskList(int taskId, PageInfoDTO pageInfo);

	Long getDownloadingTaskNum();

	List<DownloadTaskDTO> getDownloadTask(int i);

	void updateDownloadStatus(Long id, DOWNLOAD_TASK_STATUS status);

	void updateDownloadStatus(Long id, DOWNLOAD_TASK_STATUS success,
			String voiceFileName);

	void deleteByIds(String ids);

	void saveAccept(int taskId, String downloadTaskIds, int categoryId);

	void redownloadAll(int taskId);

	void saveAccept(String taskIds, int categoryId);

}
