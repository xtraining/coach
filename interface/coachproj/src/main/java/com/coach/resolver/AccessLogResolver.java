package com.coach.resolver;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.coach.dao.AccessLogDao;
import com.coach.model.AccessLog;

@Service
public class AccessLogResolver extends BaseResolver{
	private static final Logger log = LoggerFactory
			.getLogger(AccessLogResolver.class);
	@Resource private AccessLogDao accessLogDao;
	public void save(AccessLog accessLog) {
		accessLogDao.save(accessLog);
	}
	
}
