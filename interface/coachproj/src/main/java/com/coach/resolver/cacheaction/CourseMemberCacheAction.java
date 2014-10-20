package com.coach.resolver.cacheaction;

import java.util.List;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.CourseMemberResponse;

public class CourseMemberCacheAction extends CacheAction<List<CourseMemberResponse>>{
	
	public CourseMemberCacheAction(Long coachId){
		this.region = CACHE_REGION.ONE_DAY;
		this.key = ONE_DAY_CACHE_KEY.COACH_STUDENT.getValue() + coachId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
