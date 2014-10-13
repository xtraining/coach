package com.coach.request;

import javax.validation.constraints.NotNull;




public class ClearCacheRequest extends BaseRequest{
	@NotNull
	private String cachePrefix;

	public String getCachePrefix() {
		return cachePrefix;
	}

	public void setCachePrefix(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}



}

