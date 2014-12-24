package com.zhiqin.coach.admin.controller.sms;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.service.SendService;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/sms/send/")
public class SendController extends BaseController{
	@Resource
	private ContactService contactService;
	@Resource
	private SendService sendService;
	@RequestMapping("list")
	public String list(Model model, PageInfoDTO pageInfo) {
		Long totalNum = sendService.getTotalNum();
		List<SendTaskDTO> list = sendService.getSendTaskList(pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/sms/send-list";
	}
	
	@RequestMapping(value = "add")
	public String add(Model model) {
		List<AreaDTO> areaList = contactService.getProvinceList();
		List<String> spNameList = contactService.getSpNameList();
		SearchContactDTO searchDto = new SearchContactDTO();
		Long totalNum = contactService.getTotalNum(searchDto);
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("areaList", areaList);
		model.addAttribute("spNameList", spNameList);
		return "/sms/send-add";
	}
	
	@ResponseBody
	@RequestMapping("create")
	public String create(SendTaskDTO sendTask, Model model){
		sendService.create(sendTask);
		return "success";
	}
	
	@RequestMapping("subtaskList")
	public String subtaskList(int sendTaskId, Model model, PageInfoDTO pageInfo) {
		Long totalNum = sendService.getSubtaskTotalNum(sendTaskId);
		List<SendSubtaskDTO> list = sendService.getSendSubTaskList(sendTaskId, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("sendTaskId", sendTaskId); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/sms/send-subtask-list";
	}
}