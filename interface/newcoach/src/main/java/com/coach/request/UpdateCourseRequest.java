package com.coach.request;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.coach.common.Constants.ALERT_SWITCH;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.common.Constants.COURSE_TYPE;
import com.coach.common.Constants.LESSON_TYPE;
import com.coach.model.Course;
import com.coach.model.Lesson;
import com.coach.utils.DateUtils;
import com.rop.utils.RopUtils;

public class UpdateCourseRequest extends CoachBaseRequest{
	@NotNull
    private Long courseId;
	@NotNull
    private String name;
	@NotNull
    private String groundName;
	@NotNull
    private String address;
	@NotNull
	private String startTime;
	private String recycleDay; 
	@NotNull
	private Float courseHour;
	@NotNull
	private Float lessonHour;
	private String modifyMemberIdList;
	private String modifyPhoneNumberList;
	private String modifyMemberNameList;
	private String newPhoneNumberList;
	private String newMemberNameList;
	private String deletedMemberIdList;
	private Double longitude;
	private Double latitude;
	private String remarks;
	private Integer alterSwitch;
	
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

	public String getGroundName() {
		return groundName;
	}

	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public String getModifyMemberIdList() {
		return modifyMemberIdList;
	}

	public void setModifyMemberIdList(String modifyMemberIdList) {
		this.modifyMemberIdList = modifyMemberIdList;
	}

	public String getModifyPhoneNumberList() {
		return modifyPhoneNumberList;
	}

	public void setModifyPhoneNumberList(String modifyPhoneNumberList) {
		this.modifyPhoneNumberList = modifyPhoneNumberList;
	}

	public String getModifyMemberNameList() {
		return modifyMemberNameList;
	}

	public void setModifyMemberNameList(String modifyMemberNameList) {
		this.modifyMemberNameList = modifyMemberNameList;
	}

	public String getNewPhoneNumberList() {
		return newPhoneNumberList;
	}

	public void setNewPhoneNumberList(String newPhoneNumberList) {
		this.newPhoneNumberList = newPhoneNumberList;
	}

	public String getNewMemberNameList() {
		return newMemberNameList;
	}

	public void setNewMemberNameList(String newMemberNameList) {
		this.newMemberNameList = newMemberNameList;
	}

	public String getDeletedMemberIdList() {
		return deletedMemberIdList;
	}

	public void setDeletedMemberIdList(String deletedMemberIdList) {
		this.deletedMemberIdList = deletedMemberIdList;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getAlterSwitch() {
		return alterSwitch;
	}

	public void setAlterSwitch(Integer alterSwitch) {
		this.alterSwitch = alterSwitch;
	}

	public Course toCourse(float assignedHour) {
		Course c = new Course();
		c.setId(courseId);
		c.setAddress(address);
		c.setCode(StringUtils.replace(RopUtils.getUUID(), "-", ""));
		c.setCourseHour(courseHour);
		c.setRemarks(remarks);
		c.setGroundName(groundName);
		c.setLessonHour(lessonHour);
		c.setCourseHour(courseHour);
		c.setName(name);
		c.setStatus(COURSE_STATUS.ACTIVE.getValue());
		c.setType(COURSE_TYPE.PERSONAL.getValue());
		c.setStartTime(DateUtils.yyyyMMddHHmmssToTimestamp(startTime));
		c.setLessonNum((int)Math.ceil(courseHour/lessonHour));
		if(StringUtils.isNotBlank(recycleDay)){
			c.setRecycleDay(recycleDay);
			Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(c.getStartTime().getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1); //加一天
			int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
			today = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(today == 0)today = 7; //convert Sunday to 7
			while((courseHour - assignedHour) >= 0.01 ){
				if(recycleDay.indexOf(today+"") >= 0){
					float hours = (courseHour-assignedHour) > lessonHour ? lessonHour : (courseHour-assignedHour);
					buildLesson(c, hours, new Timestamp(cal.getTimeInMillis()));
					assignedHour += hours;
				}
				cal.add(Calendar.DAY_OF_MONTH, 1); //加一天
				today = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if(today == 0)today = 7; //convert Sunday to 7
			}
			Lesson lastLesson = c.getLessonList().get(c.getLessonList().size() - 1);
			c.setEndTime(lastLesson.getEndTime());
		}
		return c;
	}
	
	private void buildLesson(Course c, float hours, Timestamp startTime) {
		Lesson lesson = new Lesson();
		lesson.setAddress(address);
		lesson.setEndTime(new Timestamp(startTime.getTime() + (int)(hours * 3600000)));
		lesson.setHours(hours);
		lesson.setName(name);
		lesson.setStartTime(startTime);
		lesson.setType(LESSON_TYPE.JOB.getValue());
		lesson.setAlertSwitch(ALERT_SWITCH.OFF.getValue());
		lesson.setGroundId(c.getGroundId());
		lesson.setGroundName(c.getGroundName());
		lesson.setCoachId(getCoachId());
		c.getLessonList().add(lesson);
	}
}
