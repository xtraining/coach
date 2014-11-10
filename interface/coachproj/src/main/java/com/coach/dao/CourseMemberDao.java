package com.coach.dao;

import java.util.List;

import com.coach.model.CourseMember;



public interface CourseMemberDao {
	
	public void save(List<CourseMember> cmList);

	public void deleteMember(Long courseId, String[] deletedMemberIdArr);

}
