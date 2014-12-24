package com.coach.response.v2;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.coach.response.v1.SimpleResponse;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "syncSimpleResponse")
public class SyncSimpleResponse extends SimpleResponse{
	@XmlElement
	private Integer appObjectId;

	public Integer getAppObjectId() {
		return appObjectId;
	}

	public void setAppObjectId(Integer appObjectId) {
		this.appObjectId = appObjectId;
	}
	
}
