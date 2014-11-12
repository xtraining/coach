package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;

public interface TaskService{

	Long getTotalNum();

	List<TaskDTO> getTaskList(PageInfoDTO pageInfo);

	void create(int soureFrom, String url);

	Long getDownloadTaskTotalNum(int taskId);

	List<DownloadTaskDTO> getDownloadTaskList(int taskId, PageInfoDTO pageInfo);

}
