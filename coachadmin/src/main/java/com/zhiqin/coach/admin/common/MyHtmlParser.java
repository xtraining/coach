package com.zhiqin.coach.admin.common;

import com.zhiqin.coach.admin.dto.TaskDTO;

public abstract class MyHtmlParser {
	public abstract TaskDTO parse(String htmlUrl);
}
