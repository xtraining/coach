/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-7-17
 */
package com.coach;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Constants;
import com.coach.common.Constants.CACHE_REGION;
import com.coach.model.SysSession;
import com.coach.resolver.SysSessionResolver;
import com.coach.utils.Config;
import com.rop.session.Session;
import com.rop.session.SessionManager;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class MySessionManager implements SessionManager{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired private SysSessionResolver sessionResolver;
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>(128, 0.75f, 32);
    private final CacheChannel cache = CacheChannel.getInstance();

    @Override
    public void addSession(String sessionId, Session session) {
    	sessionMap.put(sessionId, session);
    	cache.set(CACHE_REGION.SESSION.getValue(), sessionId, session);
    }

    @Override
    public Session getSession(String sessionId) {
    	Session session = sessionMap.get(sessionId);
    	if(session != null){
    		return session;
    	}
    	if("1".equals(Config.getProperty(Constants.READ_FROM_CACHE_SWITCH))) {
	    	CacheObject cachObject = cache.get(CACHE_REGION.SESSION.getValue(), sessionId);
	    	session = (Session) cachObject.getValue();
    	}
    	if(session != null){
    		return session;
    	} else {
	    	SysSession sysSession = sessionResolver.getValidSessionBySessionId(sessionId);
	    	if(sysSession == null){
	    		return null;
	    	} else {
	    		 Session tempSession = sysSession.toSession();
	    		 sessionMap.put(sessionId, tempSession);
	    		 if("1".equals(Config.getProperty(Constants.READ_FROM_CACHE_SWITCH))) {
	    			 cache.set(CACHE_REGION.SESSION.getValue(), sessionId, tempSession);
	    		 }
	    		 return tempSession;
	    	}
    	}
    }

    @Override
    public void removeSession(String sessionId) {
    	sessionMap.remove(sessionId);
    	sessionResolver.invalidSessionBySessionId(sessionId);
    	if("1".equals(Config.getProperty(Constants.READ_FROM_CACHE_SWITCH))) {
    		cache.evict(CACHE_REGION.SESSION.getValue(), sessionId);
    	}
    }
}

