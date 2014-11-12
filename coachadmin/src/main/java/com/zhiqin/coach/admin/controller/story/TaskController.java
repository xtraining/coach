package com.zhiqin.coach.admin.controller.story;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_SOURCE_FROM;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;
import com.zhiqin.coach.admin.service.TaskService;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/story/task/")
public class TaskController extends BaseController{
	@Resource
	private TaskService taskService;
	
	@RequestMapping("list")
	public String list(Model model, PageInfoDTO pageInfo) {
		Long totalNum = taskService.getTotalNum();
		List<TaskDTO> list = taskService.getTaskList(pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/task-list";
	}
	
	@RequestMapping(value = "add")
	public String add(int sourceFrom,Model model) {
		switch (sourceFrom) {
		case 0:
			return "/story/task-add-xmly";

		default:
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("create")
	public String create(int sourceFrom, String url, Model model){
		taskService.create(sourceFrom, url);
		return "success";
	}
	
	@RequestMapping(value = "detail")
	public String detail(int sourceFrom, int taskId, PageInfoDTO pageInfo, Model model) {
		switch (sourceFrom) {
		case 0:{
			Long totalNum = taskService.getDownloadTaskTotalNum(taskId);
			List<DownloadTaskDTO> list = taskService.getDownloadTaskList(taskId, pageInfo);
			model.addAttribute("responseList", list); 
			model.addAttribute("totalCount", totalNum+"");
			model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
			return "/story/task-detail-xmly";
		}
		default:
			return null;
		}
	}
}