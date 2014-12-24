package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;





public interface SendTaskDao extends BaseDao{

	void save(SendTaskDTO sendTask);

	void saveSendSubtask(SendSubtaskDTO sendSubtaskDTO);

	void saveSendSubtaskContact(Long sendSubtaskId,
			List<Long> contactIdList);

	Long getTaskTotalNum();

	List<SendTaskDTO> getSendTaskList(PageInfoDTO pageInfo);

	Long getSubtaskTotalNum(int sendTaskId);

	List<SendSubtaskDTO> getSendSubtaskList(int sendTaskId, PageInfoDTO pageInfo);



}
