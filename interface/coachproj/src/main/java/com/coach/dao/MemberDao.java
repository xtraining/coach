package com.coach.dao;

import java.util.List;

import com.coach.model.Member;
import com.coach.response.MemberDetailResponse;
import com.coach.response.MemberResponse;
import com.coach.response.SearchMemberResponse;



public interface MemberDao {
	

	public void save(Member m);

	public List<Member> getMemberByCourseId(Long courseId);

	public List<Member> getMemberByCoachId(Long coachId);

	public List<Member> getMemberByCoachIdAndKeyword(Long coachId, String keyword);

	public List<Member> getMemberByCoachId(Long coachId,
			Long courseId);

	public List<Member> getMemberWithAttendHistory(Long coachId, Long courseId, Long memberId);

	public void updateMember(Member m);

	public void updateMemberStatus(Long memberId, Long courseId, Integer status);

	public Member getMemberByCourseIdAndMemberId(Long courseId, Long memberId);

	public void updateBasicMember(Member m);


}
