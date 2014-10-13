package com.coach.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.coach.common.Constants.ID_TYPE;
import com.coach.model.Coach;
import com.coach.utils.DateUtils;

public class UpdateProfileDetailRequest extends BaseRequest{
	@NotNull
	private Integer coachId;
	private String name;
	private Integer gender;
	private String idNumber;
	private Integer idType = ID_TYPE.ID_CARD.getValue();
	@Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
	private String email;
	private String areaCode;
	private String birthday;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Coach toCoach() {
		Coach c = new Coach();
		c.setAreaCode(areaCode);
		c.setId(coachId);
		c.setBirthday(DateUtils.yyyyMMddToDate(birthday));
		c.setEmail(email);
		c.setIdType(idType);
		c.setIdNumber(idNumber);
		c.setName(name);
		c.setGender(gender);
		return c;
	}
	

	
}
