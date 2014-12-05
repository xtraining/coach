package com.coach.dao;

import java.util.List;

import com.coach.model.Member;




public interface MemberDao {

	void insert(Member t);

	List<Member> getList(Long coachId, Long teamId);

	void update(Long coachId, Long teamId, Member m);

	void delete(Long coachId, Long teamId, Long memberId);


}
