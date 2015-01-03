package com.zhiqin.coach.admin.util;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.zhiqin.coach.admin.common.Constants;
import com.zhiqin.coach.admin.common.Constants.FILE_NAME_PREFIX;

public class QiniuUtils {
	public static String getUptoken() throws AuthException, JSONException{
        Mac mac = new Mac(Config.getProperty("QINIU_ACCESS_KEY"), Config.getProperty("QINIU_SECRET_KEY"));
        String bucketName = Config.getProperty("QINIU_QA_BUCKET_NAME");
        PutPolicy putPolicy = new PutPolicy(bucketName);
        putPolicy.expires = 10 * 60;
        String uptoken = putPolicy.token(mac);
        return uptoken;
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

	public static String generateTagImageName(Long imageId, String uploadFileName) {
		String suffix = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		String extFileName = uploadFileName.substring(uploadFileName.lastIndexOf("."));	
		String fileName = Constants.TAG_IMAGE_PREFIX + "_" + imageId + "_" + suffix + extFileName;
		return fileName;
	}

	public static String generateArtifactImageName(Long id,
			String originalFilename) {
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));	
		String dateStr = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		return FILE_NAME_PREFIX.STORY_IMAGE.getValue() + id + "_" + dateStr  + extFileName;
	}

	public static String generateArtifactMediaName(Long id,
			String originalFilename) {
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));	
		return FILE_NAME_PREFIX.VOICE.getValue() + id + extFileName;
	}

	public static String generateTopListImageName(Long id,
			String originalFilename) {
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));	
		String dateStr = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		return FILE_NAME_PREFIX.TOP_LIST_IMAGE.getValue() + id + "_" + dateStr + extFileName;
	}
	
	public static String generateCategoryImageName(Long id,
			String originalFilename) {
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));	
		String dateStr = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		return FILE_NAME_PREFIX.CATEGORY.getValue() + id + "_" + dateStr + extFileName;
	}

	public static String generateTopListDetailImageName(Long id,
			String originalFilename) {
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));	
		String dateStr = DateUtils.dateToyyyyMMddHHmissWithSeparator(new Timestamp(new Date().getTime()));
		return FILE_NAME_PREFIX.TOP_DETAIL_IMAGE.getValue() + id + "_" + dateStr  + extFileName;
	}

}
