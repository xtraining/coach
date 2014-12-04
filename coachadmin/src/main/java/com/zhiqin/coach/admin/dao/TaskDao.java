package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;


public interface TaskDao extends BaseDao{

	Long getTotalNum();

	List<TaskDTO> getTaskList(PageInfoDTO pageInfo);

	void create(TaskDTO dto);

	void saveDownloadTask(DownloadTaskDTO downloadDto);

	TaskDTO getById(Integer taskId);

	void updateStatus(Integer taskId, TASK_STATUS failure);

	Long getDownloadTaskTotalNum(int taskId);

	List<DownloadTaskDTO> getDownloadTaskList(int taskId, PageInfoDTO pageInfo);

	Long getDownloadingTaskNum();

	List<DownloadTaskDTO> getDownloadTask(int maxNum);

	void updateDownloadStatus(Long downloadTaskId, DOWNLOAD_TASK_STATUS status);

	void updateDownloadStatus(Long downloadTaskId, DOWNLOAD_TASK_STATUS status,
			String fileName);

	void deleteByIds(String ids);

	List<ArtifactDTO> getArtifactByTaskId(int taskId);

	void updateStatus(List<Long> downloadIdList, int value);

	List<ArtifactDTO> getArtifactByDownloadTaskId(int downloadTaskId);



}
