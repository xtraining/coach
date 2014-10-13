package com.coach.dao;

import java.util.List;

import com.coach.model.MemberNews;
import com.coach.response.MemberNewsResponse;



public interface MemberNewsDao {
	

	public int insert(MemberNews news);

	public List<MemberNewsResponse> getNews(Integer coachId);
	

}
