package com.coach.service;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.coach.common.Constants.METHOD;
import com.coach.model.AppVersion;
import com.coach.utils.HttpUtil;
import com.rop.utils.RopUtils;

public class WebCoachServiceTest extends TestCase {
//	public static final String SERVER_URL = "http://localhost:8080/coach/service";
	public static final String SERVER_URL = "http://114.215.145.26/coach/service";
	public static final String APP_KEY = "web_user";
	public static final String APP_SECRET = "dUnogGHrvDYnowxANsk063EVmxepTJU2TbfzX9";
	public static final String VERSION = "V1.0.0";
	public static final String SESSION_ID = "100BBF85-5CC1-440A-B43A-7C06E2EC7653";
	
	public void testSignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_IN.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "E10ADC3949BA59ABBE56E057F20F883E");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	
	/*public void testGetAllList() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getAllList"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testDeleteMemberInBatch() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.deleteMemberInBatch"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("memberIds", "1, 2");
    	map.put("teamId", 2+"");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
/*	public void testChangeMemberTeamInBatch() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.changeMemberTeamInBatch"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("memberIds", "1, 2");
    	map.put("targetTeamId", 2+"");
    	map.put("teamId", 2+"");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
}
