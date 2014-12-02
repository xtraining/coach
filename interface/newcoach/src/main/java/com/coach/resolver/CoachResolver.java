package com.coach.resolver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.common.Config;
import com.coach.common.Constants;
import com.coach.common.Constants.COACH_STATUS;
import com.coach.common.Constants.COACH_TYPE;
import com.coach.common.Constants.IMAGE_TYPE;
import com.coach.common.Constants.ORG_COACH_DISPLAY_STATUS;
import com.coach.common.Constants.ORG_COACH_STATUS;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.REGISTER_TYPE;
import com.coach.common.Constants.SMS_TYPE;
import com.coach.dao.AppVersionDao;
import com.coach.dao.ClientAppkeyDao;
import com.coach.dao.CoachDao;
import com.coach.dao.CoachExpandDao;
import com.coach.dao.SmsHistoryDao;
import com.coach.dao.TuserDao;
import com.coach.model.Coach;
import com.coach.model.CoachExpand;
import com.coach.model.SmsHistory;
import com.coach.model.Tuser;
import com.coach.request.BindBaiduPushMessageRequest;
import com.coach.request.BindThirdPartyRequest;
import com.coach.request.SignOutRequest;
import com.coach.request.SignUpRequest;
import com.coach.request.ThirdSignUpRequest;
import com.coach.request.UpdateProfileDetailRequest;
import com.coach.request.UploadAvatarRequest;
import com.coach.response.BindOrgResponse;
import com.coach.response.ProfileDetailResponse;
import com.coach.response.ProfileResponse;
import com.coach.response.UploadImageResponse;
import com.coach.utils.QiniuUtils;
@Service
public class CoachResolver extends BaseResolver implements ICoachResolver{
	@Resource private CoachDao coachDao;
	@Resource private TuserDao tuserDao;
	@Resource private SmsHistoryDao smsHistoryDao;
	@Resource private CoachExpandDao coachExpandDao;
	@Resource private ClientAppkeyDao clientAppkeyDao;
	@Resource private AppVersionDao appVersionDao;
	public Coach getIdByCredentials(String phoneNumber, String password) {
		return coachDao.getIdByCredentials(phoneNumber, password);
	}

	public SmsHistory createVcode(String phoneNumber, SMS_TYPE type) {
		Random random = new Random(); 
		String vcode = "";
		for(int i = 0; i < 6; i++){
			vcode += random.nextInt(10);
		}
		if(!StringUtils.equals("1", Config.getProperty("sms_switch"))){
			vcode = "123456";
		}
		String content = "";
		if(type == SMS_TYPE.SIGN_UP){
			content = Config.getProperty(Constants.SMS_SIGN_UP_TEMPLATE);
		} else if(type == SMS_TYPE.RESET_PWD){
			content = Config.getProperty(Constants.SMS_RESET_PASSWORD_TEMPLATE);
		}
		content = StringUtils.replace(content, "{vcode}", vcode);
		SmsHistory h = new SmsHistory();
		h.setPhoneNumber(phoneNumber);
		h.setContent(content);
		h.setCreateTime(new Timestamp(new Date().getTime()));
		h.setType(type.getValue());
		h.setVcode(vcode);
		h.setReceiverType(RECEIVER_TYPE.COACH.getValue());
		smsHistoryDao.save(h);
		return h;
	}

	public int checkVcode(String phoneNumber, String vcode, SMS_TYPE type) {
		SmsHistory h = smsHistoryDao.getLastestHistory(type, phoneNumber);
		if(h != null){
			long timeDifference = System.currentTimeMillis() - h.getCreateTime().getTime();
			if(timeDifference > Integer.valueOf(Config.getProperty("sms_valid_time")) * 60 * 1000){
				return 1; //超时
			}
			if(StringUtils.equals(h.getVcode(), vcode)){
				return 0; //验证码正确
			} else {
				return 2; //验证码不正确
			}
		}
		return 2;//验证码不正确
	}

	public void resetPassword(String phoneNumber, String password) {
		coachDao.resetPassword(phoneNumber, password);
		
	}

	@Transactional
	public Coach signUp(SignUpRequest request) {
		Tuser u = new Tuser();
		u.setPhoneNumber(request.getPhoneNumber());
		u.setPassword(request.getPassword());
		u.setStatus(COACH_STATUS.ACTIVE.getValue());
		tuserDao.save(u);
		
		Coach c = new Coach();
		c.setType(COACH_TYPE.PUBLIC.getValue());
		c.setRegisterType(REGISTER_TYPE.PHONE.getValue());
		c.setGraphicLock(0);
		c.setTuserId(u.getId());
		coachDao.save(c);
		return c;
	}


	public void unbindBaiduPushMessage(SignOutRequest request) {
		coachExpandDao.deleteBaiduBinding(request.getCoachId(), request.getBaiduUserId(), request.getBaiduChannelId());
		
	}

	public ProfileResponse getProfile(Long coachId) {
			Coach c = coachDao.getBasicById(coachId);
			ProfileResponse response = c.toProfileResponse();
			return response;
	}

	public Long checkByPhoneNumber(String phoneNumber) {
		Long tuserId = tuserDao.getByPhoneNumber(phoneNumber);
		return tuserId;
	}

	public boolean checkExceedSmsNum(SMS_TYPE type, String phoneNumber) {
		Integer count = smsHistoryDao.getTodaySmsNumber(type, phoneNumber);
		int maxNum = 0;
		if(type == SMS_TYPE.SIGN_UP){
			maxNum = Integer.valueOf(Config.getProperty(Constants.MAX_SMS_SIGN_UP_NUM));
		} else if(type == SMS_TYPE.RESET_PWD){
			maxNum = Integer.valueOf(Config.getProperty(Constants.MAX_SMS_RESET_PASSWORD_NUM));
		}
		if(count == null || count <= maxNum){
			return false;
		} else {
			return true; //超过了发送短信的条数
		}
	}
	

