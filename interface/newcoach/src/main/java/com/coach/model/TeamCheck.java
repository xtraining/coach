package com.coach.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import com.coach.response.CheckResponse;
import com.coach.response.MemberResponse;
import com.coach.response.TeamCheckResponse;
import com.coach.response.TeamResponse;
import com.coach.utils.DateUtils;

/**
 * SmsHistory entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TeamCheck extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long id;
	private Long teamId;
	private String teamName;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Double longitude;
	private Double latitude;
	private Integer status;
	private String province;
	private String city;
	private String district;
	private String street;
	private String streetNumber;
	private Integer attendNum;
	private Integer absentNum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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
	
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public CheckResponse toResponse() {
		CheckResponse r1 = new CheckResponse();
		r1.setTeamCheckId(id);
		String address = null;
		if(StringUtils.isBlank(district)){
			address = StringUtils.trimToEmpty(city) + StringUtils.trimToEmpty(street);
		} else {
			address = StringUtils.trimToEmpty(district) + StringUtils.trimToEmpty(street);
		}
		r1.setAddress(address);
		r1.setCreateTime(DateUtils.dateToyyyyMMddHHmiss(updateTime == null ? createTime : updateTime));
		r1.setAbsentNum(absentNum);
		r1.setAttendNum(attendNum);
		return r1;
	}
	public TeamCheckResponse toTeamCheckResponse() {
		TeamCheckResponse t = new TeamCheckResponse();
		t.setTeamId(teamId);
		t.setName(teamName);
		String address = null;
		if(StringUtils.isBlank(district)){
			address = StringUtils.trimToEmpty(city) + StringUtils.trimToEmpty(street);
		} else {
			address = StringUtils.trimToEmpty(district) + StringUtils.trimToEmpty(street);
		}
		t.setAddress(address);
		t.setCreateTime(DateUtils.dateToyyyyMMddHHmiss(updateTime == null ? createTime : updateTime));
		t.setStatus(status);
		return t;
	}

}