package com.zhiqin.coach.admin.controller.story;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.ResponseDTO;
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
	private TopService topSerivce;
	
	@RequestMapping("list")
	public String list(Model model, String name, PageInfoDTO pageInfo) {
		Long totalNum = topSerivce.getTopTotalNum(name);
		List<TopDTO> list = topSerivce.getTopList(name, pageInfo);
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
		return "/story/top-edit";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(TopDTO dto, @RequestParam MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response) throws IOException{
		topSerivce.create(dto);
		PrintWriter out = response.getWriter();  
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload"); 
		if(imageFile != null && !imageFile.isEmpty() && imageFile.getSize() > 0){
	        try {
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(realPath, imageFile.getOriginalFilename()));
				out.print("success");
			} catch (IOException e) {
				log.error("file upload error : " + e);
				out.print("error");
	
			} 
		}
		out.print("success");
	}
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		topSerivce.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("推荐内容");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
}