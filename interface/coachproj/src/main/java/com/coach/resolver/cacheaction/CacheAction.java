package com.coach.resolver.cacheaction;

import net.oschina.j2cache.CacheChannel;

import com.coach.common.Constants.CACHE_REGION;

public abstract class CacheAction<T> {
	protected CacheChannel cache = CacheChannel.getInstance();
	
	protected String key;
	protected CACHE_REGION region;
	
	@SuppressWarnings("unchecked")
	public T getValue(){
		return (T)cache.get(region.getValue(), key).getValue();
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
