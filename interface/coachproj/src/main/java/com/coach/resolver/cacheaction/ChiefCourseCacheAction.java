package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.response.ChiefCourseResponse;

public class ChiefCourseCacheAction extends CacheAction<ChiefCourseResponse>{
	
	public ChiefCourseCacheAction(Long coachId, Integer type){
		this.region = CACHE_REGION.ONE_HOUR;
		this.key = ONE_HOUR_CACHE_KEY.CHIEF_COURSE.getValue() + coachId + "_" + type;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
