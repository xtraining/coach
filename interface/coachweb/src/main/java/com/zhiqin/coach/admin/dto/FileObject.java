/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.io.File;


/**
 * @author Lenovo
 *
 */
public class FileObject {

	private File upload;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
}
