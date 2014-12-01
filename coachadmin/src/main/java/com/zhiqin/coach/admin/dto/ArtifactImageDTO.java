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
public class ArtifactImageDTO {

	private Long id;
	private String fileName;
	private String tagNameList;
	private String url;
	private Integer type; 
	private Long objectId;
	private Long imageId;
	private Integer tagImage;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public Integer getTagImage() {
		return tagImage;
	}
	public void setTagImage(Integer tagImage) {
		this.tagImage = tagImage;
	}
	
}
