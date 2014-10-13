package com.coach.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants;
import com.coach.common.Constants.IMAGE_TYPE;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

public class QiniuUtils {
	private static final Logger log = LoggerFactory.getLogger(QiniuUtils.class);
	
	/**
	 * type : 分别表示问题图片，答案图片，用户头像
	 * objectId : 分别是questionId, answerId, memberId
	 * @param appVersion
	 * @param type
	 * @param objectId
	 * @return
	 */
	public static String getUptoken(String v, IMAGE_TYPE type, Long objectId){
        Mac mac = new Mac(Config.getProperty("QINIU_ACCESS_KEY"), Config.getProperty("QINIU_SECRET_KEY"));
        String bucketName = Config.getProperty("QINIU_QA_BUCKET_NAME");
        PutPolicy putPolicy = new PutPolicy(bucketName);
        putPolicy.expires = 10 * 60;
        putPolicy.callbackUrl = Config.getProperty("QINIU_CALLBACK_URL");
        putPolicy.callbackBody = "name=$(fname)&hash=$(etag)&method=qiniu.uploadCallback&v="+v+"&format=json&appKey=qiniu&type="+type.getValue()+"&objectId="+objectId;
        String uptoken = null;
		try {
			uptoken = putPolicy.token(mac);
		} catch (Throwable e){
			log.error("生成token错误", e);
		}
        return uptoken;
	}

	public static String generateCoachImageName(Integer coachId, String extFileName) {
		String suffix = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		String fileName = Constants.COACH_AVATAR_PREFIX + "_" + coachId + "_" + suffix + "." + extFileName;
		return fileName;
	}

	public static void deleteFile(String fileName) {
		Mac mac = new Mac(Config.getProperty("QINIU_ACCESS_KEY"), Config.getProperty("QINIU_SECRET_KEY"));
        RSClient client = new RSClient(mac);
        String bucketName = Config.getProperty("QINIU_QA_BUCKET_NAME");
        client.delete(bucketName, fileName);
	}

	public static int upload(String uptoken, String fileNameInQiniu, File upload) {
		PutExtra extra = new PutExtra();
		PutRet ret = IoApi.putFile(uptoken, fileNameInQiniu, upload, extra);
		if (ret.ok()) {
			return 0;
		} else {
			return 1;
		}
	}

}
