package com.coach.request;

import javax.validation.constraints.NotNull;

public class UpdateCourseRequest extends AddCourseRequest{
	@NotNull
    private Long courseId;
	private String memberIdList;
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getMemberIdList() {
		return memberIdList;
	}
	public void setMemberIdList(String memberIdList) {
		this.memberIdList = memberIdList;
	}
	
}
