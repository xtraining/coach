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
public class ArtifactDTO {

	private Long id;
	private String title;
	private String subtitle;
	private Integer type;
	private Integer hitNum;
	private Integer artifactOrder;
	private String fileName;
	private Integer duration;
	private Integer status;
	private Integer materialType;//书、故事
	private String  description;
	private Long downloadTaskId;
	private String fileUrl;
	private String imageUrl;
	private String imageName;
	private Long imageId;
	private Long categoryId;
	private Integer categoryOrder;
	private String categoryName;
	private String tags;
	private Integer artifactCategoryOrder;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getHitNum() {
		return hitNum;
	}
	public void setHitNum(Integer hitNum) {
		this.hitNum = hitNum;
	}
	public Integer getArtifactOrder() {
		return artifactOrder;
	}
	public void setArtifactOrder(Integer artifactOrder) {
		this.artifactOrder = artifactOrder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public Integer getMaterialType() {
		return materialType;
	}
	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getDownloadTaskId() {
		return downloadTaskId;
	}
	public void setDownloadTaskId(Long downloadTaskId) {
		this.downloadTaskId = downloadTaskId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getArtifactCategoryOrder() {
		return artifactCategoryOrder;
	}
	public void setArtifactCategoryOrder(Integer artifactCategoryOrder) {
		this.artifactCategoryOrder = artifactCategoryOrder;
	}
	
}
