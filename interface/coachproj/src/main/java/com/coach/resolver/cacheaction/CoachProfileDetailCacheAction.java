package com.coach.resolver.cacheaction;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_DAY_CACHE_KEY;
import com.coach.response.ProfileDetailResponse;

public class CoachProfileDetailCacheAction extends CacheAction<ProfileDetailResponse>{
	
	public CoachProfileDetailCacheAction(Long coachId){
		this.region = CACHE_REGION.ONE_DAY;
		this.key = ONE_DAY_CACHE_KEY.COACH_PROFILE_DETAIL.getValue() + coachId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
