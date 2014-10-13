package com.coach.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Config;
import com.coach.common.Constants;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.REGISTER_TYPE;
import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.Coach;
import com.coach.model.SmsHistory;
import com.coach.model.SysSession;
import com.coach.request.BindBaiduPushMessageRequest;
import com.coach.request.BindThirdPartyRequest;
import com.coach.request.CoachBaseRequest;
import com.coach.request.GetVcodeRequest;
import com.coach.request.ResetPasswordRequest;
import com.coach.request.SignInRequest;
import com.coach.request.SignOutRequest;
import com.coach.request.SignUpRequest;
import com.coach.request.ThirdSignInRequest;
import com.coach.request.ThirdSignUpRequest;
import com.coach.request.UnbindThirdPartyRequest;
import com.coach.request.UpdateBindOrgStatusRequest;
import com.coach.request.UpdateProfileDetailRequest;
import com.coach.request.UploadAvatarRequest;
import com.coach.resolver.CoachResolver;
import com.coach.resolver.ILessonResolver;
import com.coach.resolver.SmsResolver;
import com.coach.resolver.SysSessionResolver;
import com.coach.response.BindOrgResponse;
import com.coach.response.ProfileDetailResponse;
import com.coach.response.ProfileResponse;
import com.coach.response.SignInResponse;
import com.coach.response.SimpleResponse;
import com.coach.response.TotalLessonResponse;
import com.rop.annotation.HttpAction;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;
import com.rop.response.BusinessServiceErrorResponse;

