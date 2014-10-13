/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-5-25
 */
package com.coach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Constants.CLIENT_APPKEY_STATUS;
import com.coach.model.AccessPrivilege;
import com.coach.model.ClientAppkey;
import com.coach.resolver.AccessPrivilegeResolver;
import com.coach.resolver.ClientAppkeyResolver;
import com.rop.security.ServiceAccessController;
import com.rop.session.Session;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class MyServiceAccessController implements ServiceAccessController {

	private static final Map<String, List<String>> aclMap = new HashMap<String, List<String>>();
    @Autowired
	private AccessPrivilegeResolver accessPrivilegeResolver;
    @Autowired
    private ClientAppkeyResolver clientAppkeyResolver;

    @Override
    public boolean isAppGranted(String appKey, String method, String version) {
    	if(aclMap.isEmpty()){
    		loadPrivilege();
    	}
        if(aclMap.containsKey(appKey)){
            List<String> serviceMethods = aclMap.get(appKey);
            boolean access = serviceMethods.contains(method);
            return access;
        }else{
            return false;
        }
    }

    private void loadPrivilege() {
    	List<ClientAppkey> list = clientAppkeyResolver.findAllClientAppkey();
		for(ClientAppkey appkey : list){
			if(appkey.getStatus().intValue() == CLIENT_APPKEY_STATUS.ACTIVE.getValue()){
				List<String> privilegeList = accessPrivilegeResolver.findClientPrivilege(appkey.getAppKey());
				List<String> serviceMethods = new ArrayList<String>();
				for(String methodName : privilegeList){		
					serviceMethods.add(methodName);
				}
				aclMap.put(appkey.getAppKey(), serviceMethods);
			}
		}	
	}

	@Override
    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}

