package com.zhiqin.coach.admin.controller.story;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
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
	
	@RequestMapping("add")
	public String add() {
		return "/story/task-add-xmly";
	}
	
	@ResponseBody
	@RequestMapping("create")
	public String create(int soureFrom, String url){
		taskService.create(soureFrom, url);
		return "success";
	}
	

}