package com.coach.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coach.response.CourseDetailResponse;
import com.coach.response.CourseResponse;
import com.coach.response.OrgCourseResponse;
import com.coach.utils.DateUtils;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer organizationId;
	private String address;
	private String description;
	private Integer type;
	private String groundName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String code;
	private Date expiryDate;
	private String organizationName;
	private Integer cooachCourseStatus;
	private Timestamp cooachCourseTime;
	private String recycleDay;
	private Float  courseHour;
	private Float  lessonHour;
	private Integer lessonNum;
	private String remarks;
	private Timestamp createTime;
	private Integer groundId;
	private Integer status;
	private Long memberNum;
	private Double longitude;
	private Double latitude;
	private Integer unassigned;
	private List<Lesson> lessonList = new ArrayList<Lesson>();
	private Integer flag;
	
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<Lesson> getLessonList() {
		return lessonList;
	}
	public void setLessonList(List<Lesson> lessonList) {
		this.lessonList = lessonList;
	}
	public Long getId() {
		return this.id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public Integer getGroundId() {
		return groundId;
	}
	public void setGroundId(Integer groundId) {
		this.groundId = groundId;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getGroundName() {
		return this.groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
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

	public Float getCourseHour() {
		return courseHour;
	}

	public void setCourseHour(Float courseHour) {
		this.courseHour = courseHour;
	}

	public Float getLessonHour() {
		return lessonHour;
	}

	public void setLessonHour(Float lessonHour) {
		this.lessonHour = lessonHour;
	}

	public Integer getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getCooachCourseStatus() {
		return cooachCourseStatus;
	}

	public void setCooachCourseStatus(Integer cooachCourseStatus) {
		this.cooachCourseStatus = cooachCourseStatus;
	}

	public Timestamp getCooachCourseTime() {
		return cooachCourseTime;
	}

	public void setCooachCourseTime(Timestamp cooachCourseTime) {
		this.cooachCourseTime = cooachCourseTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public OrgCourseResponse getOrgCoureseResponse() {
		OrgCourseResponse r = new OrgCourseResponse();
		r.setOrgId(getOrganizationId());
		if(r.getOrgId() != null && r.getOrgId() > 0){
			r.setType(0);// org course
		} else {
			r.setType(1); // personal course
		}
		r.setOrgName(getOrganizationName());
		return r;
	}

	public CourseResponse toCourseResonse() {
		CourseResponse c = new CourseResponse();
		c.setCourseId(id);
		c.setCourseName(name);
		c.setStatus(cooachCourseStatus);
		c.setCreateTime(DateUtils.dateToyyyyMMddHHmiss(cooachCourseTime));
		c.setEndTime(DateUtils.dateToyyyyMMddHHmiss(endTime));
		c.setUnassigned(unassigned);
		return c;
	}
	public CourseDetailResponse toDetailResponse() {
		CourseDetailResponse c = new CourseDetailResponse();
		c.setCourseId(id);
		c.setType(type);
		c.setLatitude(latitude);
		c.setLongitude(longitude);
		c.setCourseName(name);
		c.setStatus(cooachCourseStatus);
		c.setCreateTime(DateUtils.dateToyyyyMMddHHmiss(cooachCourseTime));
		c.setAddress(address);
		c.setCourseHour(courseHour);
		c.setGroundName(groundName);
		c.setGroundId(groundId);
		c.setLessonHour(lessonHour);
		c.setRecycleDay(recycleDay);
		c.setRemarks(remarks);
		c.setStartTime(DateUtils.dateToyyyyMMddHHmiss(startTime));
		c.setEndTime(DateUtils.dateToyyyyMMddHHmiss(endTime));
		c.setMemberNum(memberNum);
		c.setExpiryDate(DateUtils.dateToyyyyMMdd(expiryDate));
		return c;
	}

}