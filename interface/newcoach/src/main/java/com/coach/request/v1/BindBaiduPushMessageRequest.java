package com.coach.request.v1;

import javax.validation.constraints.NotNull;



public class BindBaiduPushMessageRequest extends CoachBaseRequest{
	@NotNull
    private String appVersion;
    @NotNull
    private String baiduUserId;
    @NotNull
    private String baiduChannelId;
    @NotNull
    private String osVersion;
	public String getBaiduUserId() {
		return baiduUserId;
	}

	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}

	public String getBaiduChannelId() {
		return baiduChannelId;
	}

	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}

