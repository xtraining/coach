package com.coach.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Config;
import com.coach.common.Constants;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.Coach;
import com.coach.model.SmsHistory;
import com.coach.model.SysSession;
import com.coach.request.BindBaiduPushMessageRequest;
import com.coach.request.ChangeSMSStatusRequest;
import com.coach.request.CheckVersionRequest;
import com.coach.request.CoachBaseRequest;
import com.coach.request.GetVcodeRequest;
import com.coach.request.ResetPasswordRequest;
import com.coach.request.ScanSignInRequest;
import com.coach.request.SignInRequest;
import com.coach.request.SignOutRequest;
import com.coach.request.SignUpRequest;
import com.coach.resolver.CoachResolver;
import com.coach.resolver.SmsResolver;
import com.coach.resolver.SysSessionResolver;
import com.coach.response.QrcodeSignInResponse;
import com.coach.response.SignInResponse;
import com.coach.response.SimpleResponse;
import com.rop.annotation.HttpAction;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean
public class CoachService extends SimpleBaseService{
	@Resource private CoachResolver coachResolver;
	@Autowired private SysSessionResolver sessionResolver;
	@Autowired private SmsResolver smsResolver;
	
	@ServiceMethod(method = "coach.checkVersion",version = "1.0", needInSession = NeedInSessionType.NO)
    public Object checkVersion(CheckVersionRequest request) {
		SimpleResponse response =  new SimpleResponse();
		if(!StringUtils.equalsIgnoreCase(request.getVersionNum(), Config.getProperty("version_num"))){
			response.setFlag(1);
			response.setMsg("http://www.pgyer.com/icoach");
		}
		return response;
	}
	
