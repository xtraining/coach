package com.coach.request;

import javax.validation.constraints.NotNull;

import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;

public class UpdateLifeRequest extends UpdateLessonRequest{
	@NotNull
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

