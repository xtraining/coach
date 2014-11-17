package com.coach.resolver;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coach.common.Constants;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.SYS_SESSION_STATUS;
import com.coach.dao.SysSessionDao;
import com.coach.model.Coach;
import com.coach.model.SysSession;
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

	private SysSession buildCoachSession(Coach c) {
		SysSession session = new SysSession();
		session.setStatus(SYS_SESSION_STATUS.ACTIVE.getValue());
		session.setReceiverType(RECEIVER_TYPE.COACH.getValue());
		session.setReceiverId(Long.valueOf(c.getId()));
		session.setSessionId(RopUtils.getUUID());
		sessionDao.save(session);
		return session;
	}
	
	public SignInResponse setupSignInSuccessResponse(RopRequest request, Coach c) {
		SignInResponse response = new SignInResponse();
		SysSession session = buildCoachSession(c);
		request.getRopRequestContext().addSession(session.getSessionId(), session.toSession());
		
		response.setFlag(0);
		response.setMsg(Constants.SUCCESS);
		response.setSessionId(session.getSessionId());
		response.setCoachId(c.getId());
		response.setOrgSwitch(c.getOrgSwitch());
		return response;
	}
}
