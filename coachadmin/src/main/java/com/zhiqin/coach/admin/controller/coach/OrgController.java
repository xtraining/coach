package com.zhiqin.coach.admin.controller.coach;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.entity.UserRoles;
import com.zhiqin.coach.admin.service.OrgService;
import com.zhiqin.coach.admin.util.JsonBinder;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/coach/org/")
public class OrgController extends BaseController{
	@Resource
	private OrgService orgService;
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, @ModelAttribute("searchDto")SearchOrgDTO searchDto, PageInfoDTO pageInfo) {
		Long totalNum = orgService.getTotalNum(searchDto);
		List<OrgDTO> list = orgService.getOrgList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/coach/org-list";
	}
	
	@RequestMapping("add")
	public String add(){
		return "/coach/org-add";
	}

	@ResponseBody
	@RequestMapping("create")
	public String create(OrgDTO org){
		orgService.create(org);
		return "success";
	}
	/**
	 * 保存用户分配角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allocation")
	public String allocation(Model model,UserRoles userRoles){
		String errorCode = "1000";
		try {
		} catch (Exception e) {
			e.printStackTrace();
			errorCode="1001";
		}
		return errorCode;
	}
}