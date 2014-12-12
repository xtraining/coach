package com.coach.service;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.coach.common.Constants.METHOD;
import com.coach.model.AppVersion;
import com.coach.utils.HttpUtil;
import com.rop.utils.RopUtils;

public class CoachServiceTest extends TestCase {
	public static final String SERVER_URL = "http://localhost:8080/coach/service";
//	public static final String SERVER_URL = "http://114.215.145.26/coach/service";
	public static final String APP_KEY = "iphone_user";
	public static final String APP_SECRET = "LlnZA8cql4liN4CvjGL5GfwhCh7fwWGE";
	public static final String VERSION = "V1.0.0";
	public static final String SESSION_ID = "100BBF85-5CC1-440A-B43A-7C06E2EC7653";
	/*@Test
	public void testUpdateLastAccessTime() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_LAST_ACCESS_TIME.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("coachId", "20");
    	map.put("sessionId", SESSION_ID);
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*@Test
	public void testUpdateSMSStatus() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "coach.changeSMSStatus"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("coachId", "20");
    	map.put("status", "1");
    	map.put("sessionId", SESSION_ID);
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/

	/*@Test
	public void testGetSignUpVcode() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_SIGN_UP_VCODE.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	/*
	@Test
	public void testSignUp() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_UP.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "123456");
    	map.put("vcode", "478063");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	/*public void testSignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_IN.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "123456");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	
/*	public void testGetResetPwdVcode() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_RESET_PWD_VCODE.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testResetPwd() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.RESET_PASSWORD.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "123456");
    	map.put("confirmPassword", "123456");
    	map.put("vcode", "905580");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testSignOut() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_OUT.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "260A2A7F-D591-404B-8CAA-9339EDEDA734");
    	map.put("coachId", "20");
    	map.put("baiduUserId", "1234562");
    	map.put("baiduChannelId", "1234562");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testBind() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.BIND_BAIDU_PUSH_MESSAGE.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("baiduUserId", "1234562");
    	map.put("baiduChannelId", "1234562");
    	map.put("appVersion", VERSION);
    	map.put("osVersion", "IOS6.1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	public void testScanSignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SCAN_SING_IN.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("token", "BCD0D53F3A9E4FE9833050522DD080C1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	
}
