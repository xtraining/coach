package com.coach.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.coach.request.ClearCacheRequest;
import com.coach.request.GetSubareaRequest;
import com.coach.resolver.AreaResolver;
import com.coach.response.AreaResponse;
import com.coach.response.SimpleResponse;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean
public class CommonService extends SimpleBaseService{
	@Autowired private AreaResolver areaResolver;
	
	@ServiceMethod(method = "common.clearCache", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object clearCache(ClearCacheRequest request) {
		areaResolver.clearCache(request.getCachePrefix());
		return new SimpleResponse();
	}
	
	@ServiceMethod(method = "common.getSubarea", version = "1.0", needInSession = NeedInSessionType.YES)
    public Object getSubarea(GetSubareaRequest request) {
		AreaResponse r = areaResolver.getSubareaByCode(request.getAreaCode());
		return r;
	}
}
