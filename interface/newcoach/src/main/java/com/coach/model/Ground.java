package com.coach.model;

/**
 * Ground entity. @author MyEclipse Persistence Tools
 */

public class Ground extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String areaCode;
	private Float longtitude;
	private Float latitude;
	private String description;

	// Constructors

	/** default constructor */
	public Ground() {
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


	public String getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public Float getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Float longtitude) {
		this.longtitude = longtitude;
	}

	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}