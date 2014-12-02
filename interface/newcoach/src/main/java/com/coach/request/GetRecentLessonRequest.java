package com.coach.request;

import javax.validation.constraints.NotNull;




public class GetRecentLessonRequest extends CoachBaseRequest{
	@NotNull
    private String startDate;
	@NotNull
	private Integer days;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}

    
}

