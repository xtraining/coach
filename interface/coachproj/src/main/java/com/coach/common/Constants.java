package com.coach.common;




public class Constants {
	public static final String SERVER_URL = Config.getProperty("coach_service_url");
	public static final String SUCCESS = "success";
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final String SMS_SIGN_UP_TEMPLATE = "sms_sign_up_template";
	public static final String SMS_RESET_PASSWORD_TEMPLATE = "sms_reset_password_template";
	public static final String MAX_SMS_SIGN_UP_NUM = "max_sms_sign_up_num";
	public static final String MAX_SMS_RESET_PASSWORD_NUM = "max_sms_reset_password_num";
	public static final String COACH_AVATAR_PREFIX = "coach_avatar";
	public static final String READ_FROM_CACHE_SWITCH = "read_from_cache_switch";
	public static final int COURSE_OFFSET = 5;
	
	
	public enum METHOD {
		GET_SUBAREA("common.getSubarea"), 
		CLEAR_CACHE("common.clearCache"), 
		
		BIND_BAIDU_PUSH_MESSAGE("coach.bindBaiduPushMessage"),
		BIND_THIRD_PARTY("coach.bindThirdParty"),
		UNBIND_THIRD_PARTY("coach.unbindThirdParty"),
		SIGN_IN("coach.signIn"),
		SIGN_OUT("coach.signOut"),
		SIGN_UP("coach.signUp"), 
		GET_SIGN_UP_VCODE("coach.getSignUpVcode"), 
		UPDATE_LAST_ACCESS_TIME("coach.updateLastAccessTime"), 
		THIRD_PARTY_SIGN_IN("coach.thirdPartySignIn"), 
		THIRD_PARTY_SIGN_UP("coach.thirdPartySignUp"),
		GET_RESET_PWD_VCODE("coach.getResetPwdVcode"), 
		RESET_PASSWORD("coach.resetPassword"),
		GET_PROFILE("coach.getProfile"),
		GET_PROFILE_DETAIL("coach.getProfileDetail"), 
		UPDATE_PROFILE_DETAIL("coach.updateProfileDetail"),
		GET_TOTAL_LESSON("coach.getTotalLesson"), 
		UPLOAD_AVATAR("coach.uploadAvatar"),
		GET_BIND_ORG("coach.getBindOrg"),
		UPDATE_BIND_ORG_STATUS("coach.updateBindOrgStatus"),
		
		GET_CHIEF_COURSE("course.getChiefCourse"),
		GET_ORG_COURSE("course.getOrgCourse"), 
		GET_NEW_COURSE("course.getNewCourse"), 
		ADD_COURSE("course.addCourse"),
		UPDATE_COURSE("course.updateCourse"),
		GET_COURSE_DETAIL("course.getCourseDetail"), 
		GET_PERSONAL_COURSE("course.getPersonalCourseList"),
		GET_COURSE_MEMBER("course.getCourseMember"), 
		DELETE_COURSE("course.deleteCourse"),
		UPDATE_COACH_COURSE_STATUS("course.updateCoachCourseStatus"),
		GET_REJECT_COURSE_DETAIL("course.getRejectCourseDetail"), 
		GET_UNASSIGNED_COURSE("course.getUnassignedCourse")		,
		
		GET_ONE_WEEK_LESSON("lesson.getOneWeekLesson"), 
		GET_LESSON_DETAIL("lesson.getLessonDetail"), 
		GET_RECENT_LESSON("lesson.getRecentLesson"), 
		GET_LESSON_MEMBER("lesson.getLessonMember"), 
		CHECK_LESSON("lesson.checkMember"), 
		ADD_PERSONAL("lesson.addPersonal"),
		ADD_LESSON("lesson.addLesson"), 
		GET_CHECK_LESSON("lesson.getCheckLesson"), 
		GET_LESSON_MEMBER_LIST("lesson.getLessonMemberList"), 
		DELETE_LESSON("lesson.deleteLesson"), 
		UPDATE_LESSON("lesson.updateLesson"), 
		UPDATE_LIFE("lesson.updateLife"),
		
		STUDENT_SEARCH_MEMBER("student.searchMember"),
		STUDENT_GET_COURSE_LIST("student.getCourseList"),
		STUDENT_GET_MEMBER("student.getMember"), 
		STUDENT_GET_MEMBER_DETAIL("student.getMemberDetail"),
		STUDENT_GET_MEMBER_NEWS("student.getMemberNews"), 
		UPDATE_MEMBER_DETAIL("student.updateMemberDetail"), 
		UPDATE_MEMBER_STATUS("student.updateMemberStatus");
		
