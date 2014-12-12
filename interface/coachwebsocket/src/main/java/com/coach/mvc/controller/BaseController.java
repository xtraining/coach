package com.coach.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.coach.util.Config;
import com.coach.util.Constants;
import com.coach.util.SessionContainer;

public class BaseController {
	protected static final String SERVER_URL = Config.getProperty("coach_url");
//	public static final String SERVER_URL = "http://114.215.145.26/coach/service";
	protected static final String APP_KEY = "web_user";
	protected static final String APP_SECRET = "dUnogGHrvDYnowxANsk063EVmxepTJU2TbfzX9";
	protected static final String VERSION = "V1.0.0";
	protected Map<String, String> getParamMap(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("v", "1.0");
    	map.put("format", "json");
    	return map;
	}
	
	public SessionContainer getSessionContainer(HttpServletRequest request){
		HttpSession session = request.getSession();
		SessionContainer obj = (SessionContainer) session.getAttribute(Constants.SESSION_CONTAINER);
		return obj;
		
	}
}
