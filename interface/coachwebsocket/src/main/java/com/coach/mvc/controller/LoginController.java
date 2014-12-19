package com.coach.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketHandler;

import com.coach.util.Constants;
import com.coach.util.HttpUtil;
import com.coach.util.JsonBinder;
import com.coach.util.MatrixToImageWriter;
import com.coach.util.RopUtils;
import com.coach.util.SessionContainer;
import com.coach.websocket.handler.DefaultEchoService;
import com.coach.websocket.handler.EchoWebSocketHandler;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Bean
	public EchoWebSocketHandler echoWebSocketHandler() {
		return new EchoWebSocketHandler(echoService());
	}

	@Bean
	public DefaultEchoService echoService() {
		return new DefaultEchoService();
	}
	
	@RequestMapping(value = "/signInSignal")
	public @ResponseBody
	String signInSignal(String token, String sign, String appKey, String coachId, String coachSessionId, Model model) {
		if(org.apache.commons.lang3.StringUtils.equals(appKey, Constants.COACH_APP_KEY)){
			Map<String, String> map = new HashMap<String, String>();
			map.put("token", token);
			map.put("appKey", appKey);
			map.put("coachId", coachId);
			map.put("coachSessionId", coachSessionId);
			String newSign = RopUtils.sign(map, Constants.COACH_SECRET_KEY);
			if(org.apache.commons.lang3.StringUtils.equals(newSign, sign)){
				echoWebSocketHandler().pushToLogin(token, coachId, coachSessionId);
				return 0+"";
			} else {
				System.err.println("非法token" + token);
				return 1+"";
			}
		} else {
			System.err.println("非法appkey" + appKey);
			return 1+"";
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/qrcode")
	public @ResponseBody
	void qrcode(String token, Model model, HttpServletResponse response) throws IOException,
			WriterException {
		int width = 250;
		int height = 250;
		// 二维码的图片格式
		String format = "jpg";
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(token,
				BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		MatrixToImageWriter.writeToStream(bitMatrix, format,
				response.getOutputStream());
	}
	
	
	@RequestMapping(value = "/login")
	public @ResponseBody String login(String jLoginName, String JPassWord, Model model, HttpServletRequest request) {
		Map<String, String> map = getParamMap();
		map.put("method", "coach.signIn"); 
		map.put("phoneNumber", jLoginName);
    	map.put("password", JPassWord);
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.post(SERVER_URL, map);
    	Integer coachId = (Integer) JsonBinder.buildNonDefaultBinder().getValue(response, "coachId");
    	if(coachId != null && coachId > 0){
    		HttpSession session = request.getSession(true);
    		SessionContainer obj = new SessionContainer();
    		obj.setPhoneNumber(jLoginName);
    		obj.setCoachId(coachId);
    		String sessionId = (String) JsonBinder.buildNonDefaultBinder().getValue(response, "sessionId");
    		obj.setSessionId(sessionId);
    		session.setAttribute(Constants.SESSION_CONTAINER, obj);
    		return "success";
    	} else {
    		return "error";
    	}
	}
	
	@RequestMapping(value = "/qrcodeLogin")
	public @ResponseBody String qrcodeLogin(String coachId, String coachSessionId, Model model, HttpServletRequest request) {
		Map<String, String> map = getParamMap();
		map.put("sessionId", coachSessionId);
		map.put("coachId", coachId);
		map.put("method", "coach.qrcodeSignIn"); 
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.post(SERVER_URL, map);
    	Integer realCoachId = (Integer) JsonBinder.buildNonDefaultBinder().getValue(response, "coachId");
    	if(realCoachId != null && realCoachId > 0){
    		String phoneNumber = (String) JsonBinder.buildNonDefaultBinder().getValue(response, "phoneNumber");
    		HttpSession session = request.getSession(true);
    		SessionContainer obj = new SessionContainer();
    		obj.setPhoneNumber(phoneNumber);
    		obj.setCoachId(realCoachId);
    		obj.setSessionId(coachSessionId);
    		session.setAttribute(Constants.SESSION_CONTAINER, obj);
    		return "success";
    	} else {
    		return "error";
    	}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null){
			session.removeAttribute(Constants.SESSION_CONTAINER);
			session.invalidate();
		}
		String token = StringUtils.replace(RopUtils.getUUID(), "-", "");
		model.addAttribute("token", token);
		return "index";
	}
	
	@RequestMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		String token = StringUtils.replace(RopUtils.getUUID(), "-", "");
		model.addAttribute("token", token);
		return "index";
	}
	
	@RequestMapping(value = "/getSignUpVcode")
	@ResponseBody
	public String getSignUpVcode(String phoneNumber, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "coach.getSignUpVcode"); 
		map.put("phoneNumber", phoneNumber);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		Integer flag = (Integer) JsonBinder.buildNonDefaultBinder().getValue(response, "flag");
		if(flag != null && flag == 0){
			return "success";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "/registerUser")
	@ResponseBody
	public String registerUser(String phoneNumber, String password, String vcode, Model model, HttpServletRequest request) throws IOException,
			WriterException {
		Map<String, String> map = getParamMap();
		map.put("method", "coach.signUp"); 
		map.put("phoneNumber", phoneNumber);
		map.put("password", password);
		map.put("vcode", vcode);
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.post(SERVER_URL, map);
		Integer flag = (Integer) JsonBinder.buildNonDefaultBinder().getValue(response, "flag");
		if(flag != null){
			if(flag == 0){
				return "success";
			} else if(flag == 1){
				return "existing";
			} else if(flag == 2){
				return "overdue";
			} else if(flag == 3){
				return "incorrect";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}
	
}
