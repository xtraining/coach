package com.zhiqin.coach.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.ContactDao;
import com.zhiqin.coach.admin.dao.SendTaskDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;
import com.zhiqin.coach.admin.service.SendService;

@Service
public class SendServiceImpl implements SendService {
	private static Log log = LogFactory.getLog(SendServiceImpl.class);
	@Resource private SendTaskDao sendTaskDao;
	@Resource private ContactDao contactDao;
	@Override
	public void create(SendTaskDTO sendTask) {
		SearchContactDTO dto = new SearchContactDTO();
		dto.setAreaId(sendTask.getAreaId());
		dto.setSpName(sendTask.getSpName());
		Long sendNum = contactDao.getTotalNum(dto);
		sendTask.setSendNum(sendNum);
		sendTask.setStatus(0);
		sendTaskDao.save(sendTask);
		
		List<Long> contactIdList = contactDao.getContactIdList(dto);
		List<SendSubtaskDTO> sendSubtaskList = new ArrayList<SendSubtaskDTO>();
		int totalSize = contactIdList.size();
		for(int i = 0; i < totalSize; i++){
			int pageNum = i / 20;
			if(i % 20 == 0){
				SendSubtaskDTO sendSubtaskDTO = new SendSubtaskDTO();
				sendSubtaskDTO.setTaskId(sendTask.getId());
				sendSubtaskDTO.setStatus(0);
				sendTaskDao.saveSendSubtask(sendSubtaskDTO);
				int size = totalSize - (pageNum+1)*20;
				if(size > 0){
					sendTaskDao.saveSendSubtaskContact(sendSubtaskDTO.getId(), contactIdList.subList(pageNum * 20, pageNum * 20 + 20));
				} else {
					sendTaskDao.saveSendSubtaskContact(sendSubtaskDTO.getId(), contactIdList.subList(pageNum * 20, totalSize));
				}
				sendSubtaskList.add(sendSubtaskDTO);
			}
		}
		
		SimpleAsyncTaskExecutor executor1 = new SimpleAsyncTaskExecutor("send-sms-"+sendTask.getId());
		executor1.setConcurrencyLimit(-1);
	    executor1.execute(new SendSmsThread(sendSubtaskList), 60000L);	
	}
	
	class SendSmsThread implements Runnable {
		private List<SendSubtaskDTO>sendSubtaskList;
		public SendSmsThread(List<SendSubtaskDTO>sendSubtaskList){
			this.sendSubtaskList = sendSubtaskList;
		}
		public void run() {
        	try {
        		for(SendSubtaskDTO sendSubtask : sendSubtaskList){
//        			List<String> contactList = contactDao.getBySendSubtaskId(sendSubtask.getId());
        		}
			} catch (Throwable e) {
				log.error("UpdateLocationThread error", e);
			}
        }
	}
	
	@Override
	public Long getTotalNum() {
		return sendTaskDao.getTaskTotalNum();
	}
	@Override
	public List<SendTaskDTO> getSendTaskList(PageInfoDTO pageInfo) {
		return sendTaskDao.getSendTaskList(pageInfo);
	}
	@Override
	public Long getSubtaskTotalNum(int sendTaskId) {
		return sendTaskDao.getSubtaskTotalNum(sendTaskId);
	}
	@Override
	public List<SendSubtaskDTO> getSendSubTaskList(int sendTaskId,
			PageInfoDTO pageInfo) {
		return sendTaskDao.getSendSubtaskList(sendTaskId, pageInfo);
	}

}
