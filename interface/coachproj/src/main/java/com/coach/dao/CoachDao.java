package com.coach.dao;

import java.util.List;

import com.coach.model.Coach;
import com.coach.response.BindOrgResponse;
import com.coach.response.ProfileResponse;




public interface CoachDao {

	public Long getIdByCredentials(String phoneNumber, String password);

	public void save(Coach c);

	public Long getByThirdPartyId(String thirdPartyId, Integer type);

	public void resetPassword(String phoneNumber, String password);

	public Coach getBasicById(Long coachId);

	public void updateProfile(Coach c);

	public Coach getDetailById(Long coachId);

	public void updateProfileDetail(Coach c);

	public int updateLastAccessTime(Long coachId);

	public void unbindThirdParty(int type, Long id);

	public void updateAvatar(Long id, String fileNameInQiniu);

	public List<BindOrgResponse> getBindOrg(Long coachId);

	public void updateBindOrgStatus(Coach c, Integer orgId);

	public Long getCoachIdByPhoneNumber(String phoneNumber);

}
