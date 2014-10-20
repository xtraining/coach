package com.coach.resolver.cacheaction;

import java.util.Arrays;

import net.oschina.j2cache.CacheChannel;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;

public class ClearCacheAction {
	
	public static void clearAddCourseCache(Long coachId) {
		CacheChannel cache = CacheChannel.getInstance();
		cache.evictPrefix(CACHE_REGION.ONE_DAY.getValue(), Arrays.asList(
				new String[]{ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() +  coachId,
						ONE_DAY_CACHE_KEY.COACH_RECENT_LESSON.getValue() + coachId,
						ONE_DAY_CACHE_KEY.COACH_STUDENT.getValue() + coachId}));
	}
}
