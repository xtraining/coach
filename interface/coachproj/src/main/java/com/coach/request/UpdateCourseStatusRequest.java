package com.coach.request;

import javax.validation.constraints.NotNull;

public class UpdateCourseStatusRequest extends CoachBaseRequest{
	@NotNull
    private Long courseId;
	@NotNull
    private Integer status;
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
