package com.coach.dao;

import java.util.List;

import com.coach.model.Coach;




public interface CoachDao {

	public Coach getIdByCredentials(String phoneNumber, String password);

	public void save(Coach c);

	public void resetPassword(String phoneNumber, String password);

	public Coach getBasicById(Long coachId);

	public void updateProfile(Coach c);

	public Coach getDetailById(Long coachId);

	public void updateProfileDetail(Coach c);

	public int updateLastAccessTime(Long coachId);

	public void unbindThirdParty(int type, Long id);

	public Long getCoachIdByPhoneNumber(String phoneNumber);

	public List<Long> getByToken(Long coachId, String token);

}
