package com.coach.mvc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coach.util.HttpUtil;
import com.coach.util.RopUtils;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/coachweb")
public class CoachController extends BaseController{
	
	@RequestMapping(value = "/getAllList")
	@ResponseBody
	public String getAllList(Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.getAllList"); 
    	map.put("sessionId", getSessionContainer(request).getSessionId());
    	map.put("coachId", getSessionContainer(request).getCoachId()+"");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.post(SERVER_URL, map);
    	return response;
	}
	
	@RequestMapping(value = "/getList")
	@ResponseBody
	public String getList(int status, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.getList"); 
    	map.put("sessionId", getSessionContainer(request).getSessionId());
    	map.put("coachId", getSessionContainer(request).getCoachId()+"");
    	map.put("status", status + "");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}
	
	@RequestMapping(value = "/createTeam", method = RequestMethod.POST)
	@ResponseBody
	public String createTeam(String teamName, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.createTeam"); 
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("name", teamName);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeStatus(String teamId, int status, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.changeStatus"); 
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("teamId", teamId);
		map.put("status", status + "");
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}

	@RequestMapping(value = "/updateTeam", method = RequestMethod.POST)
	@ResponseBody
	public String updateTeam(String teamName, String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.updateTeam"); 
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("teamId", teamId);
		map.put("name", teamName);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}

	@RequestMapping(value = "/getMemberList")
	@ResponseBody
	public String getMemberList(String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.getMemberList"); 
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("teamId", teamId);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
	    return response;
	}
	
	@RequestMapping(value = "/getMemberDetail")
	@ResponseBody
	public String getMemberDetail(String teamId, String memberId, String phoneNumber, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "team.getMemberDetail"); 
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("teamId", teamId);
		map.put("memberId", memberId);
		map.put("phoneNumber", phoneNumber);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
	    return response;
	}
	
	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	@ResponseBody
	public String addMember(String[] phoneNumber, String[] memberName, String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		if(phoneNumber != null && phoneNumber.length > 0 && memberName != null && memberName.length > 0){
			for(int i = 0; i < phoneNumber.length;i++){
				Map<String, String> map = getParamMap();
				map.put("method", "team.addMember"); 
				map.put("sessionId", getSessionContainer(request).getSessionId());
				map.put("coachId", getSessionContainer(request).getCoachId()+"");
				map.put("teamId", teamId);
				map.put("phoneNumber", phoneNumber[i]);
				map.put("memberName", memberName[i]);
				String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
				map.put("sign", sign);
				String response = HttpUtil.post(SERVER_URL, map);
			}
			return "0";
		}
		return "";
	}


	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	@ResponseBody
	public String updateMember(String phoneNumber, String memberName, String memberId, String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("method", "team.updateMember"); 
		map.put("teamId", teamId);
		map.put("memberId", memberId);
		map.put("memberName", memberName);
		map.put("phoneNumber", phoneNumber);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}
	
	@RequestMapping(value = "/deleteMemberInBatch", method = RequestMethod.POST)
	@ResponseBody
	public String deleteMemberInBatch(String memberIds, String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("method", "team.deleteMemberInBatch"); 
		map.put("memberIds", memberIds);
		map.put("teamId", teamId);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}

	@RequestMapping(value = "/changeMemberTeamInBatch", method = RequestMethod.POST)
	@ResponseBody
	public String changeMemberTeamInBatch(String memberIds, String targetTeamId, String teamId, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("sessionId", getSessionContainer(request).getSessionId());
		map.put("coachId", getSessionContainer(request).getCoachId()+"");
		map.put("method", "team.changeMemberTeamInBatch"); 
		map.put("memberIds", memberIds);
		map.put("targetTeamId",targetTeamId);
		map.put("teamId", teamId);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		return response;
	}
	
	@RequestMapping(value = "/toMain")
	public String toMain(Model model) {
		return "main";
	}
}
