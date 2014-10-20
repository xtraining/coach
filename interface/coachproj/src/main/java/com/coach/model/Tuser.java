package com.coach.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Coach entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Tuser extends AbstractBaseModel implements java.io.Serializable {

	// Fields

	private Long id;
	private String phoneNumber;
	private String password;
	
	private String name;
	private Integer gender;
	private String idNumber;
	private Integer idType;
	private Date birthday;
	private String email;
	private Timestamp createTime;
	private Timestamp certificateTime;
	private Timestamp lastAccessTime;
	private Integer status;
	private Timestamp lockTime;

	/** default constructor */
	public Tuser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getCertificateTime() {
		return certificateTime;
	}

	public void setCertificateTime(Timestamp certificateTime) {
		this.certificateTime = certificateTime;
	}

	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getLockTime() {
		return lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

}