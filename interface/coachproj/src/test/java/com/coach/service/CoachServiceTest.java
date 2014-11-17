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
	public static final String VERSION = "V0.5.0.0";

	@Test
	public void testSignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_IN.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("openuuid", "2");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "123456");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	/*@Test
	public void testUpdateLastAccessTime() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_LAST_ACCESS_TIME.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("coachId", "7");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
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
    	map.put("phoneNumber", "13382170135");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*@Test
	public void testSignUp() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.SIGN_UP.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("phoneNumber", "13636426042");
    	map.put("password", "123456");
    	map.put("vcode", "109601");
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
	
	/*public void testThirdPartySignIn() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.THIRD_PARTY_SIGN_IN.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("thirdPartyId", "thirdPartyId");
    	map.put("type", "1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	*/
	/*public void testThirdPartySignUp() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.THIRD_PARTY_SIGN_UP.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("thirdPartyId", "thirdPartyId4");
    	map.put("type", "1");
    	map.put("gender", "1");
    	map.put("avatarUrl", "http://url");
    	map.put("phoneNumber", "13636426046");
    	map.put("password", "123456");
    	map.put("vcode", "042777");
    	map.put("thirdPartyNickname", "thirdPartyNickname");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetResetPwdVcode() {
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
    	map.put("vcode", "042777");
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
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "6");
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
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "6");
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
	
	/*public void testGetProfile() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_PROFILE.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "7954FE41-3B84-4454-89DE-884D1BD59A9D");
    	map.put("coachId", "17");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testBindThirdParty() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.BIND_THIRD_PARTY.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "7954FE41-3B84-4454-89DE-884D1BD59A9D");
    	map.put("coachId", "17");
    	map.put("thirdPartyId", "thirdPartyId");
    	map.put("type", "1");
    	map.put("gender", "1");
    	map.put("avatarUrl", "avatarUrl1");
    	map.put("thirdPartyNickname", "thirdPartyNickname");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	*/
	/*public void testUnbindThirdParty() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UNBIND_THIRD_PARTY.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("type", "2");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetProfileDetail() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_PROFILE_DETAIL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "89D7C136-FA07-4849-B96A-CDC0C7753FEC");
    	map.put("coachId", "17");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testUpdateProfileDetail() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_PROFILE_DETAIL.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "F80315AB-FBB4-45F3-BF65-0FCD9AF46063");
    	map.put("coachId", "26");
    	map.put("areaCode", "110000");
    	map.put("gender", "1");
    	map.put("name", "张志鹏");
    	map.put("email", "a@a.com");
    	map.put("idType", "0");
    	map.put("idNumber", "642101198011270014");
    	map.put("birthday", "2003-11-3");
    	map.put("description", "description");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testUploadAvatar() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPLOAD_AVATAR.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "CD47C554-2ADF-4713-851A-5FF928C14A39");
    	map.put("coachId", "7");
    	map.put("imageExtFileName", "jpg");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testgetBindOrg() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_BIND_ORG.getValue()); 
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
	}*/
	
	/*
	public void testUpdateBindOrg() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.UPDATE_BIND_ORG_STATUS.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "111F0FED-5009-4CF6-B63A-0453D08F4212");
    	map.put("coachId", "24");
    	map.put("orgId", "10");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}
	*/
/*	public void testGetTotalLesson() {
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", METHOD.GET_TOTAL_LESSON.getValue()); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", "111F0FED-5009-4CF6-B63A-0453D08F4212");
    	map.put("coachId", "17");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
}
