package com.coach.dao;

import java.util.List;

import com.coach.model.Team;




public interface TeamDao {

	void insert(Team t);

	void updateTeam(Team t);

	List<Team> getListByCoachId(Long coachId, Integer status);

	void updateTeamStatus(Team t);

}
