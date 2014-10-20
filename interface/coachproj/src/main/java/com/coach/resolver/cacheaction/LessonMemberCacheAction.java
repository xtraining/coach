package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.response.LessonMemberResponse;

public class LessonMemberCacheAction extends CacheAction<LessonMemberResponse>{
	
	public LessonMemberCacheAction(Long coachId, Long lessonId){
		this.region = CACHE_REGION.ONE_HOUR;
		this.key = ONE_HOUR_CACHE_KEY.COACH_LESSON_MEMBER.getValue() + "_" + coachId + "_" + lessonId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
