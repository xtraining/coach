package com.zhiqin.coach.admin.controller.story;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.CategoryService;
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
@RequestMapping("/story/category/")
public class CategoryController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(CategoryController.class);
	@Resource
	private CategoryService categorySerivce;
	
	@RequestMapping("list")
	public String list(Model model, String name, PageInfoDTO pageInfo) {
		Long totalNum = categorySerivce.getCategoryTotalNum(name);
		List<CategoryDTO> list = categorySerivce.getCategoryList(name, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("name", name); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/category-list";
	}
	
	@RequestMapping(value = "add")
	public String add(Model model) {
		return "/story/category-add";
	}
	
	
	@RequestMapping(value = "edit")
	public String edit(int categoryId, Model model) {
		CategoryDTO dto = categorySerivce.getById(categoryId);
		model.addAttribute("editObj", dto); 
		return "/story/category-edit";
	}
	
	@RequestMapping(value = "update")
	public void update(CategoryDTO dto, Model model, HttpServletResponse response) throws IOException {
		categorySerivce.update(dto);
		PrintWriter out = response.getWriter();  
		out.print("success");
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		categorySerivce.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("图库管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(CategoryDTO dto, HttpServletRequest request, HttpServletResponse response) throws IOException{
		categorySerivce.create(dto);
		PrintWriter out = response.getWriter();  
		out.print("success");
	}
}