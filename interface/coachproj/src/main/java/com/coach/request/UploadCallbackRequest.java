package com.coach.request;

import javax.validation.constraints.NotNull;

import com.rop.AbstractRopRequest;



public class UploadCallbackRequest extends AbstractRopRequest{
	@NotNull
    private String name;
	@NotNull
    private Integer type;
	@NotNull
    private Long objectId;  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	@Override
	public String toString() {
		return "UploadCallbackRequest [name="
				+ name + ", type=" + type + ", objectId=" + objectId + "]";
	}
    
}

