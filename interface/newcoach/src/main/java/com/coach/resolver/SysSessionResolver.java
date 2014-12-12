package com.coach.resolver;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.coach.common.Constants;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.SYS_SESSION_STATUS;
import com.coach.dao.SysSessionDao;
import com.coach.model.Coach;
import com.coach.model.SysSession;
import com.coach.request.ScanSignInRequest;
import com.coach.response.SignInResponse;
import com.rop.RopRequest;
import com.rop.utils.RopUtils;

@Service
public class SysSessionResolver extends BaseResolver{
	@Resource private SysSessionDao sessionDao;

	public SysSession getValidSessionBySessionId(String sessionId) {
		return sessionDao.getValidSessionBySessionId(sessionId);
	}

	public void invalidSessionBySessionId(String sessionId) {
		sessionDao.invalidSessionBySessionId(sessionId);		
	}

	
	public SignInResponse setupSignInSuccessResponse(RopRequest request, Coach c) {
		SignInResponse response = new SignInResponse();
		SysSession session = new SysSession();
		session.setStatus(SYS_SESSION_STATUS.ACTIVE.getValue());
		if(StringUtils.equals(request.getRopRequestContext().getAppKey(), "web_user")){
			session.setReceiverType(RECEIVER_TYPE.WEB_COACH.getValue());
		} else {
			session.setReceiverType(RECEIVER_TYPE.COACH.getValue());
		}
		session.setReceiverId(Long.valueOf(c.getId()));
		session.setSessionId(RopUtils.getUUID());
		sessionDao.save(session);
		
		request.getRopRequestContext().addSession(session.getSessionId(), session.toSession());
		
		response.setFlag(0);
		response.setMsg(Constants.SUCCESS);
		response.setSessionId(session.getSessionId());
		response.setCoachId(c.getId());
		response.setSmsSwitch(c.getSmsSwitch());
		return response;
	}

	public void removeInvalidSession() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 31 * 60 * 1000);
		sessionDao.removeInalidSession(timestamp);
		
	}

	public void updateLastAccessTime(String sessionId) {
		sessionDao.updateLastAccessTime(sessionId);
		
	}

	public SignInResponse setupWebSignInSuccessResponse(ScanSignInRequest request,
			Long coachId) {
		SignInResponse response = new SignInResponse();
		SysSession session = new SysSession();
		session.setStatus(SYS_SESSION_STATUS.ACTIVE.getValue());
		session.setReceiverType(RECEIVER_TYPE.WEB_COACH.getValue());
		session.setReceiverId(coachId);
		session.setSessionId(RopUtils.getUUID());
		sessionDao.save(session);
		
		request.getRopRequestContext().addSession(session.getSessionId(), session.toSession());
		
		response.setFlag(0);
		response.setMsg(Constants.SUCCESS);
		response.setSessionId(session.getSessionId());
		response.setCoachId(coachId);
		return response;
	}
}
