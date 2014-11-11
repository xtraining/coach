package com.coach.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.request.UploadCallbackRequest;
import com.coach.response.CallbackSuccessResponse;
import com.coach.utils.Config;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

@ServiceMethodBean(ignoreSign=IgnoreSignType.YES)
public class QiniuCallbackService extends SimpleBaseService{
	private static final Logger log = LoggerFactory
			.getLogger(QiniuCallbackService.class);
	@ServiceMethod(method = "qiniu.uploadCallback",version = "1.0",needInSession = NeedInSessionType.NO)
    public Object uploadCallback(UploadCallbackRequest request) { 
		CallbackSuccessResponse r = new CallbackSuccessResponse();
		r.setKey(Config.getProperty("QINIU_DOMAIN") + request.getName());
		log.info("callback from qiniu:", request.toString());
		return r;
	}
}
