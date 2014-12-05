package com.coach.dao;

import java.sql.Timestamp;
import java.util.List;

import com.coach.model.Member;
import com.coach.model.TeamCheck;
import com.coach.model.TeamMember;





public interface TeamCheckDao {

	void updateTime(Long id);

	void insert(TeamCheck check);

	void updateLoaction(Long teamCheckId, String province, String city,
			String district, String street, String streetNumber);

	Long getLatestCheck(Long teamId, Timestamp startTime);

	List<Member> getMemberByCheckId(Long coachId, Long teamCheckId);

	List<TeamCheck> getTeamCheckHistory(Long teamId, Integer pageNumber,
			Integer pageSize);

	List<TeamCheck> getMemberCheckHistory(Long coachId, Long teamId,
			Long memberId);

	List<TeamMember> getTeamMemberListByPhoneNumber(Long coachId, String phoneNumber);


}
