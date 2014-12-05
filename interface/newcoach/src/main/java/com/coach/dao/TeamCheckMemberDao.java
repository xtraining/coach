package com.coach.dao;

import java.util.List;

import com.coach.model.Member;
import com.coach.model.TeamMember;




public interface TeamCheckMemberDao {

	List<Member> getSmsMemberList(Long teamId, Long checkId, List<Long> attendMemberIdList);

	void deleteByTeamCheckId(Long id);

	void saveAttend(Long id, Long teamId, List<Long> attendMemberIdList);

	void saveAbcent(Long id, Long teamId, List<Long> attendMemberIdList);



}
