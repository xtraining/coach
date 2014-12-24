package com.zhiqin.coach.admin.controller.sms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.util.JsonUtils;

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
		List<AreaDTO> areaList = contactService.getProvinceList();
		List<String> spNameList = contactService.getSpNameList();
		Long totalNum = contactService.getTotalNum(searchDto);
		List<ContactDTO> list = contactService.getContactList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("searchDto", searchDto); 
		model.addAttribute("areaList", areaList);
		model.addAttribute("spNameList", spNameList);
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
			return "/sms/contact-add-import";
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
	
	@RequestMapping(value="import", method=RequestMethod.POST)  
	public void importContact(@RequestParam MultipartFile excelFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException, BiffException{
		PrintWriter out = response.getWriter();  
		contactService.importContact(excelFile);
		out.print("success");
	}
	
	@ResponseBody
	@RequestMapping(value="getContactCount")  
	public void getContactCount(SearchContactDTO searchDto, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException, BiffException{
		PrintWriter out = response.getWriter();  
		Long totalNum = contactService.getTotalNum(searchDto);
		out.print(totalNum+"");
	}
}