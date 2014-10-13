package com.coach.resolver;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.dao.AreaDao;
import com.coach.response.AreaResponse;

@Service
public class AreaResolver extends BaseResolver{
	private static final Logger log = LoggerFactory
			.getLogger(AreaResolver.class);
	@Resource private AreaDao areaDao;
	
	public AreaResponse getSubareaByCode(String areaCode) {
		CacheObject cachObject = cache.get(CACHE_REGION.ONE_DAY.getValue(), areaCode);
		AreaResponse r = (AreaResponse) cachObject.getValue();
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
			cache.set(CACHE_REGION.ONE_DAY.getValue(), areaCode, r);
			return r;
    	}
	}

	public void clearCache(String prefix) {
		cache.evictPrefix(CACHE_REGION.DEFAULT.getValue(), prefix);
		cache.evictPrefix(CACHE_REGION.ONE_DAY.getValue(), prefix);
		cache.evictPrefix(CACHE_REGION.ONE_HOUR.getValue(), prefix);
	}
	
}
