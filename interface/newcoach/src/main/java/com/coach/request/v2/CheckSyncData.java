package com.coach.request.v2;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.coach.model.TeamCheck;
import com.coach.request.v1.BaseSyncData;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "checkSyncData")
public class CheckSyncData extends BaseSyncData{
	@XmlAttribute
	private Integer appCheckId;

	public Integer getAppCheckId() {
		return appCheckId;
	}

	public void setAppCheckId(Integer appCheckId) {
		this.appCheckId = appCheckId;
	}
	//0:新增  1：修改
	private Long teamCheckId;
	private String attendMemberId;
	private String attendAppMemberId;
	private Double latitude;
	private Double longitude;
	private Long teamId;
	private Long appTeamId;
	public Long getTeamCheckId() {
		return teamCheckId;
	}
	public void setTeamCheckId(Long teamCheckId) {
		this.teamCheckId = teamCheckId;
	}
	public String getAttendMemberId() {
		return attendMemberId;
	}
	public void setAttendMemberId(String attendMemberId) {
		this.attendMemberId = attendMemberId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAttendAppMemberId() {
		return attendAppMemberId;
	}

	public void setAttendAppMemberId(String attendAppMemberId) {
		this.attendAppMemberId = attendAppMemberId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getAppTeamId() {
		return appTeamId;
	}

	public void setAppTeamId(Long appTeamId) {
		this.appTeamId = appTeamId;
	}

	public TeamCheck toTeamCheck() {
		TeamCheck check = new TeamCheck();
		check.setLatitude(getLatitude());
		check.setLongitude(getLongitude());
		check.setStatus(0);
		check.setTeamId(teamId);
		check.setId(teamCheckId);
		return check;
	}
	
}

