package com.coach.request;

import javax.validation.constraints.NotNull;


public class GetMemberRequest extends CoachBaseRequest{
	@NotNull
    private Long courseId;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}




	
}
