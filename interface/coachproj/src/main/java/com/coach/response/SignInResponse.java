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
	private Long coachId;
	@XmlElement
	private Integer orgSwitch;
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
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public Integer getOrgSwitch() {
		return orgSwitch;
	}
	public void setOrgSwitch(Integer orgSwitch) {
		this.orgSwitch = orgSwitch;
	}
	
}
