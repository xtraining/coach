package com.coach.response.v1;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "checkResponse")
public class CheckResponse implements Serializable {
	@XmlElement
	private Long teamCheckId;
	@XmlElement
	private String address;
	@XmlElement
	private String createTime;
	@XmlElement
	private Integer attendNum;
	@XmlElement
	private Integer absentNum;
	@XmlElement
	private Integer status;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}

	public Integer getAbsentNum() {
		return absentNum;
	}

	public void setAbsentNum(Integer absentNum) {
		this.absentNum = absentNum;
	}

	public Long getTeamCheckId() {
		return teamCheckId;
	}

	public void setTeamCheckId(Long teamCheckId) {
		this.teamCheckId = teamCheckId;
	}
	

	
}
