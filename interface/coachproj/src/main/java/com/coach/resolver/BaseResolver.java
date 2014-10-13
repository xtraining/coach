package com.coach.resolver;

import net.oschina.j2cache.CacheChannel;

public abstract class BaseResolver {
	protected static final CacheChannel cache = CacheChannel.getInstance();
}
