package com.coach.model;

import java.sql.Timestamp;

/**
 * SmsHistory entity. @author MyEclipse Persistence Tools
 */

public class SmsHistory extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Long receiverId;
	private Integer receiverType;
	private String phoneNumber;
	private Integer type;
	private String content;
	private Timestamp createTime;
	private Integer status;
	private String vcode;

	// Constructors

	/** default constructor */
	public SmsHistory() {
	}

	/** minimal constructor */
	public SmsHistory(Integer receiverType, String phoneNumber, String content,
			Timestamp createTime, String vcode) {
		this.receiverType = receiverType;
		this.phoneNumber = phoneNumber;
		this.content = content;
		this.createTime = createTime;
		this.vcode = vcode;
	}

	/** full constructor */
	public SmsHistory(Long receiverId, Integer receiverType,
			String phoneNumber, Integer type, String content,
			Timestamp createTime, Integer status, String vcode) {
		this.receiverId = receiverId;
		this.receiverType = receiverType;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.content = content;
		this.createTime = createTime;
		this.status = status;
		this.vcode = vcode;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getReceiverType() {
		return this.receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVcode() {
		return this.vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}