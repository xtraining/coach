/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 *
 */
public class CourseDTO {

	private Long id;
	private Long courseId;
	private String name;
	private String description;
	private String address;
	private Integer type;
	private Integer groundId;
	private Integer status;
	private Long coachCourseId;
	private Integer coachCourseStatus;
	private String groundName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String recycleDay;
	private Double courseHour;
	private Double lessonHour;
	private Date expiryDate;
	private String remarks;
	private Long orgId;
	private String orgName;
	private Timestamp createTime;
	private String code;
	private String startTimeStr;
	private String endTimeStr;
	private String expiryDateStr;
	private Integer lessonNum;
	private Long coachId;
	private String coachName;
	private List<LessonDTO> lessonList = new ArrayList<LessonDTO>();
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGroundId() {
		return groundId;
	}
	public void setGroundId(Integer groundId) {
		this.groundId = groundId;
	}
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getRecycleDay() {
		return recycleDay;
	}
	public void setRecycleDay(String recycleDay) {
		this.recycleDay = recycleDay;
	}
	public Double getCourseHour() {
		return courseHour;
	}
	public void setCourseHour(Double courseHour) {
		this.courseHour = courseHour;
	}
	public Double getLessonHour() {
		return lessonHour;
	}
	public void setLessonHour(Double lessonHour) {
		this.lessonHour = lessonHour;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public void setExpiryDateStr(String expiryDate) {
		this.expiryDateStr = expiryDate;
	}
	public String getExpiryDateStr() {
		return expiryDateStr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLessonNum() {
		return lessonNum;
	}
	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<LessonDTO> getLessonList() {
		return lessonList;
	}
	public void setLessonList(List<LessonDTO> lessonList) {
		this.lessonList = lessonList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getCoachCourseId() {
		return coachCourseId;
	}
	public void setCoachCourseId(Long coachCourseId) {
		this.coachCourseId = coachCourseId;
	}
	public Integer getCoachCourseStatus() {
		return coachCourseStatus;
	}
	public void setCoachCourseStatus(Integer coachCourseStatus) {
		this.coachCourseStatus = coachCourseStatus;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	
}
