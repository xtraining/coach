package com.coach.response;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "teamCheckResponse")
public class TeamCheckResponse extends TeamResponse implements Serializable {
	@XmlElement
	private List<CheckResponse> checkResponse;

	public List<CheckResponse> getCheckResponse() {
		return checkResponse;
	}

	public void setCheckResponse(List<CheckResponse> checkResponse) {
		this.checkResponse = checkResponse;
	}


	
}
