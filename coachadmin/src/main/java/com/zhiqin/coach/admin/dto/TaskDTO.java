/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 *
 */
public class TaskDTO {

	private Integer id;
	private String url;
	private Integer downloadedNum;
	private Integer totalNum;
	private Integer importedNum;
	private Timestamp createTime;
	private Integer status;
	private Integer sourceFrom;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSourceFrom() {
		return sourceFrom;
	}
	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public Integer getDownloadedNum() {
		return downloadedNum;
	}
	public void setDownloadedNum(Integer downloadedNum) {
		this.downloadedNum = downloadedNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getImportedNum() {
		return importedNum;
	}
	public void setImportedNum(Integer importedNum) {
		this.importedNum = importedNum;
	}
	
}
