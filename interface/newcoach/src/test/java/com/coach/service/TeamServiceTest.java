package com.coach.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.coach.common.Constants.METHOD;
import com.coach.model.AppVersion;
import com.coach.request.v2.CheckSyncData;
import com.coach.request.v2.CheckSyncDataList;
import com.coach.request.v2.MemberSyncData;
import com.coach.request.v2.MemberSyncDataList;
import com.coach.request.v2.TeamSyncData;
import com.coach.request.v2.TeamSyncDataList;
import com.coach.utils.HttpUtil;
import com.coach.utils.JsonBinder;
import com.rop.utils.RopUtils;

public class TeamServiceTest extends TestCase {
//	public static final String SERVER_URL = "http://localhost:8080/coach/service";
	public static final String SERVER_URL = "http://114.215.145.26:8082/coach/service";
	public static final String APP_KEY = "iphone_user";
	public static final String APP_SECRET = "LlnZA8cql4liN4CvjGL5GfwhCh7fwWGE";
//	public static final String APP_KEY = "web_user";
//	public static final String APP_SECRET = "dUnogGHrvDYnowxANsk063EVmxepTJU2TbfzX9";
	public static final String VERSION = "V0.5.0.0";
	public static final String SESSION_ID = "100BBF85-5CC1-440A-B43A-7C06E2EC7653";
	
	/*public void testGetTeamList(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getList"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "50");
    	map.put("status", "0");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testCreateTeam(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.createTeam"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("name", "篮球二班");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testUpdateTeam(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.updateTeam"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("name", "篮球二班1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testChangeStatus(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.changeStatus"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("status", "-1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetMemberList(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getMemberList"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
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
    	map.put("method", "team.checkMember"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("attendMemberId", "2, 4");
    	map.put("longitude", "116.322987");
    	map.put("latitude", "39.983424");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testAddMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.addMember"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("phoneNumber", "13612332632");
    	map.put("memberName", "zzp3");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testDeleteMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.deleteMember"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("memberId", "1");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testUpdateMember(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.updateMember"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("memberId", "1");
    	map.put("memberName", "zzp");
    	map.put("phoneNumber", "12389432532");
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
    	map.put("method", "team.getMemberDetail"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("memberId", "2");
    	map.put("teamId", "2");
    	map.put("phoneNumber", "13636426042");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetTeamCheck(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getTeamCheck"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*	public void testGetTeamCheckHistory(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getTeamCheckHistory"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamId", "2");
    	map.put("pageNumber", 1+"");
    	map.put("pageSize", 10+"");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	/*public void testGetTeamCheckDetail(){
		Map <String, String>map = new HashMap<String, String>();
    	map.put("appKey", APP_KEY); //第二个参数为AppKey
    	map.put("method", "team.getTeamCheckDetail"); 
    	map.put("v", "1.0");
    	map.put("format", "json");
    	map.put("sessionId", SESSION_ID);
    	map.put("coachId", "20");
    	map.put("teamCheckId", "4");
    	String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
    	map.put("sign", sign);
    	String response = HttpUtil.postServer(SERVER_URL, map);
        System.out.println("response = " + response);
        assertNotNull(response);
        assertTrue(response.indexOf("code") <= 0);
	}*/
	
	public void testSyncDataService() {
		TeamSyncDataList t = new TeamSyncDataList();
		TeamSyncData t1 = new TeamSyncData();
		t1.setAppTeamId(4);
		t1.setOperationTime("2014-12-25 17:00:22");
		t1.setOperationType(1);
		t1.setTeamId(0L);
		t1.setTeamName("广告");
		List<TeamSyncData> list1 = new ArrayList<TeamSyncData>();
		list1.add(t1);
		t.setTeamDataList(list1);
		
		MemberSyncDataList m = new MemberSyncDataList();
		MemberSyncData m1 = new MemberSyncData();
		m1.setAppTeamId(1);
		m1.setAppMemberId(3);
		m1.setOperationTime("2014-08-08 11:30:40");
		m1.setOperationType(1);
//		m1.setMemberId(60L);
		m1.setMemberName("zzp");
		m1.setPhoneNumber("1367777777");
//		m1.setTeamId(5L);
		List<MemberSyncData> list2 = new ArrayList<MemberSyncData>();
		list2.add(m1);
		m.setMemberDataList(list2);
		
		CheckSyncDataList c = new CheckSyncDataList();
		CheckSyncData s1 = new CheckSyncData();
		s1.setAppCheckId(3);
		s1.setTeamCheckId(11L);
		s1.setTeamId(14L);
		s1.setOperationTime("2014-08-08 11:30:40");
//		s1.setOperationType(2);
		s1.setAttendMemberId("3, 4, 5");
		s1.setAttendAppMemberId("3, 4, 5");
		s1.setLongitude(36.323D);
		s1.setLatitude(124.323D);
		List<CheckSyncData> list3 = new ArrayList<CheckSyncData>();
		list3.add(s1);
		c.setCheckDataList(list3);
		
		Map <String, String>map = new HashMap<String, String>();
		map.put("appKey", APP_KEY); //第二个参数为AppKey
		map.put("method", "team.getList"); 
		map.put("v", "2.0");
		map.put("format", "json");
		map.put("teams", JsonBinder.buildNormalBinder().toJson(t));
//    	map.put("members", JsonBinder.buildNormalBinder().toJson(m));
//    	map.put("checks", JsonBinder.buildNormalBinder().toJson(c));
    	map.put("sessionId", "FC325135-37CD-4AE7-B369-23DDEA909B48");
		map.put("coachId", "34");
		map.put("status", "0");
		String sign = RopUtils.sign(map, APP_SECRET); //第二个参数为SecretKey, 有O2O系统分配
		map.put("sign", sign);
		String response = HttpUtil.postServer(SERVER_URL, map);
	    System.out.println("response = " + response);
	    assertNotNull(response);
	    assertTrue(response.indexOf("code") <= 0);
	}
}
