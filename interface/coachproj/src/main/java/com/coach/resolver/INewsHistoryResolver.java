package com.coach.resolver;

import org.springframework.stereotype.Service;

import com.coach.common.Constants.NEWS_TYPE;
@Service
public interface INewsHistoryResolver{

	void updateHistory(NEWS_TYPE type, Integer coachId);
}
