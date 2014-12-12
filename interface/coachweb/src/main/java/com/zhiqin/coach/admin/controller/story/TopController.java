package com.zhiqin.coach.admin.controller.story;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
import com.zhiqin.coach.admin.dto.ArtifactArrayDTO;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TopDTO;
import com.zhiqin.coach.admin.service.TopService;
import com.zhiqin.coach.admin.util.DateUtils;
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
@RequestMapping("/story/top/")
public class TopController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(TopController.class);
	@Resource
	private TopService topService;
	
	@RequestMapping("list")
	public String list(Model model, String name, PageInfoDTO pageInfo) {
		Long totalNum = topService.getTopTotalNum(name);
		List<TopDTO> list = topService.getTopList(name, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("name", name); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/top-list";
	}
	
	@RequestMapping(value = "add")
	public String add(Model model) {
		String nowTime = DateUtils.dateToyyyyMMddHHmi(new Timestamp(new Date().getTime()));
		model.addAttribute("nowTime", nowTime);
		return "/story/top-add";
	}
	
	@RequestMapping(value = "edit")
	public String edit(int topId, Model model) {
		TopDTO dto = topService.getById(topId);
		List<ArtifactDTO> artifacts = topService.getArtifactByTopId(topId);
		model.addAttribute("editObj", dto);
		model.addAttribute("artifacts", artifacts);
		return "/story/top-edit";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(TopDTO dto, ArtifactArrayDTO artifacts, @RequestParam MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		topService.create(dto, artifacts, imageFile);
		PrintWriter out = response.getWriter();  
		out.print("success");
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)  
	public void update(TopDTO dto, ArtifactArrayDTO artifacts, @RequestParam MultipartFile listImageFile, HttpServletRequest request, HttpServletResponse response) throws IOException, AuthException, JSONException{
		topService.update(dto, artifacts, listImageFile);
		PrintWriter out = response.getWriter();  
		out.print("success");
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		topService.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("推荐内容");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
}