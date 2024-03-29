package com.coach.resolver;

import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.Coach;
import com.coach.model.SmsHistory;
import com.coach.request.v1.BindBaiduPushMessageRequest;
import com.coach.request.v1.ChangeSMSStatusRequest;
import com.coach.request.v1.ScanSignInRequest;
import com.coach.request.v1.SignOutRequest;
import com.coach.request.v1.SignUpRequest;
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

	public void changeSMSStatus(ChangeSMSStatusRequest request);

	public String sendToWeb(ScanSignInRequest request);

}
