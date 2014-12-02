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

public class AddCourseRequest extends CoachBaseRequest{
	@NotNull
    private String name;
	@NotNull
    private String groundName;
	private Integer groundId;
	@NotNull
	private Integer type;
	@NotNull
    private String address;
	@NotNull
	private String startTime;
	private String recycleDay; 
	@NotNull
	private Float courseHour;
	@NotNull
	private Float lessonHour;
	@NotNull
	private String phoneNumberList;
	@NotNull
	private String memberNameList;
	private Double longitude;
	private Double latitude;
	private String remarks;
	private Integer alterSwitch;
	
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

	public Integer getAlterSwitch() {
		return this.alterSwitch;
	}

	public void setAlterSwitch(Integer alterSwitch) {
		this.alterSwitch = alterSwitch;
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
	public Integer getGroundId() {
		return groundId;
	}
	public void setGroundId(Integer groundId) {
		this.groundId = groundId;
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
	public String getPhoneNumberList() {
		return phoneNumberList;
	}
	public void setPhoneNumberList(String phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}
	public String getMemberNameList() {
		return memberNameList;
	}
	public void setMemberNameList(String memberNameList) {
		this.memberNameList = memberNameList;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	/*public Course toCourse() {
		Course c = new Course();
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
	        int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        float assignedHour = 0f;
			while((courseHour - assignedHour) >= 0.01 ){
				if(recycleDay.indexOf(today+"") >= 0){
					if(assignedHour < 0.01){
						float hours = courseHour > lessonHour ? lessonHour : courseHour;
						buildLesson(c, hours, c.getStartTime());
						assignedHour += hours;
					} else {
						float hours = (courseHour-assignedHour) > lessonHour ? lessonHour : (courseHour-assignedHour);
						buildLesson(c, hours, new Timestamp(cal.getTimeInMillis()));
						assignedHour += hours;
					}
				}
				cal.add(Calendar.DAY_OF_MONTH, 1); //加一天
				today = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if(today == 0)today = 7; //convert Sunday to 7
			}
			Lesson lastLesson = c.getLessonList().get(c.getLessonList().size() - 1);
			c.setEndTime(lastLesson.getEndTime());
		}
		return c;
	}*/
	
	public Course toCourse() {
		Course c = new Course();
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
		c.setLatitude(latitude);
		c.setLongitude(longitude);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(c.getStartTime().getTime());
		//添加第一天的课程
		float assignedHour = 0f;
		float hours = courseHour > lessonHour ? lessonHour : courseHour;
		buildLesson(c, hours, c.getStartTime());
		assignedHour += hours;
		
		if(StringUtils.isNotBlank(recycleDay)){
			c.setRecycleDay(recycleDay);
			cal.add(Calendar.DAY_OF_MONTH, 1); //加一天
			int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
			today = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(today == 0)today = 7; //convert Sunday to 7
			while((courseHour - assignedHour) >= 0.01 ){
				if(recycleDay.indexOf(today+"") >= 0){
					hours = (courseHour-assignedHour) > lessonHour ? lessonHour : (courseHour-assignedHour);
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
		lesson.setLatitude(latitude);
		lesson.setLongitude(longitude);
		lesson.setStartTime(startTime);
		lesson.setType(LESSON_TYPE.JOB.getValue());
		lesson.setAlertSwitch(ALERT_SWITCH.OFF.getValue());
		lesson.setGroundId(c.getGroundId());
		lesson.setGroundName(c.getGroundName());
		lesson.setCoachId(getCoachId());
		c.getLessonList().add(lesson);
	}
	
	public static void main(String[] args) {
		AddCourseRequest a = new AddCourseRequest();
		a.setLessonHour(1.5f);
		a.setCourseHour(200f);
		a.setRecycleDay("6");
		a.setStartTime("2014-10-30 08:00:00");
		Course c = a.toCourse();
		System.out.println(c.getLessonNum());
		for(Lesson lesson : c.getLessonList()){
			Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(lesson.getStartTime().getTime());
	        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
			System.out.println(DateUtils.dateToyyyyMMddHHmiss(lesson.getStartTime()) +"-"+ DateUtils.dateToyyyyMMddHHmiss(lesson.getEndTime())
					 + " hours: " + lesson.getHours() + " day : " + day);
		}
		System.out.println(c);
	}
	
}
