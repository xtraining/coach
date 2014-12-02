package com.coach.request;

import javax.validation.constraints.NotNull;


public class GetMemberDetailRequest extends CoachBaseRequest{
	@NotNull
    private Long courseId;
	@NotNull
    private Long memberId;

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

	
}
