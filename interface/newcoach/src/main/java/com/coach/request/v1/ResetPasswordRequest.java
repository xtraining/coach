package com.coach.request.v1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class ResetPasswordRequest extends BaseRequest{
	@NotNull
    @Pattern(regexp = "^[1-9]\\d{10}$")
    private String phoneNumber;
    
    @NotNull
    @Pattern(regexp = "\\w{6,100}")
    private String password;
    
    @NotNull
    @Pattern(regexp = "\\w{6,100}")
    private String confirmPassword;

    @NotNull
    private String vcode;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    
}

