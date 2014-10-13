package com.coach.request;




public class GetOneWeekLessonRequest extends CoachBaseRequest{
    private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
}

