package com.coach.resolver.cacheaction;

import java.util.List;

import com.coach.common.Constants.CACHE_REGION;
import com.coach.common.Constants.ONE_HOUR_CACHE_KEY;
import com.coach.response.LessonMemberResponse;
import com.coach.response.MemberResponse;

public class LessonMemberListCacheAction extends CacheAction<List<MemberResponse>>{
	
	public LessonMemberListCacheAction(Long coachId, Long lessonId){
		this.region = CACHE_REGION.ONE_HOUR;
		this.key = ONE_HOUR_CACHE_KEY.COACH_LESSON_MEMBER_LIST.getValue() + "_" + coachId + "_" + lessonId;
	}
	
	@Override
	protected void clearRelativeCache() {
		// TODO Auto-generated method stub
		
	}
	
	
}
