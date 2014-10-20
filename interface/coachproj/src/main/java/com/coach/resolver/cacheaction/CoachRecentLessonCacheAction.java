package com.coach.resolver.cacheaction;

import java.util.Date;
import java.util.List;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.LessonDetailResponse;
import com.coach.response.LessonResponse;
import com.coach.utils.DateUtils;

public class CoachRecentLessonCacheAction extends CacheAction<List<LessonDetailResponse>>{
	
	public CoachRecentLessonCacheAction(Long coachId, Date startDate, int days){
		this.region = CACHE_REGION.ONE_DAY;
		this.key = ONE_DAY_CACHE_KEY.COACH_RECENT_LESSON.getValue() + coachId + "_" + DateUtils.dateToyyyyMMdd(startDate) + "_" + days;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
