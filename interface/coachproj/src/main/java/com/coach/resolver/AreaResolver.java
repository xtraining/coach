package com.coach.resolver;

import java.util.List;

import javax.annotation.Resource;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.dao.AreaDao;
import com.coach.resolver.cacheaction.AreaCacheAction;
import com.coach.resolver.cacheaction.CacheAction;
import com.coach.response.AreaResponse;

@Service
public class AreaResolver extends BaseResolver{
	private static final Logger log = LoggerFactory
			.getLogger(AreaResolver.class);
	@Resource private AreaDao areaDao;
	
	public AreaResponse getSubareaByCode(String areaCode) {
		CacheAction<AreaResponse> cacheAction = new AreaCacheAction(areaCode);
		AreaResponse r = cacheAction.getValue();
    	if(r != null){
    		return r;
    	} else {
			r = new AreaResponse();
			List<AreaResponse> list = null;
			if(StringUtils.isBlank(areaCode)){
				list = areaDao.getAllProvinces();
			} else {
				list = areaDao.getSubareaByCode(areaCode);
			}
			r.setAreaCode(areaCode);
			r.setSubareaList(list);
			cacheAction.setValue(r);
			return r;
    	}
	}

	public void clearCache(String prefix) {
		CacheChannel cache = CacheChannel.getInstance();
		cache.evictPrefix(CACHE_REGION.DEFAULT.getValue(), prefix);
		cache.evictPrefix(CACHE_REGION.ONE_DAY.getValue(), prefix);
		cache.evictPrefix(CACHE_REGION.ONE_HOUR.getValue(), prefix);
	}
	
}
