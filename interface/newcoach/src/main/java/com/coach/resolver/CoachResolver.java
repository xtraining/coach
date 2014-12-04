package com.coach.resolver;

import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.Coach;
import com.coach.model.SmsHistory;
import com.coach.request.BindBaiduPushMessageRequest;
import com.coach.request.SignOutRequest;
import com.coach.request.SignUpRequest;
public interface CoachResolver {
	public Coach getIdByCredentials(String phoneNumber, String password);

	public SmsHistory createVcode(String phoneNumber, SMS_TYPE type);

	public int checkVcode(String phoneNumber, String vcode, SMS_TYPE type);

	public void resetPassword(String phoneNumber, String password) ;

	
	public Coach signUp(SignUpRequest request);


	public void unbindBaiduPushMessage(SignOutRequest request);

	public Long checkByPhoneNumber(String phoneNumber);

	public boolean checkExceedSmsNum(SMS_TYPE type, String phoneNumber);
	
	public void bindBaiduPushMessage(BindBaiduPushMessageRequest request);

	public Coach getBasicById(Long coachId) ;

	public int updateLastAccessTime(Long coachId);


	public Long getCoachIdByPhoneNumber(String phoneNumber);

	public boolean checkToken(Long coachId, String token);

}
