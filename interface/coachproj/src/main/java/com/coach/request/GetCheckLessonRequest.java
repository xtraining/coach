package com.coach.request;

import javax.validation.constraints.NotNull;

public class GetCheckLessonRequest extends CoachBaseRequest{
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
