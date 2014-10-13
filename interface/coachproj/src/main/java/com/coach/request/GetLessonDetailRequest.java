package com.coach.request;

import javax.validation.constraints.NotNull;




public class GetLessonDetailRequest extends CoachBaseRequest{
	@NotNull
    private Long lessonId;

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

    
}

