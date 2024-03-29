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
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.service.ContactService;
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
@RequestMapping("/sms/contact/")
public class ContactController extends BaseController{
	@Resource
	private ContactService contactService;
	@RequestMapping("list")
	public String list(SearchContactDTO searchDto, Model model, PageInfoDTO pageInfo) {
		List<AreaDTO> provinceList = contactService.getProvinceList();
		List<String> spNameList = contactService.getSpNameList();
		List<String> tagNameList = contactService.getTagNameList();
		if(searchDto.getCityId() != null && searchDto.getCityId() > 0){
			searchDto.setAreaId(searchDto.getCityId());
		} else if(searchDto.getProvinceId() != null){
			searchDto.setAreaId(searchDto.getProvinceId());
		}
		Long totalNum = contactService.getTotalNum(searchDto);
		List<ContactDTO> list = contactService.getContactList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("searchDto", searchDto); 
		model.addAttribute("tagNameList", tagNameList);
		model.addAttribute("provinceList", provinceList);
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
	public String create(String tagName, String phoneNumbers, Model model){
		contactService.create(tagName, phoneNumbers);
		return "success";
	}
	
	@RequestMapping(value="import", method=RequestMethod.POST)  
	public void importContact(String tagName, @RequestParam MultipartFile excelFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException, BiffException{
		PrintWriter out = response.getWriter();  
		contactService.importContact(tagName, excelFile);
		out.print("success");
	}
	
	@ResponseBody
	@RequestMapping(value="getContactCount")  
	public String getContactCount(SearchContactDTO searchDto, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException, BiffException{
		Long totalNum = contactService.getTotalNum(searchDto);
		return totalNum+"";
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		contactService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("号码管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
}