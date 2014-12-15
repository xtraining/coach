package com.zhiqin.coach.admin.controller.story;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.mail.util.BASE64EncoderStream;
import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.KeywordDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.service.KeywordService;
import com.zhiqin.coach.admin.util.UnicodeUtils;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/story/keyword/")
public class KeywordController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(KeywordController.class);
	@Resource
	private KeywordService keywordSerivce;
	
	@RequestMapping("list")
	public String list(Model model, String name, PageInfoDTO pageInfo) {
		Long totalNum = keywordSerivce.getKeywordTotalNum(name);
		List<KeywordDTO> list = keywordSerivce.getKeywordList(name, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("name", name); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/keyword-list";
	}
	
	@RequestMapping("storyList")
	public String storyList(String name, Model model, PageInfoDTO pageInfo) throws UnsupportedEncodingException {
		name = java.net.URLDecoder.decode(name, "UTF-8");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		Long totalNum = keywordSerivce.getStoryTotalNumByKeyword(name);
		List<ArtifactDTO> list = keywordSerivce.getStoryListByKeyword(name, pageInfo);
		model.addAttribute("responseList", list); 
		model.addAttribute("name", name); 
		model.addAttribute("totalCount", totalNum+"");
		model.addAttribute("currentNum", pageInfo.getPageNum() == null ? 1 : pageInfo.getPageNum());
		return "/story/keyword-story-list";
	}
	/*@RequestMapping(value = "add")
	public String add(Model model) {
		return "/story/keyword-add";
	}
	
	@RequestMapping(value="create", method=RequestMethod.POST)  
	public void create(KeywordDTO dto, HttpServletRequest request, HttpServletResponse response) throws IOException{
		keywordSerivce.create(dto);
		PrintWriter out = response.getWriter();  
		out.print("success");
	}
	
	
	@RequestMapping("delete")
	public String delete(String ids, HttpServletResponse response){
		tagSerivce.deleteByIds(ids);
		ResponseDTO success = new ResponseDTO();
		success.setStatusCode("200");
		success.setMessage("删除成功");
		success.setNavTabId("标签管理");
		JsonUtils.write(response, JsonBinder.buildNormalBinder().toJson(success));
		return null;
	}
	
	@RequestMapping(value = "edit")
	public String edit(long keywordId, Model model) {
		KeywordDTO dto = keywordSerivce.getById(keywordId);
		model.addAttribute("editObj", dto); 
		return "/story/keyword-edit";
	}*/
	
}