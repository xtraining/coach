package com.coach.request;

import javax.validation.constraints.NotNull;



public class BindThirdPartyRequest extends CoachBaseRequest{
	@NotNull
    private String thirdPartyId;
    @NotNull
    private Integer type;
    @NotNull
    private Integer gender;
    @NotNull
    private String avatarUrl;
    @NotNull
    private String thirdPartyNickname;
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getThirdPartyNickname() {
		return thirdPartyNickname;
	}
	public void setThirdPartyNickname(String thirdPartyNickname) {
		this.thirdPartyNickname = thirdPartyNickname;
	}
    
}

