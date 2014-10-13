package com.coach.dao;

import java.util.List;

import com.coach.model.Coach;
import com.coach.response.BindOrgResponse;
import com.coach.response.ProfileResponse;




public interface CoachDao {

	public Integer getIdByCredentials(String phoneNumber, String password);

	public Integer getByPhoneNumber(String phoneNumber);

	public void save(Coach c);

	public Integer getByThirdPartyId(String thirdPartyId, Integer type);

	public void resetPassword(String phoneNumber, String password);

	public Coach getBasicById(Integer coachId);

	public void updateProfile(Coach c);

	public Coach getDetailById(Integer coachId);

	public void updateProfileDetail(Coach c);

	public int updateLastAccessTime(Integer coachId);

	public void unbindThirdParty(int type, Integer id);

	public void updateAvatar(Integer id, String fileNameInQiniu);

	public List<BindOrgResponse> getBindOrg(Integer coachId);

	public void updateBindOrgStatus(Integer coachId, Integer orgId);

}
