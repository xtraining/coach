package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "areaResponse")
public class AreaResponse implements Serializable {
	@XmlElement
	private String areaCode = "";
	@XmlElement
	private String name = "";
	@XmlElement
	private List<AreaResponse> subareaList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AreaResponse> getSubareaList() {
		return subareaList;
	}
	public void setSubareaList(List<AreaResponse> subareaList) {
		this.subareaList = subareaList;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	
}
