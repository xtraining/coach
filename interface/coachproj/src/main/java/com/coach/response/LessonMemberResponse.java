package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lessonMemberResponse")
public class LessonMemberResponse extends LessonResponse implements Serializable {
	@XmlElement
	private Long lessonId;
	@XmlElement
	private Integer totalNum;
	@XmlElement
	private Integer attendNum;
	@XmlElement
	private Integer absentNum;
	@XmlElement
	private String groundName;
	@XmlElement
	private String startTime;
	@XmlElement
	private String lessonName;
	@XmlElement
	private List <MemberResponse> memberList;
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}
	public Integer getAbsentNum() {
		return absentNum;
	}
	public void setAbsentNum(Integer absentNum) {
		this.absentNum = absentNum;
	}
	public List<MemberResponse> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberResponse> memberList) {
		this.memberList = memberList;
	}
	public Long getLessonId() {
		return lessonId;
	}
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	public String getGroundName() {
		return groundName;
	}
	public void setGroundName(String groundName) {
		this.groundName = groundName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	
}
