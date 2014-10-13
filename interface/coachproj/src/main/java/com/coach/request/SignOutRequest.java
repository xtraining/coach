package com.coach.request;

import javax.validation.constraints.NotNull;


public class SignOutRequest extends CoachBaseRequest {
    @NotNull
	private String baiduUserId;
	@NotNull
	private String baiduChannelId;

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

}
