package com.coach.request;

import javax.validation.constraints.NotNull;




public class CheckLessonMemberRequest extends CoachBaseRequest{
	@NotNull
    private Long lessonId;
	private String attendMemberId;
	private String absentMemberId;

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public String getAttendMemberId() {
		return attendMemberId;
	}

	public void setAttendMemberId(String attendMemberId) {
		this.attendMemberId = attendMemberId;
	}

	public String getAbsentMemberId() {
		return absentMemberId;
	}

	public void setAbsentMemberId(String absentMemberId) {
		this.absentMemberId = absentMemberId;
	}

    
}

