package com.coach.response;

import javax.xml.bind.annotation.XmlElement;

public class SignInResponse {
	@XmlElement
	private Integer flag = 0;
	@XmlElement
	private String msg;
	@XmlElement
	private String sessionId;
	@XmlElement
	private Integer coachId;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	
}
