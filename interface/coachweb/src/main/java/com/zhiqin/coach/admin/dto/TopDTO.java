/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.sql.Timestamp;



/**
 * @author Lenovo
 *
 */
public class TopDTO {

	private Long id;
	private String name;
	private Integer type;
	private Integer topOrder;
	private Integer status;
	private String startTimeStr;
	private Timestamp startTime;
	private String listImageFileName;
	private String listImageFileUrl;
	private String tags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTopOrder() {
		return topOrder;
	}
	public void setTopOrder(Integer topOrder) {
		this.topOrder = topOrder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getListImageFileName() {
		return listImageFileName;
	}
	public void setListImageFileName(String listImageFileName) {
		this.listImageFileName = listImageFileName;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getListImageFileUrl() {
		return listImageFileUrl;
	}
	public void setListImageFileUrl(String listImageFileUrl) {
		this.listImageFileUrl = listImageFileUrl;
	}
	
}
