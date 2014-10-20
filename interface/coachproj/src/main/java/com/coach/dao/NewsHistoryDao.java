package com.coach.dao;

import com.coach.common.Constants.NEWS_TYPE;
import com.coach.model.CoachNewsHistory;




public interface NewsHistoryDao {
	

	public int insert(CoachNewsHistory h);

	public void delete(NEWS_TYPE type, Long coachId);
	

}