	@ServiceMethod(method = "coach.updateLastAccessTime",version = "1.0",needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object updateLastAccessTime(CoachBaseRequest request) {
		int result = coachResolver.updateLastAccessTime(request.getCoachId());
		SimpleResponse response =  new SimpleResponse();
		if(result > 0){
			return response;
		} else {
			response.setFlag(1);
			response.setMsg("coach id not existing.");
			return response;
		}
	}
	
	@ServiceMethod(method = "coach.getSignUpVcode", version = "1.0", needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object getSignUpVcode(GetVcodeRequest request) {
		//check whether the phone number already exists or not.
		Long tuserId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		SimpleResponse r = new SimpleResponse();
		if(tuserId != null && tuserId > 0){
			r.setFlag(1);
			r.setMsg("该手机号码已注册");
			return r;
		} 
		
		boolean exceedSmsNum = coachResolver.checkExceedSmsNum(SMS_TYPE.SIGN_UP, request.getPhoneNumber());
		if(exceedSmsNum){
			r.setFlag(2);
			r.setMsg("The phone number already exceeds max sign up sms num one day.");
			return r;
		}
		
		SmsHistory msg = coachResolver.createVcode(request.getPhoneNumber(), SMS_TYPE.SIGN_UP);
		smsResolver.sendVcode(msg);
		r.setFlag(0);
		r.setMsg(Constants.SUCCESS);
		return r;
	}
	
	@ServiceMethod(method = "coach.signUp",version = "1.0",needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object signUp(SignUpRequest request) {
		//check whether the phone number already exists or not.
		Long tuserId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		if(tuserId != null && tuserId > 0){
			SignInResponse response = new SignInResponse();
			response.setFlag(1);
			response.setMsg("该手机号码已注册");
			return response;
		}
		SignInResponse response = validateVcode(request.getPhoneNumber(), request.getVcode(), SMS_TYPE.SIGN_UP);
		if(response.getFlag() != 0){
			return response;
		}
		Coach c = coachResolver.signUp(request);		
		response = sessionResolver.setupSignInSuccessResponse(request, c);
		return response;
	}
	
	@ServiceMethod(method = "coach.signIn",version = "1.0", needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object signIn(SignInRequest request) {	
		SignInResponse response = new SignInResponse();
		Coach coach = coachResolver.getIdByCredentials(request.getPhoneNumber(), request.getPassword());	
		if(coach == null || coach.getId() == null){
			response.setFlag(1);
			response.setMsg("用户名或密码错误");
			return response;
		}
		response = sessionResolver.setupSignInSuccessResponse(request, coach);
		return response;
	}
	
	private SignInResponse validateVcode(String phoneNumber, String vcode, SMS_TYPE type){
		SignInResponse response = new SignInResponse();
		if(StringUtils.equals("1", Config.getProperty("sms_switch"))){
			//check vcode
			int vcodeVerify = coachResolver.checkVcode(phoneNumber, vcode, type);
			if(vcodeVerify == 1){
				response.setFlag(2);
				response.setMsg("验证码已过期");
				return response;
			} else if(vcodeVerify == 2){
				response.setFlag(3);
				response.setMsg("验证码不正确");
				return response;
			}
		}
		return response;
	}
	@ServiceMethod(method = "coach.getResetPwdVcode",version = "1.0",needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object getResetPwdVcode(GetVcodeRequest request) {
		Long tuserId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		SimpleResponse r = new SimpleResponse();
		if(tuserId == null || tuserId == 0){
			r.setFlag(1);
			r.setMsg("该手机号码还未注册");
			return r;
		} 
		boolean exceedSmsNum = coachResolver.checkExceedSmsNum(SMS_TYPE.RESET_PWD, request.getPhoneNumber());
		if(exceedSmsNum){
			r.setFlag(2);
			r.setMsg("The phone number already exceeds max reset password sms num one day.");
			return r;
		}
		
		SmsHistory msg = coachResolver.createVcode(request.getPhoneNumber(), SMS_TYPE.RESET_PWD);
		smsResolver.sendVcode(msg);
		r.setFlag(0);
		r.setMsg(Constants.SUCCESS);
        return r;
	}
	
	@ServiceMethod(method = "coach.resetPassword",version = "1.0",needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object resetPassword(ResetPasswordRequest request) {
		if(!StringUtils.equals(request.getPassword(), request.getConfirmPassword())){
			SignInResponse response = new SignInResponse();
			response.setFlag(4);
			response.setMsg("输入的密码不一致");
			return response;
		}
		//check whether the phone number already exists or not.
		Long tuserId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		if(tuserId == null || tuserId == 0){
			SignInResponse response = new SignInResponse();
			response.setFlag(1);
			response.setMsg("该手机号码还未注册");
			return response;
		}
		SignInResponse response = validateVcode(request.getPhoneNumber(), request.getVcode(), SMS_TYPE.RESET_PWD);
		if(response.getFlag() != 0){
			return response;
		}
		coachResolver.resetPassword(request.getPhoneNumber(), request.getPassword());	
		Coach c = new Coach();
		Long coachId = coachResolver.getCoachIdByPhoneNumber(request.getPhoneNumber());
		c.setId(coachId);
		response = sessionResolver.setupSignInSuccessResponse(request, c);
		return response;
	}
	
	@ServiceMethod(method = "coach.signOut",version = "1.0", needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object signOut(SignOutRequest request) {	
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session != null && session.getReceiverType() != null && 
				session.getReceiverType() == RECEIVER_TYPE.COACH.getValue() && 
				session.getReceiverId() != null && 
				session.getReceiverId().intValue() == request.getCoachId()){
			request.getRopRequestContext().removeSession();
		}
		coachResolver.unbindBaiduPushMessage(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "coach.bindBaiduPushMessage",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object bindBaiduPushMessage(BindBaiduPushMessageRequest request) {
		coachResolver.bindBaiduPushMessage(request);
		SimpleResponse s = new SimpleResponse();
		return s;
	}
	
	@ServiceMethod(method = "coach.scanSignIn",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object scanSignIn(ScanSignInRequest request) throws Exception {
		SimpleResponse s = new SimpleResponse();
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			s.setFlag(1);
			s.setMsg("Session ID doesn't match with Coach ID.");
			return s;
		}
    	String response = coachResolver.sendToWeb(request);
		if("0".equals(response)){
			return s;
		} else {
			s.setFlag(1);
			s.setMsg("Token failed.");
			return s;
		}
	}
	
	@ServiceMethod(method = "coach.qrcodeSignIn",version = "1.0", needInSession = NeedInSessionType.NO)
    public Object qrcodeSignIn(CoachBaseRequest request) throws Exception {
		QrcodeSignInResponse s = new QrcodeSignInResponse();
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.WEB_COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return s;
		}
		Coach c = coachResolver.getBasicById(request.getCoachId());
		s.setCoachId(request.getCoachId());
		s.setPhoneNumber(c.getPhoneNumber());
		return s;
	}
	
	@ServiceMethod(method = "coach.changeSMSStatus",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object changeSMSStatus(ChangeSMSStatusRequest request) {
		coachResolver.changeSMSStatus(request);
		SimpleResponse s = new SimpleResponse();
		return s;
	}
}
