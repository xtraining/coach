package com.coach.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.coach.request.v2.CheckSyncData;
import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncData;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncData;
import com.coach.request.v2.TeamSyncDataList;
import com.coach.utils.HttpUtil;
import com.coach.utils.JsonBinder;
import com.rop.utils.RopUtils;

public class CoachServiceTest extends TestCase {
	public static final String SERVER_URL = "http://localhost:8080/coach/service";
//	public static final String SERVER_URL = "http://114.215.145.26/coach/service";
	public static final String APP_KEY = "iphone_user";
	public static final String APP_SECRET = "LlnZA8cql4liN4CvjGL5GfwhCh7fwWGE";
	public static final String VERSION = "V1.0.0";
	public static final String SESSION_ID = "100BBF85-5CC1-440A-B43A-7C06E2EC7653";
	
	/*public void testCheckVersionNum() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "coach.checkVersion"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("versionNum", "v1.0.0.10");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
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
	/*
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
	*/
	

/*	public void testQrcodeSignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", "web_user"); //第二个参数为AppKey
    	map.put("v", "1.0");
    	map.put("format", "json");
		map.put("method", "coach.qrcodeSignIn"); 
    	map.put("sessionId", "29951461-2125-45E2-A9B0-C7A2772C63FC");
    	map.put("coachId", "29");
    	String sign = RopUtils.sign(map, "dUnogGHrvDYnowxANsk063EVmxepTJU2TbfzX9"); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	public void testSyncDataService() {
		TeamSyncDataList t = new TeamSyncDataList();
		TeamSyncData t1 = new TeamSyncData();
		t1.setAppTeamId(1);
		t1.setOperationTime("2014-08-08 11:30:40");
		t1.setOperationType(1);
		t1.setTeamId(54L);
		t1.setTeamName("new Name");
		List<TeamSyncData> list1 = new ArrayList<TeamSyncData>();
		list1.add(t1);
		t.setTeamDataList(list1);
		
		MemberSyncDataList m = new MemberSyncDataList();
		MemberSyncData m1 = new MemberSyncData();
		m1.setAppTeamId(3);
		m1.setAppMemberId(3);
		m1.setOperationTime("2014-08-08 11:30:40");
		m1.setOperationType(0);
		m1.setMemberId(60L);
		m1.setMemberName("zzp");
		m1.setPhoneNumber("1367777777");
		m1.setTeamId(30L);
		List<MemberSyncData> list2 = new ArrayList<MemberSyncData>();
		list2.add(m1);
		m.setMemberDataList(list2);
		
		CheckSyncDataList c = new CheckSyncDataList();
		CheckSyncData s1 = new CheckSyncData();
		s1.setAppCheckId(3);
		s1.setTeamCheckId(40L);
		s1.setOperationTime("2014-08-08 11:30:40");
		s1.setOperationType(0);
		s1.setAttendMemberId("3, 4, 5");
		s1.setLongitude(36.323D);
		s1.setLatitude(124.323D);
		List<CheckSyncData> list3 = new ArrayList<CheckSyncData>();
		list3.add(s1);
		c.setCheckDataList(list3);
		
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "coach.syncData"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("coachId", "29");
    	map.put("teams", JsonBinder.buildNormalBinder().toJson(t));
    	map.put("members", JsonBinder.buildNormalBinder().toJson(m));
    	map.put("checks", JsonBinder.buildNormalBinder().toJson(c));
    	map.put("sessionId", SESSION_ID);
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	
}
