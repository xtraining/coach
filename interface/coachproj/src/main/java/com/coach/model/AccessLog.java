package com.coach.model;

import java.sql.Timestamp;

/**
 * AccessLog entity. @author MyEclipse Persistence Tools
 */

public class AccessLog extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private String ip;
	private String method;
	private String appkey;
	private Integer type;
	private Timestamp createTime;
	private String message;
	private Long memberId;

	// Constructors

	/** default constructor */
	public AccessLog() {
	}

	/** minimal constructor */
	public AccessLog(String ip, String method, String appkey, Integer type,
			Timestamp createTime, String message) {
		this.ip = ip;
		this.method = method;
		this.appkey = appkey;
		this.type = type;
		this.createTime = createTime;
		this.message = message;
	}

	/** full constructor */
	public AccessLog(String ip, String method, String appkey, Integer type,
			Timestamp createTime, String message, Long memberId) {
		this.ip = ip;
		this.method = method;
		this.appkey = appkey;
		this.type = type;
		this.createTime = createTime;
		this.message = message;
		this.memberId = memberId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAppkey() {
		return this.appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}