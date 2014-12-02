package com.coach.model;

/**
 * MemberExpand entity. @author MyEclipse Persistence Tools
 */

public class MemberExpand extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long memberId;
	private String uuid;
	private Integer osType;
	private String baiduUserId;
	private String baiduChannelId;
	private Integer appVersionId;
	private Integer status;
	private String osVersion;

	// Constructors

	/** default constructor */
	public MemberExpand() {
	}

	/** minimal constructor */
	public MemberExpand(Long memberId, Integer osType, Integer appVersionId,
			Integer status, String osVersion) {
		this.memberId = memberId;
		this.osType = osType;
		this.appVersionId = appVersionId;
		this.status = status;
		this.osVersion = osVersion;
	}

	/** full constructor */
	public MemberExpand(Long memberId, String uuid, Integer osType,
			String baiduUserId, String baiduChannelId, Integer appVersionId,
			Integer status, String osVersion) {
		this.memberId = memberId;
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

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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