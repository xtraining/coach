package com.coach.response;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "uploadImageResponse")
public class UploadImageResponse {
	@XmlElement
	private String uptoken;
	@XmlElement
	private List<String> keys;
	public String getUptoken() {
		return uptoken;
	}
	public void setUptoken(String uptoken) {
		this.uptoken = uptoken;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	
}
