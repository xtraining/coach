package com.coach.resolver;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.coach.common.Config;
import com.coach.common.Constants.RECEIVER_TYPE;
import com.coach.common.Constants.SMS_STATUS;
import com.coach.common.Constants.SMS_TYPE;
import com.coach.dao.SmsHistoryDao;
import com.coach.model.Member;
import com.coach.model.SmsHistory;
import com.coach.utils.DateUtils;
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
        			smsHistoryDao.updateStatus(msg);
        		} else if(StringUtils.equals("2", Config.getProperty("sms_channel"))){
        			SMS_STATUS status = SMSUtil2.send(msg.getPhoneNumber(), msg.getContent());
        			msg.setStatus(status.getValue());
        			smsHistoryDao.updateStatus(msg);
        		}
				smsHistoryDao.updateStatus(msg);
			} catch (Throwable e) {
				log.error("SendMessageThread sleep error", e);
				msg.setStatus(SMS_STATUS.SENT_FAILED.getValue());
				smsHistoryDao.updateStatus(msg);
			}
        }
	}

	public void sendAttendSms(List<Member> smsMemberList, String phoneNumber) {
		SimpleAsyncTaskExecutor executor2 = new SimpleAsyncTaskExecutor("sms-"+smsMemberList.toString());
		executor2.setConcurrencyLimit(-1);
	    executor2.execute(new SendSmsThread(smsMemberList, phoneNumber), 60000L);
	}
	
	class SendSmsThread implements Runnable {
		private List<Member> smsMemberList;
		private String phoneNumber;
		public SendSmsThread(List<Member> smsMemberList, String phoneNumber){
			this.smsMemberList = smsMemberList;
			this.phoneNumber = phoneNumber;
		}
        public void run() {
        	try {
        		for(Member m : smsMemberList){
        			String msg = null;
        			if(m.getStatus() == 0){
        				msg = Config.getProperty("absent_msg");
        			} else {
        				msg = Config.getProperty("attend_msg");
        			}
        			msg = StringUtils.replace(msg, "{memberName}", m.getName());
        			msg = StringUtils.replace(msg, "{date}", DateUtils.dateToMMDD(new Date()));
        			msg = StringUtils.replace(msg, "{phoneNumber}", phoneNumber);
        			SMS_STATUS status = SMS_STATUS.SENT_SUCCESS;
        			if(StringUtils.equals("1", Config.getProperty("sms_channel"))){
        				SMSUtil.send(m.getPhoneNumber(), msg);
        			} else if(StringUtils.equals("2", Config.getProperty("sms_channel"))){
        				status = SMSUtil2.send(m.getPhoneNumber(), msg);
        			}
        			SmsHistory h = new SmsHistory();
        			h.setPhoneNumber(m.getPhoneNumber());
        			h.setContent(msg);
        			h.setReceiverId(m.getId());
        			h.setType(SMS_TYPE.ALERT_MSG.getValue());
        			h.setVcode("000000");
        			h.setStatus(status.getValue());
        			h.setReceiverType(RECEIVER_TYPE.MEMBER.getValue());
        			smsHistoryDao.save(h);
        		}
			} catch (Throwable e) {
				log.error("SendSmsThread  error", e);
			}
        }
	}

}
