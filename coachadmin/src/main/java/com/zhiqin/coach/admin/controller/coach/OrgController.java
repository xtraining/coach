package com.zhiqin.coach.admin.controller.coach;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.OrgService;
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
	public String create(OrgDTO org, HttpServletRequest request){
		Long orgId = orgService.getOrgIdByName(org.getOrgName());
		if(orgId != null && orgId > 0){
			return "input";
		}
		orgService.create(org);
		return "success";
	}
	
	@RequestMapping("detail")
	public String detail(Model model, Long orgId){
		OrgDTO org = orgService.getOrgById(orgId);
		model.addAttribute("editObj", org);
		return "/coach/org-detail";
	}
	
	@ResponseBody
	@RequestMapping("update")
	public String update(OrgDTO org, HttpServletRequest request){
		Long orgId = orgService.getOrgIdByName(org.getOrgName());
		if(orgId != null && orgId > 0 && orgId.longValue() != org.getOrgId().longValue()){
			return "input";
		}
		orgService.update(org);
		return "success";
	}

	@RequestMapping("delete")
	public String delete(Long orgId, HttpServletResponse response){
		orgService.deleteById(orgId);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("机构管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	@RequestMapping("coachList")
	public String coachList(Model model, Long orgId, PageInfoDTO pageInfo){
		Long totalNum = orgService.getCoachTotalNum(orgId);
		List<CoachDTO> list = orgService.getCoachByOrgId(orgId, pageInfo);
		model.addAttribute("responseList", list);
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("orgId", orgId);
		return "/coach/org-coach-list";
	}
	
	@RequestMapping("addCoach")
	public String addCoach(Model model, Long orgId){
		model.addAttribute("orgId", orgId);
		return "/coach/org-coach-add";
	}
	
	@ResponseBody
	@RequestMapping("createCoach")
	public String createCoach(CoachDTO coach, HttpServletRequest request){
		Long oldOrgCoachId = orgService.getOrgCoachByIdNumberAndType(coach);
		if(oldOrgCoachId != null && oldOrgCoachId > 0){
			return "input";
		}
		orgService.createCoach(coach);
		return "success";
	}
	 
	
	@RequestMapping("coachDetail")
	public String coachDetail(Model model, Long orgCoachId, Long orgId){
		CoachDTO coach = orgService.getOrgCoachById(orgCoachId);
		coach.setOrgId(orgId);
		model.addAttribute("editObj", coach);
		return "/coach/org-coach-detail";
	}
	
	@ResponseBody
	@RequestMapping("updateCoach")
	public String updateCoach(CoachDTO coach, HttpServletRequest request){
		Long oldOrgCoachId = orgService.getOrgCoachByIdNumberAndType(coach);
		if(oldOrgCoachId != null && oldOrgCoachId > 0 && oldOrgCoachId.longValue() != coach.getOrgCoachId().longValue()){
			return "input";
		}
		orgService.updateCoach(coach);
		return "success";
	}
	
	@RequestMapping("courseList")
	public String courseList(Model model, SearchCourseDTO searchDto, PageInfoDTO pageInfo){
		Long totalNum = orgService.getCourseTotalNum(searchDto);
		List<CourseDTO> list = orgService.getCourseByOrgId(searchDto, pageInfo);
		model.addAttribute("responseList", list);
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("searchDto", searchDto);
		return "/coach/org-course-list";
	}
	
	@RequestMapping("addCourse")
	public String addCourse(Model model, Long orgId){
		model.addAttribute("orgId", orgId);
		return "/coach/org-course-add";
	}
	
	@ResponseBody
	@RequestMapping("createCourse")
	public String createCourse(CourseDTO course, HttpServletRequest request){
		orgService.createCourse(course);
		return "success";
	}
	
	@RequestMapping("assignCourse")
	public String assignCourse(Model model, Long orgId, Long courseId){
		List<CoachDTO> list = orgService.getBindCoachListById(orgId);
		model.addAttribute("responseList", list);
		model.addAttribute("courseId", courseId);
		return "/coach/org-course-assign-coach";
	}
	
	@ResponseBody
	@RequestMapping("saveAssign")
	public String saveAssign(Model model, Long courseId, Long coachId){
		orgService.assignCourse(coachId, courseId);
		return "success";
	}
}