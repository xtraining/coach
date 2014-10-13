package com.coach.model;

/**
 * AppVersion entity. @author MyEclipse Persistence Tools
 */

public class AppVersion extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer osType;

	// Constructors

	/** default constructor */
	public AppVersion() {
	}

	/** full constructor */
	public AppVersion(String name, Integer osType) {
		this.name = name;
		this.osType = osType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOsType() {
		return this.osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

}