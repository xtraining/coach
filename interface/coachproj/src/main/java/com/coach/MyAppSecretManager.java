package com.coach; /**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-5-25
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Constants.CLIENT_APPKEY_STATUS;
import com.coach.model.ClientAppkey;
import com.coach.resolver.ClientAppkeyResolver;
import com.rop.security.AppSecretManager;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class MyAppSecretManager implements AppSecretManager {

	@Autowired
    private ClientAppkeyResolver clientAppkeyResolver;
    
    private static Map<String, String> appKeySecretMap = new HashMap<String, String>();

	@Override
    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
	
    @Override
    public String getSecret(String appKey) {
    	if(appKeySecretMap.isEmpty()){
    		loadAppKey();
    	}
        return appKeySecretMap.get(appKey);
    }
	
    private void loadAppKey() {
		List<ClientAppkey> list = clientAppkeyResolver.findAllClientAppkey();
		for(ClientAppkey client : list){
			if(StringUtils.isNotBlank(client.getSecretKey()) && client.getStatus().intValue() == CLIENT_APPKEY_STATUS.ACTIVE.getValue()){
				appKeySecretMap.put(client.getAppKey(), client.getSecretKey());
			}
		}		
	}
}

