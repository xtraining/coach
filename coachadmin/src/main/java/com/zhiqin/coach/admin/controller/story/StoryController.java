package com.zhiqin.coach.admin.controller.story;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.CoachService;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/story/story/")
public class StoryController extends BaseController{
	@Resource
	private CoachService coachService;
	
	@RequestMapping("menu")
	public String index() {
		return "/story/story-menu";
	}
	
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, @ModelAttribute("searchDto")SearchCoachDTO searchDto, PageInfoDTO pageInfo) {
		Long totalNum = coachService.getTotalNum(searchDto);
		List<CoachDTO> list = coachService.getCoachList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/coach/coach-list";
	}
	
	@RequestMapping("courseList")
	public String courseList(Model model, @ModelAttribute("searchDto")SearchCourseDTO searchDto, Long coachId, PageInfoDTO pageInfo) {
		if(coachId != null && coachId > 0){
			searchDto.setCoachId(coachId);
		}
		Long totalNum = coachService.getCourseTotalNum(searchDto);
		List<CourseDTO> list = coachService.getCourseList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/coach/coach-course-list";
	}
	
	@RequestMapping("unbindCoachList")
	public String unbindCoachList(Model model, @ModelAttribute("searchDto")SearchOrgDTO searchDto, Long coachId, PageInfoDTO pageInfo) {
		Long totalNum = coachService.getUnbindCoachTotalNum(searchDto);
		List<CoachDTO> list = coachService.getUnbindCoachList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/coach/coach-unbind-list";
	}

}