package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.SendTaskDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SendSubtaskDTO;
import com.zhiqin.coach.admin.dto.SendTaskDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SendTaskDaoImpl extends BaseDaoImpl implements SendTaskDao
{
	private static final Logger log = LoggerFactory
			.getLogger(SendTaskDaoImpl.class);

	@Override
	public void save(SendTaskDTO sendTask) {
		try{
			this.getSqlSession().insert("sendtask.save", sendTask);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

	@Override
	public void saveSendSubtask(SendSubtaskDTO sendSubtaskDTO) {
		try{
			this.getSqlSession().insert("sendtask.saveSendSubtask", sendSubtaskDTO);
		} catch(RuntimeException e){
			log.error("saveSendSubtask", e);
			throw e;
		}
	}

	@Override
	public void saveSendSubtaskContact(Long sendSubtaskId,
			List<Long> contactIdList) {
		try{
			Map map = new HashMap();
			map.put("sendSubtaskId", sendSubtaskId);
			map.put("contactIdList", contactIdList);
			this.getSqlSession().insert("sendtask.saveSendSubtaskContact", map);
		} catch(RuntimeException e){
			log.error("saveSendSubtaskContact", e);
			throw e;
		}
		
	}

	@Override
	public Long getTaskTotalNum() {
		try{
			return this.getSqlSession().selectOne("sendtask.getTaskTotalNum");
		} catch(RuntimeException e){
			log.error("getTaskTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<SendTaskDTO> getSendTaskList(PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("sendtask.getSendTaskList", map);
		} catch(RuntimeException e){
			log.error("getSendTaskList", e);
			throw e;
		}
	}

	@Override
	public Long getSubtaskTotalNum(int sendTaskId) {
		try{
			return this.getSqlSession().selectOne("sendtask.getSubtaskTotalNum", sendTaskId);
		} catch(RuntimeException e){
			log.error("getSubtaskTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<SendSubtaskDTO> getSendSubtaskList(int sendTaskId,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("sendTaskId", sendTaskId);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("sendtask.getSendSubtaskList", map);
		} catch(RuntimeException e){
			log.error("getSendSubtaskList", e);
			throw e;
		}
	}

	@Override
	public void updateStatus(Long subtaskId, int resultCode) {
		try{
			Map map = new HashMap();
			map.put("resultCode", resultCode);
			map.put("subtaskId", subtaskId);
			this.getSqlSession().insert("sendtask.updateStatus", map);
		} catch(RuntimeException e){
			log.error("updateStatus", e);
			throw e;
		}
		
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("sendtask.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("deleteByIds", e);
			throw e;
		}
	}

}
