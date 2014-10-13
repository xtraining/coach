package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.model.Member;


public class UpdateMemberStatusRequest extends CoachBaseRequest{
	@NotNull
    private Long memberId;
	@NotNull
    private Long courseId;
	@NotNull
	private Integer status;
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
