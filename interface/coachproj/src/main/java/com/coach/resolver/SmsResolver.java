package com.coach.resolver;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.coach.common.Config;
import com.coach.common.Constants.SMS_STATUS;
import com.coach.dao.SmsHistoryDao;
import com.coach.model.SmsHistory;
import com.coach.utils.SMSUtil;
import com.coach.utils.SMSUtil2;
@Service
public class SmsResolver extends BaseResolver{
	private static final Logger log = LoggerFactory.getLogger(SmsResolver.class);
	@Resource private SmsHistoryDao smsHistoryDao;
	public void sendVcode(SmsHistory msg) {
		if(StringUtils.equals("1", Config.getProperty("sms_switch"))){
			SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("sms-"+msg.getPhoneNumber());
			executor.setConcurrencyLimit(-1);
		    executor.execute(new SendMessageThread(msg), 60000L);	
		}
	}

	class SendMessageThread implements Runnable {
		private SmsHistory msg;
		public SendMessageThread(SmsHistory msg){
			this.msg = msg;
		}
        public void run() {
        	try {
        		if(StringUtils.equals("1", Config.getProperty("sms_channel"))){
        			SMSUtil.send(msg.getPhoneNumber(), msg.getContent());
        			msg.setStatus(SMS_STATUS.SENT_SUCCESS.getValue());
        		} else if(StringUtils.equals("2", Config.getProperty("sms_channel"))){
        			SMS_STATUS status = SMSUtil2.send(msg.getPhoneNumber(), msg.getContent());
        			msg.setStatus(status.getValue());
        		}
				smsHistoryDao.updateStatus(msg);
			} catch (Throwable e) {
				log.error("SendMessageThread sleep error", e);
				msg.setStatus(SMS_STATUS.SENT_FAILED.getValue());
				smsHistoryDao.updateStatus(msg);
			}
        }
	}
}
