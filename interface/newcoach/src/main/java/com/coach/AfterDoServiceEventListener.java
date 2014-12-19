/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-6-2
 */
package com.coach;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coach.common.Constants.ACCESS_LOG_TYPE;
import com.coach.model.AccessLog;
import com.coach.resolver.AccessLogResolver;
import com.rop.MessageFormat;
import com.rop.RopRequest;
import com.rop.RopRequestContext;
import com.rop.event.AfterDoServiceEvent;
import com.rop.event.RopEventListener;
import com.rop.marshaller.MessageMarshallerUtils;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class AfterDoServiceEventListener implements RopEventListener<AfterDoServiceEvent> {
	private static final Logger log = LoggerFactory.getLogger(AfterDoServiceEventListener.class);
	@Autowired
	private AccessLogResolver logResolver;
    @Override
    public void onRopEvent(AfterDoServiceEvent ropEvent) {
        RopRequestContext ropRequestContext = ropEvent.getRopRequestContext();
        if(ropRequestContext != null && ropRequestContext.getRopRequest() != null){
            String message = MessageMarshallerUtils.getMessage(ropRequestContext.getRopResponse(), MessageFormat.json);
            log.info("response:" + message);
            AccessLog accessLog = new AccessLog();
            accessLog.setAppkey(ropRequestContext.getAppKey());
            accessLog.setIp(ropRequestContext.getIp());
            accessLog.setMethod(ropRequestContext.getMethod());
            if (message != null && message.length() > 1900) {
            	message = message.substring(0,1900);
            }
            accessLog.setMessage(message);
            accessLog.setType(ACCESS_LOG_TYPE.RESPONSE.getValue());
            accessLog.setCreateTime(new Timestamp(ropEvent.getServiceBeginTime()));
            logResolver.save(accessLog);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

