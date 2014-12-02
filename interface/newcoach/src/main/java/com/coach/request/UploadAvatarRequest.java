package com.coach.request;

import javax.validation.constraints.NotNull;



public class UploadAvatarRequest extends CoachBaseRequest{
	@NotNull
    private String imageExtFileName;

	public String getImageExtFileName() {
		return imageExtFileName;
	}

	public void setImageExtFileName(String imageExtFileName) {
		this.imageExtFileName = imageExtFileName;
	}

}

