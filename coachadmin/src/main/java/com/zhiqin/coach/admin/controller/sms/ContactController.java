package com.zhiqin.coach.admin.controller.sms;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.service.ContactService;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/sms/contact/")
public class ContactController extends BaseController{
	@Resource
	private ContactService contactService;
	@RequestMapping("list")
	public String list(SearchContactDTO searchDto, Model model, PageInfoDTO pageInfo) {
		Long totalNum = contactService.getTotalNum(searchDto);
		List<ContactDTO> list = contactService.getContactList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/sms/contact-list";
	}
	
	@RequestMapping(value = "add")
	public String add(int sourceFrom,Model model) {
		switch (sourceFrom) {
		case 0:
			return "/sms/contact-add";
		case 1:
			return "/sms/contact-add-excel";
		default:
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("create")
	public String create(String phoneNumbers, Model model){
		contactService.create(phoneNumbers);
		return "success";
	}
	
}