package com.coach.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.resolver.SysSessionResolver;
import com.rop.AbstractInterceptor;
import com.rop.RopRequestContext;

@Component
public class WebUserAccessInterceptor extends AbstractInterceptor{
	@Autowired private SysSessionResolver sessionResolver;
	@Override
	public void beforeService(RopRequestContext ropRequestContext) {
		if(isMatch(ropRequestContext)){
			sessionResolver.updateLastAccessTime(ropRequestContext.getSessionId());
		}
	}

	@Override
	public boolean isMatch(RopRequestContext ropRequestContext) {
		String appKey = ropRequestContext.getAppKey();
		if(StringUtils.equals(appKey, "web_user")){
			return true;
		} else {
			return false;
		}
		
	}

}
