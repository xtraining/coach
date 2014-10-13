package com.coach.model;

/**
 * CoachExpand entity. @author MyEclipse Persistence Tools
 */

public class CoachExpand extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Integer coachId;
	private String uuid;
	private Integer osType;
	private String baiduUserId;
	private String baiduChannelId;
	private Integer appVersionId;
	private Integer status;
	private String osVersion;

	// Constructors

	/** default constructor */
	public CoachExpand() {
	}

	/** minimal constructor */
	public CoachExpand(Integer coachId, Integer osType, Integer appVersionId,
			Integer status, String osVersion) {
		this.coachId = coachId;
		this.osType = osType;
		this.appVersionId = appVersionId;
		this.status = status;
		this.osVersion = osVersion;
	}

	/** full constructor */
	public CoachExpand(Integer coachId, String uuid, Integer osType,
			String baiduUserId, String baiduChannelId, Integer appVersionId,
			Integer status, String osVersion) {
		this.coachId = coachId;
		this.uuid = uuid;
		this.osType = osType;
		this.baiduUserId = baiduUserId;
		this.baiduChannelId = baiduChannelId;
		this.appVersionId = appVersionId;
		this.status = status;
		this.osVersion = osVersion;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCoachId() {
		return this.coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getOsType() {
		return this.osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	public String getBaiduUserId() {
		return this.baiduUserId;
	}

	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}

	public String getBaiduChannelId() {
		return this.baiduChannelId;
	}

	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}

	public Integer getAppVersionId() {
		return this.appVersionId;
	}

	public void setAppVersionId(Integer appVersionId) {
		this.appVersionId = appVersionId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

}