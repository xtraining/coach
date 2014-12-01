package com.zhiqin.coach.admin.controller.story;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.service.ArtifactService;
import com.zhiqin.coach.admin.service.CategoryService;
import com.zhiqin.coach.admin.service.TagService;
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
@RequestMapping("/story/artifact/")
public class ArtifactController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(ArtifactController.class);
	@Resource
	private ArtifactService artifactService;
	@Resource
	private TagService tagService;
	@Resource
	private CategoryService categoryService;
	@RequestMapping("list")
	public String list(Model model, SearchArtifactDTO searchDto, PageInfoDTO pageInfo) {
		if(searchDto.getCategoryId() != null){
			CategoryDTO dto = categoryService.getById(searchDto.getCategoryId().intValue());
			if(dto != null){
				searchDto.setCategoryName(dto.getName());
			}
		}
		if(searchDto.getTagId() != null){
			TagDTO dto = tagService.getById(searchDto.getTagId().intValue());
			if(dto != null){
				searchDto.setTagName(dto.getName());
			}
		}
		Long totalNum = artifactService.getArtifactTotalNum(searchDto);
		List<ArtifactDTO> list = artifactService.getArtifactList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("searchDto", searchDto); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/artifact-list";
	}
	
	@RequestMapping(value = "add")
	public String add(Model model) {
		return "/story/artifact-add";
	}
	
	@RequestMapping(value = "addAlbum")
	public String addAlbum(Model model) {
		return "/story/artifact-add-album";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(ArtifactDTO dto, CategoryArrayDTO categorys, TagArrayDTO tags, @RequestParam MultipartFile listImageFile, @RequestParam MultipartFile mediaFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		PrintWriter out = response.getWriter();  
		if(categorys == null || categorys.getCategory() == null || categorys.getCategory().length == 0){
			out.print("input1");
			return;
		}
		if(tags == null || tags.getTag() == null || tags.getTag().length == 0){
			out.print("input2");
			return;
		}
		artifactService.create(dto, categorys, tags, listImageFile, mediaFile);
		out.print("success");
	}
	
	@RequestMapping("edit")
	public String edit(long artifactId, Model model, SearchArtifactDTO searchDto, PageInfoDTO pageInfo) {
		ArtifactDTO dto = artifactService.getById(artifactId);
		List<TagDTO> tags = artifactService.getTagByArtifactId(artifactId);
		List<CategoryDTO> categories = artifactService.getCategoryByArtifactId(artifactId);
		model.addAttribute("editObj", dto); 
		model.addAttribute("tags", tags); 
		model.addAttribute("categories", categories); 
		if(dto.getType().intValue() == 0){
			List<ArtifactDTO> sublist = artifactService.getSublistById(artifactId);
			model.addAttribute("sublist", sublist); 
			return "/story/artifact-edit-album";
		} else {
			return "/story/artifact-edit";
		}
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)  
	public void update(ArtifactDTO dto, CategoryArrayDTO categorys, TagArrayDTO tags, @RequestParam MultipartFile listImageFile, @RequestParam MultipartFile mediaFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		PrintWriter out = response.getWriter();  
		if(categorys == null || categorys.getCategory() == null || categorys.getCategory().length == 0){
			out.print("input1");
			return;
		}
		if(tags == null || tags.getTag() == null || tags.getTag().length == 0){
			out.print("input2");
			return;
		}
		artifactService.update(dto, categorys, tags, listImageFile, mediaFile);
		out.print("success");
	}
	
	
	@RequestMapping("select")
	public String select(Model model, SearchArtifactDTO searchDto, PageInfoDTO pageInfo) {
		list(model, searchDto, pageInfo);
		return "/story/artifact-select";
	}
	
	@RequestMapping("assignTag")
	public String assignTag(String artifactIds, Model model) {
		PageInfoDTO pageInfo = new PageInfoDTO();
		pageInfo.setPageNum(1);
		pageInfo.setNumPerPage(10000);
		List<TagDTO> list = tagService.getTagList(null, pageInfo);
		model.addAttribute("tagList", list); 
		model.addAttribute("artifactIds", artifactIds); 
		return "/story/artifact-assign";
	}
	
	@RequestMapping("saveAssign")
	@ResponseBody
	public String saveAssign(String artifactIds, int tagId, Model model, HttpServletResponse response) throws IOException {
		if(tagId <= 0){
			return "input";
		}
		artifactService.saveAssign(artifactIds, tagId);
		return "success";
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		artifactService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("故事管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}

}