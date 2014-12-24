package com.coach.request.v1;

import javax.validation.constraints.NotNull;




public class GetTeamCheckHistoryRequest extends TeamIdRequest{
	@NotNull
    private Integer pageNumber;
	@NotNull
	private Integer pageSize;
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}

