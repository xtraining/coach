package com.coach.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class ThirdSignUpRequest extends BaseRequest{
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
    @NotNull
    @Pattern(regexp = "^[1-9]\\d{10}$")
    private String phoneNumber;
    
    @NotNull
    @Pattern(regexp = "\\w{6,100}")
    private String password;
    
    @NotNull
    private String vcode;
	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}


	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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

	public String getThirdPartyNickname() {
		return thirdPartyNickname;
	}

	public void setThirdPartyNickname(String thirdPartyUsername) {
		this.thirdPartyNickname = thirdPartyUsername;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}

