package com.zhiqin.coach.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhiqin.coach.admin.dao.ServerInfoDao;
import com.zhiqin.coach.admin.entity.ServerInfo;

@Repository("serverInfoDao")
public class ServerInfoDaoImpl extends BaseDaoImpl<ServerInfo> implements ServerInfoDao{

}
