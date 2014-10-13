package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;

public class GetOrgCourseRequest extends BaseRequest{
	@NotNull
    private Integer coachId;
	@NotNull
    private Integer orgId;
	@NotNull
    private Integer pageNumber;
	@NotNull
    private Integer pageSize;
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

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
