package com.coach.dao;

import java.util.List;

import com.coach.model.TeamMember;




public interface TeamMemberDao {

	void insert(TeamMember t);


	void changeMemberToDone(Long teamId, List<Long> list);

}
