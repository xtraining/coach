package com.zhiqin.coach.admin.common;

import java.util.List;

import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;

public abstract class MyHtmlParser {
	public enum DESC{
		INIT("正在解析");
		
		String value = "";
		private DESC(String str){
			value = str;
		}
		protected String getValue(){
			return value;
		}
	}
	public abstract TaskDTO getTask(String url);
	public abstract List<DownloadTaskDTO> getDownloadTask(TaskDTO task);
}
