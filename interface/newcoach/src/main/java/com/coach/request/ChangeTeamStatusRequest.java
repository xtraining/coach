package com.coach.request;

import javax.validation.constraints.NotNull;




public class ChangeTeamStatusRequest extends TeamIdRequest{
	@NotNull
	private Integer status;   //-1:删除  0：正常  1：结课
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    
}

