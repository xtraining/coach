package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "chiefCourseResponse")
public class ChiefCourseResponse implements Serializable {
	@XmlElement
	private Integer hasNew = 0;
	@XmlElement
	private List<OrgCourseResponse> orgCoureResponseList;
	public Integer getHasNew() {
		return hasNew;
	}
	public void setHasNew(Integer hasNew) {
		this.hasNew = hasNew;
	}
	public List<OrgCourseResponse> getOrgCoureResponseList() {
		return orgCoureResponseList;
	}
	public void setOrgCoureResponseList(List<OrgCourseResponse> orgCoureResponseList) {
		this.orgCoureResponseList = orgCoureResponseList;
	}
	
}
