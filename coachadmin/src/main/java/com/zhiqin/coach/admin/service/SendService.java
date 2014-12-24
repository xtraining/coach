package com.zhiqin.coach.admin.service;

import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;



public interface SendService{

	void create(SendTaskDTO sendTask);

	Long getTotalNum();

	List<SendTaskDTO> getSendTaskList(PageInfoDTO pageInfo);

	Long getSubtaskTotalNum(int sendTaskId);

	List<SendSubtaskDTO> getSendSubTaskList(int sendTaskId, PageInfoDTO pageInfo);


}
