package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.dao.ServerInfoDao;
import com.zhiqin.coach.admin.entity.ServerInfo;
import com.zhiqin.coach.admin.service.ServerInfoService;
import com.zhiqin.coach.admin.util.PageView;

@Transactional
@Service("serverInfoService")
public class ServerInfoServiceImpl implements ServerInfoService{
	@Autowired
	private ServerInfoDao serverInfoDao;

	public void add(ServerInfo serverInfo) {
		serverInfoDao.add(serverInfo);
	}

	public void delete(String id) {
		serverInfoDao.delete(id);
	}

	public ServerInfo getById(String id) {
		return serverInfoDao.getById(id);
	}
	
	//编译指令
	public PageView query(PageView pageView, ServerInfo serverInfo) {
		List<ServerInfo> list = serverInfoDao.query(pageView, serverInfo);
		pageView.setRecords(list);
		return pageView;
	}
	
	public List<ServerInfo> queryAll(ServerInfo serverInfo) {
		return serverInfoDao.queryAll(serverInfo);
	}

	public void modify(ServerInfo serverInfo) {
		serverInfoDao.modify(serverInfo);
	}
	
}
