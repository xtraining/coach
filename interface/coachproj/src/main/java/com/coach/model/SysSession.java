package com.coach.model;

import java.sql.Timestamp;

import com.rop.session.Session;
import com.rop.session.SimpleSession;

/**
 * SysSession entity. @author MyEclipse Persistence Tools
 */

public class SysSession extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Timestamp createTime;
	private Integer status;
	private String sessionId;
	private Long receiverId;
	private Integer receiverType;

	// Constructors

	/** default constructor */
	public SysSession() {
	}

	/** minimal constructor */
	public SysSession(Timestamp createTime, Integer status, String sessionId,
			Integer receiverType) {
		this.createTime = createTime;
		this.status = status;
		this.sessionId = sessionId;
		this.receiverType = receiverType;
	}


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public Long getReceiverId() {
		return receiverId;
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

	public Session toSession() {
		SimpleSession session = new SimpleSession();
		session.setAttribute("id", getId());
		session.setAttribute("receiverId", getReceiverId().intValue());
		session.setAttribute("receiverType", getReceiverType().intValue());
		session.setAttribute("sessionid", getSessionId());
		return session;
	}

}