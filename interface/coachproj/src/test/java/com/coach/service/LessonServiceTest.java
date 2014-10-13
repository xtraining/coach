package com.coach.service;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.coach.common.Constants.METHOD;
import com.coach.model.AppVersion;
import com.coach.utils.HttpUtil;
import com.rop.utils.RopUtils;

public class LessonServiceTest extends TestCase {
	public static final String SERVER_URL = "http://localhost/coach/service";
	public static final String APP_KEY = "iphone_user";
	public static final String APP_SECRET = "LlnZA8cql4liN4CvjGL5GfwhCh7fwWGE";
	public static final String VERSION = "V0.5.0.0";

	
	/*public void testGetOneWeekLesson(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_ONE_WEEK_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("date", "2014-02-24");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetLessonDetail(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_LESSON_DETAIL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("lessonId", "400");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetRecentLesson(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_RECENT_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("startDate", "2014-02-26");
    	map.put("days", "5");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
/*	public void testGetLessonMemberList(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_LESSON_MEMBER_LIST.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("lessonId", "271");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	
	/*public void testGetLessonMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_LESSON_MEMBER.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("lessonId", "271");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	*/
	/*public void testAddPersonal(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.ADD_PERSONAL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("name", "life");
    	map.put("address", "address");
    	map.put("longitude", "4");
    	map.put("latitude", "271");
    	map.put("startTime", "2014-09-10 10:30:00");
    	map.put("endTime", "2014-09-10 20:00:00");
    	map.put("alertSwitch", "0");
    	map.put("remarks", "remarks");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testAddLesson(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.ADD_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("name", "lesson");
    	map.put("address", "address");
    	map.put("longitude", "4");
    	map.put("latitude", "271");
    	map.put("startTime", "2014-09-10 10:30:00");
    	map.put("endTime", "2014-09-10 20:00:00");
    	map.put("alertSwitch", "0");
    	map.put("remarks", "remarks"); 
    	map.put("courseId", "19");
    	map.put("groundName", "groundName");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testCheckMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.CHECK_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("lessonId", "271");
    	map.put("attendMemberId", "4, 3");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetCheckLesson(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_CHECK_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("pageNumber", 1+"");
    	map.put("pageSize", 10+"");
    	map.put("coachId", "7");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
/*	public void testDeleteLesson(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.DELETE_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("lessonId", "271");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
}
