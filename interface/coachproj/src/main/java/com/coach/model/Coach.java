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

public class Coach extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phoneNumber;
	private String password;
	private Integer registerType;
	private String qqId;
	private String weixinId;
	private String weiboId;
	private String avatarUrl;
	private Integer graphicLock;
	private String name;
	private Integer gender;
	private String idNumber;
	private Date birthday;
	private String email;
	private Timestamp createTime;
	private Timestamp certificateTime;
	private Timestamp lastAccessTime;
	private Integer status;
	private Timestamp lockTime;
	private Integer type;
	private String code;
	private String qqUsername;
	private String weixinUsername;
	private String weiboUsername;
	private Integer idType;
	private Integer areaId;
	private String areaName;
	private String areaCode;
	private String description;

	/** default constructor */
	public Coach() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRegisterType() {
		return this.registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}

	public String getQqId() {
		return this.qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getWeiboId() {
		return this.weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getCertificateTime() {
		return this.certificateTime;
	}

	public void setCertificateTime(Timestamp certificateTime) {
		this.certificateTime = certificateTime;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
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
		r.setBirthday(DateUtils.dateToyyyyMMdd(birthday));
		r.setEmail(getEmail());
		r.setGender(gender);
		r.setIdNumber(idNumber);
		r.setIdType(idType);
		r.setName(getName());
		r.setPhoneNumber(getPhoneNumber());
		r.setDescription(description);
		return r;
	}


}