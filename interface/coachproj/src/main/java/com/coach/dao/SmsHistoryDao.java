package com.coach.dao;

import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.SmsHistory;



public interface SmsHistoryDao {

	Integer getTodaySmsNumber(SMS_TYPE type, String phoneNumber);

	void save(SmsHistory h);

	void updateStatus(SmsHistory msg);

	SmsHistory getLastestHistory(SMS_TYPE type, String phoneNumber);
	


}
