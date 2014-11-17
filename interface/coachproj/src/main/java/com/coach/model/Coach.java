package com.coach.model;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.coach.response.ProfileDetailResponse;
import com.coach.response.ProfileResponse;
import com.coach.utils.Config;
import com.coach.utils.DateUtils;

/**
 * Coach entity. @author MyEclipse Persistence Tools
 */

public class Coach extends Tuser implements java.io.Serializable {

	// Fields

	private Long id;
	private String avatarUrl;
	private Integer graphicLock;
	private Timestamp createTime;
	private Timestamp lastAccessTime;
	private Timestamp lockTime;
	private Integer type;
	private Integer orgSwitch;
	private Integer areaId;
	private String areaName;
	private String areaCode;
	private String description;
	private Long tuserId;
	private Integer registerType;
	private String qqId;
	private String weixinId;
	private String weiboId;
	private String qqUsername;
	private String weixinUsername;
	private String weiboUsername;
	/** default constructor */
	public Coach() {
	}

	// Property accessors

	public Long getTuserId() {
		return tuserId;
	}

	public void setTuserId(Long tuserId) {
		this.tuserId = tuserId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Integer getGraphicLock() {
		return this.graphicLock;
	}

	public void setGraphicLock(Integer graphicLock) {
		this.graphicLock = graphicLock;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastAccessTime() {
		return this.lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Timestamp getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

	public Integer getOrgSwitch() {
		return orgSwitch;
	}

	public void setOrgSwitch(Integer orgSwitch) {
		this.orgSwitch = orgSwitch;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}
	
	public String getQqUsername() {
		return qqUsername;
	}

	public void setQqUsername(String qqUsername) {
		this.qqUsername = qqUsername;
	}

	public String getWeixinUsername() {
		return weixinUsername;
	}

	public void setWeixinUsername(String weixinUsername) {
		this.weixinUsername = weixinUsername;
	}

	public String getWeiboUsername() {
		return weiboUsername;
	}

	public void setWeiboUsername(String weiboUsername) {
		this.weiboUsername = weiboUsername;
	}
	
	public ProfileResponse toProfileResponse() {
		ProfileResponse r = new ProfileResponse();
		r.setCoachId(getId());
		if(StringUtils.isNotBlank(avatarUrl)){
			if(avatarUrl.trim().startsWith("http")){
				r.setAvatarUrl(avatarUrl);
			} else {
				r.setAvatarUrl(Config.getProperty("QINIU_DOMAIN") + avatarUrl);
			}
		} else {
			r.setAvatarUrl("");
		}
		r.setName(getName());
		r.setGender(getGender());
		r.setQqId(getQqId());
		r.setWeiboId(getWeiboId());
		r.setWeixinId(getWeixinId());
		r.setDescription(description);
		return r;
	}

	public ProfileDetailResponse toProfileDetailResponse() {
		ProfileDetailResponse r = new ProfileDetailResponse();
		r.setCoachId(id);
		r.setAreaCode(areaCode);
		r.setAreaName(areaName);
		if(StringUtils.isNotBlank(avatarUrl)){
			if(avatarUrl.trim().startsWith("http")){
				r.setAvatarUrl(avatarUrl);
			} else {
				r.setAvatarUrl(Config.getProperty("QINIU_DOMAIN") + avatarUrl);
			}
		} else {
			r.setAvatarUrl("");
		}
		r.setBirthday(DateUtils.dateToyyyyMMdd(getBirthday()));
		r.setEmail(getEmail());
		r.setGender(getGender());
		r.setIdNumber(getIdNumber());
		r.setIdType(getIdType());
		r.setName(getName());
		r.setPhoneNumber(getPhoneNumber());
		r.setDescription(description);
		return r;
	}


}