package com.coach.dao;

import java.sql.Timestamp;
import java.util.List;

import com.coach.common.Constants.TEAM_TYPE;
import com.coach.model.Team;
import com.coach.model.TeamCheck;




public interface TeamDao {

	void insert(Team t);

	void updateTeam(Team t);

	List<Team> getListByCoachId(Long coachId, Integer status);

	void updateTeamStatus(Team t);

	Long getDoneNumber(Long coachId, TEAM_TYPE type);

	boolean checkOverDue(Long id, Timestamp yyyyMMddHHmmssToTimestamp);

}
