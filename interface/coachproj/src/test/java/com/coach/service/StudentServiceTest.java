package com.coach.service;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.coach.common.Constants.METHOD;
import com.coach.model.AppVersion;
import com.coach.utils.HttpUtil;
import com.rop.utils.RopUtils;

public class StudentServiceTest extends TestCase {
//	public static final String SERVER_URL = "http://localhost:8080/coach/service";
	public static final String SERVER_URL = "http://114.215.145.26/coach/service";
	public static final String APP_KEY = "iphone_user";
	public static final String APP_SECRET = "LlnZA8cql4liN4CvjGL5GfwhCh7fwWGE";
	public static final String VERSION = "V0.5.0.0";

	/*public void testGetCourseList(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.STUDENT_GET_COURSE_LIST.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	/*
	public void testSearchMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.STUDENT_SEARCH_MEMBER.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "111F0FED-5009-4CF6-B63A-0453D08F4212");
    	map.put("coachId", "24");
    	map.put("keyword", "s");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.STUDENT_GET_MEMBER.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("courseId", "17");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetMemberDetail(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.STUDENT_GET_MEMBER_DETAIL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "111F0FED-5009-4CF6-B63A-0453D08F4212");
    	map.put("coachId", "24");
    	map.put("memberId", "55");
    	map.put("courseId", "47");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	public void testGetMemberNews(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.STUDENT_GET_MEMBER_NEWS.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "111F0FED-5009-4CF6-B63A-0453D08F4212");
    	map.put("coachId", "24");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	
/*	public void testUpdateMemberDetail(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_MEMBER_DETAIL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("memberId", "7");
    	map.put("memberName", "name");
    	map.put("phoneNumber", "135");
    	map.put("gender", "1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	

	public void testUpdateMemberStatus(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_MEMBER_STATUS.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("courseId", "17");
    	map.put("coachId", "7");
    	map.put("memberId", "7");
    	map.put("status", "1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
}
