package com.coach.model;

/**
 * AccessPrivilege entity. @author MyEclipse Persistence Tools
 */

public class AccessPrivilege extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String appKey;
	private String methodName;

	// Constructors

	/** default constructor */
	public AccessPrivilege() {
	}

	/** full constructor */
	public AccessPrivilege(String appKey, String methodName) {
		this.appKey = appKey;
		this.methodName = methodName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}