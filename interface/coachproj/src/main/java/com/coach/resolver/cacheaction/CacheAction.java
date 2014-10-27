package com.coach.resolver.cacheaction;

import net.oschina.j2cache.CacheChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.utils.Config;

public abstract class CacheAction<T> {
	private static final Logger log = LoggerFactory
			.getLogger(CacheAction.class);
	protected CacheChannel cache = CacheChannel.getInstance();
	
	protected String key;
	protected CACHE_REGION region;
	
	@SuppressWarnings("unchecked")
	public T getValue(){
		if("1".equals(Config.getProperty("read_from_cache_switch"))) {
			log.info("load from cache region : " + region + " key : " + key);
			return (T)cache.get(region.getValue(), key).getValue();
		} else {
			log.info("load from db region : " + region + " key : " + key);
			return null;
		}
	}
	
	public void setValue(T value) {
		cache.set(region.getValue(), key, value);
		clearRelativeCache();
	}
	
	public void clear() {
		cache.evict(region.getValue(), key);
	}
	
	protected abstract void clearRelativeCache();
	
}
