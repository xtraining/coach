package com.coach.model;

/**
 * ClientAppkey entity. @author MyEclipse Persistence Tools
 */

public class ClientAppkey extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String appKey;
	private String secretKey;
	private Integer status;
	private Integer osType;

	// Constructors

	/** default constructor */
	public ClientAppkey() {
	}

	/** full constructor */
	public ClientAppkey(String appKey, String secretKey, Integer status,
			Integer osType) {
		this.appKey = appKey;
		this.secretKey = secretKey;
		this.status = status;
		this.osType = osType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOsType() {
		return this.osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

}