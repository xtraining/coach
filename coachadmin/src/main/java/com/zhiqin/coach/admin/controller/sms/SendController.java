package com.zhiqin.coach.admin.controller.sms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.service.SendService;
import com.zhiqin.coach.admin.util.Config;
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
		List<String> tagNameList = contactService.getTagNameList();
		SearchContactDTO searchDto = new SearchContactDTO();
		Long totalNum = contactService.getTotalNum(searchDto);
		String productNames = Config.getProperty("sms_product_name");
		String[] productNameArr = StringUtils.split(productNames, ",");
		List<String> productNameList = new ArrayList<String>();
		for(String productName : productNameArr){
			productNameList.add(StringUtils.trimToEmpty(productName));
		}
		model.addAttribute("tagNameList", tagNameList);
		model.addAttribute("productNameList", productNameList);
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("areaList", areaList);
		model.addAttribute("spNameList", spNameList);
		return "/sms/send-add";
	}
	
	@ResponseBody
	@RequestMapping("create")
	public String create(SendTaskDTO sendTask, Model model){
		if(StringUtils.isBlank(sendTask.getProductName())){
			return "input";
		}
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
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		sendService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("短信管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
}