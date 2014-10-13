package com.coach.dao;

import com.coach.model.Coach;




public interface AppVersionDao {

	Integer getIdByVersion(String appVersion, int osType);


}
