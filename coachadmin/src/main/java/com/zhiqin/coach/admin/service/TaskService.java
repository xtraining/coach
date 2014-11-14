package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
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

}
