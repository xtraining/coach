package com.coach.model;

import java.sql.Timestamp;

/**
 * Organization entity. @author MyEclipse Persistence Tools
 */

public class Organization extends AbstractBaseModel implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Timestamp createTime;
	private String code;
	private String level;
	private String address;
	private String description;

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(String name, Timestamp createTime, String code,
			String level, String address) {
		this.name = name;
		this.createTime = createTime;
		this.code = code;
		this.level = level;
		this.address = address;
	}

	/** full constructor */
	public Organization(String name, Timestamp createTime, String code,
			String level, String address, String description) {
		this.name = name;
		this.createTime = createTime;
		this.code = code;
		this.level = level;
		this.address = address;
		this.description = description;
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}