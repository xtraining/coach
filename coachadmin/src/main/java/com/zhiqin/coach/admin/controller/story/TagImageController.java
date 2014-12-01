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
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;
import com.zhiqin.coach.admin.service.ImageService;
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
@RequestMapping("/story/tagimage/")
public class TagImageController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(TagImageController.class);
	@Resource
	private TagService tagSerivce;
	@Resource
	private ImageService imageService;
	
	@RequestMapping("list")
	public String list(Model model, SearchTagImageDTO searchDto, PageInfoDTO pageInfo) {
		if(searchDto.getTagId() != null){
			TagDTO dto = tagSerivce.getById(searchDto.getTagId().intValue());
			if(dto != null){
				searchDto.setTagName(dto.getName());
			}
		}
		Long totalNum = imageService.getTagImageTotalNum(searchDto);
		List<ArtifactImageDTO> list = imageService.getTagImageList(searchDto, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("searchDto", searchDto); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/tagimage-list";
	}
	
	@RequestMapping("assignTag")
	public String assignTag(long imageId, Model model) {
		List<TagDTO> tagList = imageService.getTagByImageId(imageId);
		model.addAttribute("tagList", tagList); 
		model.addAttribute("imageId", imageId); 
		return "/story/tagimage-assign-tag";
	}
	
	
	@RequestMapping(value = "add")
	public String add(Model model) {
		return "/story/tagimage-add";
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		imageService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("图库管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(TagArrayDTO items, @RequestParam MultipartFile[] imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		PrintWriter out = response.getWriter();  
		if(items == null || items.getTag() == null || items.getTag().length == 0){
			out.print("input");
			return;
		}
		imageService.createFiles(items, imageFile);
		out.print("success");
	}
	
	@RequestMapping(value="saveAssignTag", method=RequestMethod.POST)  
	public void saveAssignTag(TagArrayDTO items, Long imageId, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		PrintWriter out = response.getWriter();  
		if(items == null || items.getTag() == null || items.getTag().length == 0){
			out.print("input");
			return;
		}
		imageService.saveAssignTag(items, imageId);
		out.print("success");
	}
}