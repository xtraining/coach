package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.ProfileResponse;

public class CoachProfileCacheAction extends CacheAction<ProfileResponse>{
	
	public CoachProfileCacheAction(Long coachId){
		this.region = CACHE_REGION.ONE_DAY;
		this.key = ONE_DAY_CACHE_KEY.COACH_PROFILE.getValue() + coachId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
