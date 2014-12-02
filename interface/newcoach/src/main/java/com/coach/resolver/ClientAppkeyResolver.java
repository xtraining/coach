package com.coach.resolver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coach.dao.ClientAppkeyDao;
import com.coach.model.ClientAppkey;
@Service
public class ClientAppkeyResolver extends BaseResolver{

	@Resource private ClientAppkeyDao clientAppkeyDao;
	
	public List<ClientAppkey> findAllClientAppkey() {
		return clientAppkeyDao.getAll();
	}
	
	public int getOsTypeByAppkey(String appKey){
		return clientAppkeyDao.getOsTypeByAppkey(appKey);
	}

}
