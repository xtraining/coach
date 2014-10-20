package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.response.CourseDetailResponse;

public class CourseDetailCacheAction extends CacheAction<CourseDetailResponse>{
	
	public CourseDetailCacheAction(Long coachId, Long courseId){
		this.region = CACHE_REGION.ONE_HOUR;
		this.key = ONE_DAY_CACHE_KEY.COACH_COURSE.getValue() + coachId + "_" + courseId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