	public Long getByThirdPartyId(String thirdPartyId, Integer type) {
		return coachDao.getByThirdPartyId(thirdPartyId, type);
	}

	@Transactional
	public Coach thirdSignUp(ThirdSignUpRequest request) {
		Tuser u = new Tuser();
		u.setPhoneNumber(request.getPhoneNumber());
		u.setPassword(request.getPassword());
		u.setStatus(COACH_STATUS.ACTIVE.getValue());
		tuserDao.save(u);
		
		Coach c = new Coach();
		c.setType(COACH_TYPE.PUBLIC.getValue());
		if(request.getType() == REGISTER_TYPE.QQ.getValue()){
			c.setQqId(request.getThirdPartyId());
		} else if(request.getType() == REGISTER_TYPE.WEIBO.getValue()){
			c.setWeiboId(request.getThirdPartyId());
		} else if(request.getType() == REGISTER_TYPE.WEIXIN.getValue()){
			c.setWeixinId(request.getThirdPartyId());
		}
		c.setName(request.getThirdPartyNickname());
		c.setRegisterType(request.getType());
		c.setGraphicLock(0);
		c.setTuserId(u.getId());
		coachDao.save(c);
		return c;
	}

	public void bindBaiduPushMessage(BindBaiduPushMessageRequest request) {
		coachExpandDao.deleteBaiduBinding(request.getCoachId(), request.getBaiduUserId(), request.getBaiduChannelId());
		int osType = clientAppkeyDao.getOsTypeByAppkey(request.getRopRequestContext().getAppKey());
		CoachExpand ex = new CoachExpand();
		ex.setAppVersionId(appVersionDao.getIdByVersion(request.getAppVersion(), osType));
		ex.setCoachId(request.getCoachId());
		ex.setOsType(osType);
		ex.setStatus(0);
		ex.setOsVersion(request.getOsVersion());
		ex.setBaiduChannelId(request.getBaiduChannelId());
		ex.setBaiduUserId(request.getBaiduUserId());
		coachExpandDao.save(ex);		
	}


	public Coach getBasicById(Long coachId) {
		return coachDao.getBasicById(coachId);
	}

	public void updateProfileDetail(UpdateProfileDetailRequest request) {
		Coach c = request.toCoach();
		coachDao.updateProfileDetail(c);	
	}

	public int updateLastAccessTime(Long coachId) {
		return coachDao.updateLastAccessTime(coachId);
	}


	public Object updateAvatar(UploadAvatarRequest request, Coach c) {
		UploadImageResponse response = new UploadImageResponse();
		String extFileName = request.getImageExtFileName();
		if(StringUtils.isNotBlank(extFileName)){
			String oldAvatarUrl = c.getAvatarUrl();
			if(StringUtils.isNotBlank(oldAvatarUrl) && !oldAvatarUrl.startsWith("http")){
				QiniuUtils.deleteFile(oldAvatarUrl);
			}
			String fileNameInQiniu = QiniuUtils.generateCoachImageName(c.getId(), extFileName); 
			List<String>fileNameList = new ArrayList<String>();
			if(!StringUtils.equalsIgnoreCase(oldAvatarUrl, fileNameInQiniu)){
				QiniuUtils.deleteFile(fileNameInQiniu);
			}
			coachDao.updateAvatar(c.getId(), fileNameInQiniu);
			fileNameList.add(fileNameInQiniu);
			
			String uptoken = QiniuUtils.getUptoken(fileNameInQiniu, request.getRopRequestContext().getVersion(), IMAGE_TYPE.COACH_AVATAR, Long.valueOf(c.getId()));
			response.setUptoken(uptoken);
			response.setKeys(fileNameList);
		}
		return response;
	}

	public List<BindOrgResponse> getBindOrg(Long coachId) {
		List<BindOrgResponse> list = coachDao.getBindOrg(coachId);
		for(BindOrgResponse r : list){
			if(r.getStatus()== ORG_COACH_STATUS.COACH_NONE.getValue()){
				r.setStatus(ORG_COACH_DISPLAY_STATUS.ACCEPT.getValue());
			} else if(r.getStatus()== ORG_COACH_STATUS.COACH_ACCEPTED.getValue()){
				r.setStatus(ORG_COACH_DISPLAY_STATUS.BINDED.getValue());
			} else if(r.getStatus()== ORG_COACH_STATUS.ORG_NONE.getValue()){
				r.setStatus(ORG_COACH_DISPLAY_STATUS.APPLIED.getValue());
			} else if(r.getStatus()== ORG_COACH_STATUS.ORG_ACCEPTED.getValue()){
				r.setStatus(ORG_COACH_DISPLAY_STATUS.BINDED.getValue());
			} else{
				r.setStatus(ORG_COACH_DISPLAY_STATUS.APPLY.getValue());
			}
		}
		return list;
	}
	
	public void updateBindOrgStatus(Coach c, Integer orgId) {
		coachDao.updateBindOrgStatus(c, orgId);
	}

	public Long getCoachIdByPhoneNumber(String phoneNumber) {
		return coachDao.getCoachIdByPhoneNumber(phoneNumber);
	}

	@Override
	public Coach getDetailById(Long coachId) {
		return coachDao.getDetailById(coachId);
	}
}
