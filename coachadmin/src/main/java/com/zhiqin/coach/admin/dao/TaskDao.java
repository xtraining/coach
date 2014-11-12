package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
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



}
