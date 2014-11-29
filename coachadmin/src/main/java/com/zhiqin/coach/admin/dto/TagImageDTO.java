/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.util.List;

/**
 * @author Lenovo
 *
 */
public class TagImageDTO {

	private Long id;
	private String fileName;
	public String tagNameList;
	private String url;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTagNameList() {
		return tagNameList;
	}
	public void setTagNameList(String tagNameList) {
		this.tagNameList = tagNameList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