		private String value;
		private METHOD(String value) {
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	public enum ONE_DAY_CACHE_KEY {
		COACH_PROFILE("coach_profile_"),
		COACH_COURSE("coach_course_"),
		COACH_PROFILE_DETAIL("coach_profile_detail_"), 
		COACH_ONE_WEEK_LESSON("coach_oneweek_lesson_"), 
		COACH_RECENT_LESSON("coach_recent_lesson_"),
		COACH_LESSON("coach_lesson_"),
		COACH_STUDENT("coach_student_"), 
		COACH_COURSE_MEMBER("coach_course_student_"),
		AREA("area_");
		private String value;
		private ONE_DAY_CACHE_KEY(String value) {
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	public enum ONE_HOUR_CACHE_KEY {
		CHIEF_COURSE("chief_course_"),
		COACH_LESSON_MEMBER("coach_lesson_member_"), 
		COACH_LESSON_MEMBER_LIST("coach_lesson_member_list_"),
		COACH_CHECK_LESSON("coach_check_lesson_");
		private String value;
		private ONE_HOUR_CACHE_KEY(String value) {
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	public enum CACHE_REGION{
		DEFAULT("defaultCache"),
		SESSION("session"),
		ONE_HOUR("onehour"),
		ONE_DAY("oneday");
		private String value;
		private CACHE_REGION(String value) {
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
	
	public enum OS_TYPE {
		IOS(0), ANDROID(1);
		private int value;
		private OS_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum ACCESS_LOG_TYPE {
		REQUEST(1), RESPONSE(2);
		private int value;
		private ACCESS_LOG_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum CLIENT_APPKEY_STATUS {
		ACTIVE(0), DELETED(1);
		private int value;
		private CLIENT_APPKEY_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum SYS_SESSION_STATUS {
		ACTIVE(0), INACTIVE(1);
		private int value;
		private SYS_SESSION_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum COACH_STATUS {
		ACTIVE(0), DELETED(1), LOCKED(2);
		private int value;
		private COACH_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum COACH_TYPE {
		PUBLIC(0), ORGANIZATION(1);
		private int value;
		private COACH_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum GENDER {
		MALE(1), FEMALE(0), UNKNOW(2);
		private int value;
		private GENDER(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum SMS_TYPE {
		SIGN_UP(0), RESET_PWD(1);
		private int value;
		private SMS_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum SMS_STATUS {
		SENT_SUCCESS(0), SENT_FAILED(1);
		private int value;
		private SMS_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum REGISTER_TYPE {
		PHONE(0), QQ(1), WEIBO(2), WEIXIN(3);
		private int value;
		private REGISTER_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum IMAGE_TYPE {
		COACH_AVATAR(0);
		private int value;
		private IMAGE_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum UPLOAD_STATUS {
		DRAFT(0), SUCCESS(1);
		private int value;
		private UPLOAD_STATUS(int value) {
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
	
	public enum RECEIVER_TYPE {
		COACH(0), MEMBER(1);
		private int value;
		private RECEIVER_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum ID_TYPE {
		ID_CARD(0), PASSPORT(1);
		private int value;
		private ID_TYPE(int value) {
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
	
	public enum ORG_COACH_DISPLAY_STATUS {
		APPLY(0), APPLIED(1), ACCEPT(2), BINDED(3);
		private int value;
		private ORG_COACH_DISPLAY_STATUS(int value) {
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
	
	public enum LESSON_STATUS {
		ACTIVE(0), 
		DELETED(1);
		private int value;
		private LESSON_STATUS(int value) {
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
	
	public enum COACH_COURSE_STATUS {
		DRAFT(0), ACCEPTED(1), REJECT(2);
		private int value;
		private COACH_COURSE_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	
	public enum COURSE_MEMBER_STATUS {
		ACTIVE(0), DELETED(1), TRY(2), QUIT(3), DONE(4), MIDDLE(5), OVER_DUE(6);
		private int value;
		private COURSE_MEMBER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
	
	public enum LESSON_MEMBER_STATUS {
		MISS(0), CHECK(1), DELETED(2);
		private int value;
		private LESSON_MEMBER_STATUS(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
	
	public enum COURSE_FLAG {
		MODIFIED(0), LOCKED(1);
		private int value;
		private COURSE_FLAG(int value) {
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
	
	public enum CAL_LESSON_TYPE {
		CHECKED(0), ORG(1), PERSONAL(2), LIFE(3);
		private int value;
		private CAL_LESSON_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
	public enum LESSON_CHECK_FLAG {
		CHECKED(1), NONE(0);
		private int value;
		private LESSON_CHECK_FLAG(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
	
	public enum NEWS_TYPE {
		MEMBER_NEWS(0), COURSE_NEWS(1);
		private int value;
		private NEWS_TYPE(int value) {
			this.value = value;
		}
		public int getValue(){
		return this.value;
		}
	}
}
