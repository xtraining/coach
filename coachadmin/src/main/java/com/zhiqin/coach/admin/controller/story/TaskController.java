package com.zhiqin.coach.admin.controller.story;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_SOURCE_FROM;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;
import com.zhiqin.coach.admin.service.CategoryService;
import com.zhiqin.coach.admin.service.TaskService;
import com.zhiqin.coach.admin.util.JsonBinder;
import com.zhiqin.coach.admin.util.JsonUtils;

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
	@Resource
	private CategoryService categoryService;
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
		case 1:
			return "/story/task-add-qtfm";
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
		Long totalNum = taskService.getDownloadTaskTotalNum(taskId);
		List<DownloadTaskDTO> list = taskService.getDownloadTaskList(taskId, pageInfo);
		List<CategoryDTO> categories = categoryService.getCategoryList(null, pageInfo);
		model.addAttribute("categories", categories); 
		model.addAttribute("taskId", taskId); 
		model.addAttribute("sourceFrom", sourceFrom); 
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		switch (sourceFrom) {
		case 0:{
			return "/story/task-detail-xmly";
		}
		case 1:{
			return "/story/task-detail-qtfm";
		}
		default:
			return null;
		}
	}
	
	@RequestMapping("redownload")
	public String redownload(Long downloadTaskId, HttpServletResponse response){
		taskService.updateDownloadStatus(downloadTaskId, DOWNLOAD_TASK_STATUS.DRAFT);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setNavTabId("");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		taskService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("任务管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	
	@RequestMapping("accept")
	public String accept(int taskId, int downloadTaskId, Model model){
		model.addAttribute("taskId", taskId); 
		model.addAttribute("downloadTaskId", downloadTaskId); 
		return "/story/task-accept";
	}
	
	@RequestMapping("saveAccept")
	public void saveAccept(int taskId, String downloadTaskIds, int categoryId, Model model, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();  
		taskService.saveAccept(taskId, downloadTaskIds, categoryId);
		out.print("success");
	}
	
	@RequestMapping("redownloadAll")
	public void redownloadAll(int taskId, int sourceFrom, Model model, HttpServletResponse response) throws IOException{
		taskService.redownloadAll(taskId);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setNavTabId("");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
	}
	
	@RequestMapping("acceptWithCategory")
	public String acceptWithCategory(String taskIds, Model model) {
		PageInfoDTO pageInfo = new PageInfoDTO();
		pageInfo.setPageNum(1);
		pageInfo.setNumPerPage(10000);
		List<CategoryDTO> categories = categoryService.getCategoryList(null, pageInfo);
		model.addAttribute("categories", categories); 
		model.addAttribute("taskIds", taskIds); 
		return "/story/task-assign-category";
	}
	
	@RequestMapping("saveCategory")
	@ResponseBody
	public String saveCategory(String taskIds, int categoryId, Model model, HttpServletResponse response) throws IOException {
		if(categoryId <= 0){
			return "input";
		}
		taskService.saveAccept(taskIds, categoryId);
		return "success";
	}
	
}