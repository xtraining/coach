package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.LessonDetailResponse;

public class LessonDetailCacheAction extends CacheAction<LessonDetailResponse>{
	
	public LessonDetailCacheAction(Long coachId, Long lessonId){
		this.region = CACHE_REGION.ONE_DAY;
		this.key = ONE_DAY_CACHE_KEY.COACH_LESSON.getValue() + coachId + "_" + lessonId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
