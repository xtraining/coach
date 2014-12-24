package com.coach.request.v1;

import javax.xml.bind.annotation.XmlAttribute;


public class BaseSyncData{
	@XmlAttribute
	private Integer operationType;
	@XmlAttribute
	private String operationTime;
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
}

