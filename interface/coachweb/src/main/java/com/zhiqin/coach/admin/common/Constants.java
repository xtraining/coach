package com.zhiqin.coach.admin.common;

import com.zhiqin.coach.admin.util.Config;


public class Constants {
	public static final String APP_KEY = "web_admin";
	public static final String SECRET_KEY = "OhP7seHIaAK4BsgOxrkjmULheq3pB2aY";
	public static final String METHOD = "method";
	public static final String SYS_USER_INF_IN_SESSION = "sys_user_in_session";
	public static final String MENU_INF_IN_SESSION = "menuInforInSession";
	public static final String SUCCESS = "success";
	public static final int ADMIN_DEFAULT_PAGE_SIZE = 20;
	public static final String ALL_MENU_INF_STR = "ALL_MENU_INF_STR";
	public static final String ALL_MENU_INF_STR_FOR_EDIT = "ALL_MENU_INF_STR_FOR_EDIT";
	public static final String IS_SUPER_ADMIN = "is_super_admin";
	public static final String QUESTION_IMAGE_PREFIX = "question";
	public static final String ANSWER_IMAGE_PREFIX = "answer";
	public static final String MEMBER_IMAGE_PREFIX = "member";
	public static final String TAG_IMAGE_PREFIX = "image_tag";
	public static final String USER_SESSION = "userSession";
	public static final String VALIFICATION_CODE_SESSION_NAME = "valication_code";
	
	public enum SYS_USER_STATUS {
		ACTIVE(0), INACTIVE(1), DELETED(2);
		private int value;
		private SYS_USER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	} 
	
	public enum QUESTION_STATUS {
		ACTIVE(0), DELETED(1);
		private int value;
		private QUESTION_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum ANSWER_STATUS {
		ACTIVE(0), DELETED(1);
		private int value;
		private ANSWER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MEMBER_TYPE {
		PUBLIC(0), FAKE(1);
		private int value;
		private MEMBER_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MEMBER_STATUS {
		ACTIVE(0), DELETED(1), LOCKED(2);
		private int value;
		private MEMBER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum THIRD_PARTY_TYPE {
		WEIBO(0), WEIXIN(1), QQ(2);
		private int value;
		private THIRD_PARTY_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum REGISTER_STATUS {
		NONREGISTERED(0), REGISTERED(1);
		private int value;
		private REGISTER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum DEFAULT_FAKE_MEMBER {
		NO(0), YES(1);
		private int value;
		private DEFAULT_FAKE_MEMBER(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MEMBER_GENDER {
		FEMALE(0), MALE(1), UNKNOWN(2);
		private int value;
		private MEMBER_GENDER(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MEMBER_PUSH_STATUS {
		INACTIVE(0), ACTIVE(1);
		private int value;
		private MEMBER_PUSH_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum NEWS_STATUS {
		ACTIVE(0), DELETED(1);
		private int value;
		private NEWS_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum NEWS_ACTION_TYPE {
		QUESTION(0), ANSWER(1);
		private int value;
		private NEWS_ACTION_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum WARNING_STATUS {
		DRAFT(0), NO_PROBLEM(1), PROBLEM(2);
		private int value;
		private WARNING_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum OS_TYPE {
		ANDROID(1), IOS(0);
		private int value;
		private OS_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MESSAGE_STATUS {
		DRAFT(0), SENT_SUCCESS(1), SENT_FAILED(2);
		private int value;
		private MESSAGE_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum PUSH_STATUS {
		ON(1), OFF(0);
		private int value;
		private PUSH_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MESSAGE_TYPE {
		QUESTION_ANSWERED(0);
		private int value;
		private MESSAGE_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum MEMBER_NEWS_TYPE {
		ANSWER_QUESTION(0), FOLLOW_QUESTION(1), SUPPORT_ANSWER(2);
		private int value;
		private MEMBER_NEWS_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum COURSE_STATUS {
		ACTIVE(0), 
		DELETED(1);
		private int value;
		private COURSE_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum COURSE_TYPE {
		ORG(0), 
		PERSONAL(1);
		private int value;
		private COURSE_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum LESSON_TYPE {
		JOB(0), 
		LIFE(1);
		private int value;
		private LESSON_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum ALERT_SWITCH {
		OFF(0), ON(1);
		private int value;
		private ALERT_SWITCH(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
	
	public enum ORG_COACH_STATUS {
		NONE(0), 
		COACH_NONE(1), COACH_ACCEPTED(2), COACH_REJECTED(3), 
		ORG_NONE(4), ORG_ACCEPTED(5), ORG_REJECTED(6), 
		COACH_UNBIND(7), ORG_UBIND(8), DELETED(-1);
		private int value;
		private ORG_COACH_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum DOWNLOAD_SOURCE_FROM {
		XIMALAYA(0), QINGTINGFM(1);
		private int value;
		private DOWNLOAD_SOURCE_FROM(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum TASK_STATUS {
		ACTIVE(0), DELETED(1);
		private int value;
		private TASK_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum ARTIFACT_STATUS {
		ACTIVE(0), DELETED(1), HIDDEN(2), DRAFT(3);
		private int value;
		private ARTIFACT_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum DOWNLOAD_TASK_STATUS {
		DRAFT(0), INPROGRESS(1), UPLOAD(2), SUCCESS(3), FAILURE(4);
		private int value;
		private DOWNLOAD_TASK_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum DOWNLOAD_TASK_TYPE {
		COVER(0), ARTIFACT(1);
		private int value;
		private DOWNLOAD_TASK_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum FILE_NAME_PREFIX {
		STORY_IMAGE("story_image_"), VOICE("story_voice_"), TOP_LIST_IMAGE("top_list_image_"),
		TOP_DETAIL_IMAGE("top_detail_image_"), CATEGORY("category_image_"), TAG("tag_image_");
		private String value;
		private FILE_NAME_PREFIX(String value) {
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
}
