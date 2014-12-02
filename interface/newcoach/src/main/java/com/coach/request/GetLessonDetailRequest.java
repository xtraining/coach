package com.coach.request;

import javax.validation.constraints.NotNull;




public class GetLessonDetailRequest extends CoachBaseRequest{
	@NotNull
    private Long lessonId;
	private Integer type;

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    
}

