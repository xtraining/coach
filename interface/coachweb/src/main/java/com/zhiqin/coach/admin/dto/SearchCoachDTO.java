/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

/**
 * @author Lenovo
 *
 */
public class SearchCoachDTO {

	private Long coachId;
	private String coachName;
	public String phoneNumber;
	public Integer type;
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
