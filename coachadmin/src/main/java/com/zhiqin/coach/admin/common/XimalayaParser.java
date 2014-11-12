package com.zhiqin.coach.admin.common;

import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dto.TaskDTO;

public class XimalayaParser extends MyHtmlParser {
	
	private static final String downloadUrl = "";

	@Override
	public TaskDTO parse(String htmlUrl) {
		TaskDTO dto = new TaskDTO();
		dto.setSourceFrom(0);
		dto.setStatus(TASK_STATUS.DRAFT.getValue());
		dto.setUrl(htmlUrl);
		return dto;
	}

}
