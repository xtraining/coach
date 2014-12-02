/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-6-2
 */
package com.coach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    @Override
    public void onRopEvent(AfterDoServiceEvent ropEvent) {
        RopRequestContext ropRequestContext = ropEvent.getRopRequestContext();
        if(ropRequestContext != null && ropRequestContext.getRopRequest() != null){
            String message = MessageMarshallerUtils.getMessage(ropRequestContext.getRopResponse(), MessageFormat.json);
            log.info("response:" + message);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

