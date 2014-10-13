package com.coach.dao;

import java.util.List;

import com.coach.model.CourseMember;
import com.coach.model.User;



public interface CourseMemberDao {
	
	public void save(List<CourseMember> cmList);

}