@ServiceMethodBean
public class CoachService extends SimpleBaseService{
	@Autowired private CoachResolver coachResolver;
	@Autowired private SysSessionResolver sessionResolver;
	@Autowired private SmsResolver smsResolver;
	@Resource private ILessonResolver lessonResolver;
	
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
		Integer coachId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		SimpleResponse r = new SimpleResponse();
		if(coachId != null && coachId > 0){
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
		Integer coachId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		if(coachId != null && coachId > 0){
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
		Integer coachId = coachResolver.getIdByCredentials(request.getPhoneNumber(), request.getPassword());	
		if(coachId == null || coachId == 0){
			response.setFlag(1);
			response.setMsg("用户名或密码错误");
			return response;
		}
		Coach c = new Coach();
		c.setId(coachId);
		response = sessionResolver.setupSignInSuccessResponse(request, c);
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
	
	@ServiceMethod(method = "coach.thirdPartySignIn",version = "1.0", needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object thirdSignIn(ThirdSignInRequest request) {	
		if(request.getType() != REGISTER_TYPE.WEIBO.getValue() && request.getType() != REGISTER_TYPE.WEIXIN.getValue()
				&& request.getType() != REGISTER_TYPE.QQ.getValue() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "NOT_SUPPORTED_THIRD_PARTY_TYPE",
        			request.getRopRequestContext().getLocale(), request.getType());
		}
		Integer coachId = coachResolver.getByThirdPartyId(request.getThirdPartyId(), request.getType());	
		if(coachId == null || coachId == 0){//如果用户是第一次第三方登陆，需要补全用户资料
			SimpleResponse response = new SimpleResponse();
			response.setFlag(1);
			response.setMsg("Please fill up the user info。");
			return response;
		}
		Coach c = new Coach();
		c.setId(coachId);
		SignInResponse response = sessionResolver.setupSignInSuccessResponse(request, c);
		return response;
	}
	
	@ServiceMethod(method = "coach.thirdPartySignUp",version = "1.0", needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object thirdPartySignUp(ThirdSignUpRequest request) {	
		if(request.getType() != REGISTER_TYPE.WEIBO.getValue() && request.getType() != REGISTER_TYPE.WEIXIN.getValue()
				&& request.getType() != REGISTER_TYPE.QQ.getValue() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "NOT_SUPPORTED_THIRD_PARTY_TYPE",
        			request.getRopRequestContext().getLocale(), request.getType());
		}
		Integer coachId = coachResolver.getByThirdPartyId(request.getThirdPartyId(), request.getType());	
		if(coachId != null && coachId > 0){//只是作为防御性检查，实际是不可能进入这个分支
			SimpleResponse response = new SimpleResponse();
			response.setFlag(1);
			response.setMsg("The user info already registerd。");
			return response;
		}
		//check whether the phone number already exists or not.
		coachId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		if(coachId != null && coachId > 0){
			SignInResponse response = new SignInResponse();
			response.setFlag(1);
			response.setMsg("该手机号码已注册");
			return response;
		}
		SignInResponse response = validateVcode(request.getPhoneNumber(), request.getVcode(), SMS_TYPE.SIGN_UP);
		if(response.getFlag() != 0){
			return response;
		}
		Coach c = coachResolver.thirdSignUp(request);
		response = sessionResolver.setupSignInSuccessResponse(request, c);
		return response;
	}
	
	@ServiceMethod(method = "coach.getResetPwdVcode",version = "1.0",needInSession = NeedInSessionType.NO, httpAction = HttpAction.POST)
    public Object getResetPwdVcode(GetVcodeRequest request) {
		Integer coachId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		SimpleResponse r = new SimpleResponse();
		if(coachId == null || coachId == 0){
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
		Integer coachId = coachResolver.checkByPhoneNumber(request.getPhoneNumber());
		if(coachId == null || coachId == 0){
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
	
	@ServiceMethod(method = "coach.getProfile",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getProfile(CoachBaseRequest request) {	
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		ProfileResponse response = coachResolver.getProfile(request.getCoachId());
		return response;
	}
	
	@ServiceMethod(method = "coach.getProfileDetail",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getProfileDetail(CoachBaseRequest request) {	
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		ProfileDetailResponse response = coachResolver.getProfileDetail(request.getCoachId());
		return response;
	}
	
	@ServiceMethod(method = "coach.updateProfileDetail",version = "1.0", needInSession = NeedInSessionType.YES)
    public Object updateProfileDetail(UpdateProfileDetailRequest request) {	
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		coachResolver.updateProfileDetail(request);
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "coach.bindBaiduPushMessage",version = "1.0",needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object bindBaiduPushMessage(BindBaiduPushMessageRequest request) {
		coachResolver.bindBaiduPushMessage(request);
		SimpleResponse s = new SimpleResponse();
		return s;
	}
	
	@ServiceMethod(method = "coach.bindThirdParty",version = "1.0",needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object bindThridParty(BindThirdPartyRequest request) {
		if(request.getType() != REGISTER_TYPE.WEIBO.getValue() && request.getType() != REGISTER_TYPE.WEIXIN.getValue()
				&& request.getType() != REGISTER_TYPE.QQ.getValue() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "NOT_SUPPORTED_THIRD_PARTY_TYPE",
        			request.getRopRequestContext().getLocale(), request.getType());
		}
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		Coach c = coachResolver.getBasicById(request.getCoachId());
		if(c == null){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COACH_ID_NOT_EXIST",
        			request.getRopRequestContext().getLocale());
		}
		ProfileResponse response = coachResolver.bindThirdParty(request, c);
		return response;
	}

	@ServiceMethod(method = "coach.unbindThirdParty",version = "1.0",needInSession = NeedInSessionType.YES, httpAction = HttpAction.POST)
    public Object unbindThridParty(UnbindThirdPartyRequest request) {
		if(request.getType() != REGISTER_TYPE.WEIBO.getValue() && request.getType() != REGISTER_TYPE.WEIXIN.getValue()
				&& request.getType() != REGISTER_TYPE.QQ.getValue() ){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "NOT_SUPPORTED_THIRD_PARTY_TYPE",
        			request.getRopRequestContext().getLocale(), request.getType());
		}
		SysSession session = sessionResolver.getValidSessionBySessionId(request.getRopRequestContext().getSessionId());
		if(session == null || session.getReceiverType() == null || session.getReceiverId() == null || 
				session.getReceiverType().intValue() != RECEIVER_TYPE.COACH.getValue() || session.getReceiverId().intValue() != request.getCoachId()){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "ID_NOT_MATCH",
        			request.getRopRequestContext().getLocale());
		}
		Coach c = coachResolver.getBasicById(request.getCoachId());
		if(c == null){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COACH_ID_NOT_EXIST",
        			request.getRopRequestContext().getLocale());
		}
		ProfileResponse response = coachResolver.unbindThirdParty(request.getType(), c);
		return response;
	}
	
	@ServiceMethod(method = "coach.uploadAvatar",version = "1.0", needInSession = NeedInSessionType.YES, httpAction=HttpAction.POST)
    public Object uploadAvatar(UploadAvatarRequest request) {	
		Coach c = coachResolver.getBasicById(request.getCoachId());
		if(c == null){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COACH_ID_NOT_EXIST",
        			request.getRopRequestContext().getLocale());
		}
		return coachResolver.updateAvatar(request, c);
	}
	
	@ServiceMethod(method = "coach.getBindOrg",version = "1.0", needInSession = NeedInSessionType.YES, httpAction=HttpAction.POST)
    public Object getBindOrg(CoachBaseRequest request) {	
		Coach c = coachResolver.getBasicById(request.getCoachId());
		if(c == null){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COACH_ID_NOT_EXIST",
        			request.getRopRequestContext().getLocale());
		}
		List<BindOrgResponse> list = coachResolver.getBindOrg(c.getId());
		return list;
	}
	
	@ServiceMethod(method = "coach.updateBindOrgStatus",version = "1.0", needInSession = NeedInSessionType.YES, httpAction=HttpAction.POST)
    public Object updateBindOrgStatus(UpdateBindOrgStatusRequest request) {	
		Coach c = coachResolver.getBasicById(request.getCoachId());
		if(c == null){
			return new BusinessServiceErrorResponse(
        			request.getRopRequestContext().getMethod(), "COACH_ID_NOT_EXIST",
        			request.getRopRequestContext().getLocale());
		}
		coachResolver.updateBindOrgStatus(request.getCoachId(), request.getOrgId());
		List<BindOrgResponse> list = coachResolver.getBindOrg(c.getId());
		return list;
	}
	
	@ServiceMethod(method = "coach.getTotalLesson", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getTotalLesson(CoachBaseRequest request) {
		TotalLessonResponse response = lessonResolver.getTotalLesson(request.getCoachId());
		return response;
	}
}
