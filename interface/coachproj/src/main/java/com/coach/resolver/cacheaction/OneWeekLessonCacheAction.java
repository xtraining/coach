package com.coach.resolver.cacheaction;

import java.util.Date;
import java.util.List;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.LessonResponse;
import com.coach.utils.DateUtils;

public class OneWeekLessonCacheAction extends CacheAction<List<LessonResponse>>{
	
	public OneWeekLessonCacheAction(Long coachId, Date startDate, Date endDate){
		this.region = CACHE_REGION.ONE_HOUR;
		this.key = ONE_DAY_CACHE_KEY.COACH_ONE_WEEK_LESSON.getValue() + coachId + "_" + DateUtils.dateToyyyyMMdd(startDate)+ "_" + DateUtils.dateToyyyyMMdd(endDate);
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
