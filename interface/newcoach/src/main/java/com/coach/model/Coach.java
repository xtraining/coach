package com.coach.model;

import java.sql.Timestamp;

/**
 * Coach entity. @author MyEclipse Persistence Tools
 */

public class Coach extends Tuser implements java.io.Serializable {

	// Fields

	private Long id;
	private String avatarUrl;
	private Timestamp createTime;
	private Timestamp lastAccessTime;
	private Timestamp lockTime;
	private Integer type;
	private String description;
	private Long tuserId;
	private Integer registerType;
	private String qqId;
	private String weixinId;
	private String weiboId;
	private String qqUsername;
	private String weixinUsername;
	private String weiboUsername;
	private Integer smsSwitch;

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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getSmsSwitch() {
		return smsSwitch;
	}

	public void setSmsSwitch(Integer smsSwitch) {
		this.smsSwitch = smsSwitch;
	}
	


}