package com.coach.request.v2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "checks")
public class CheckSyncDataList{
	@XmlElementWrapper(name = "checkDataList") 
	@XmlElement(name = "checkData")
	private List<CheckSyncData> checkDataList;

	public List<CheckSyncData> getCheckDataList() {
		return checkDataList;
	}

	public void setCheckDataList(List<CheckSyncData> checkDataList) {
		this.checkDataList = checkDataList;
	}

}

