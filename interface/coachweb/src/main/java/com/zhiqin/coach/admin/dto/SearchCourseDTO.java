/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

/**
 * @author Lenovo
 *
 */
public class SearchCourseDTO {

	private Long courseId;
	private String courseName;
	private Long orgId;
	private Long coachId;
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	
}
