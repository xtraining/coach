package com.zhiqin.coach.admin.entity;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class SysRole implements java.io.Serializable {

	private Integer id;
	private Integer status;
	private String name;
	private String roleKey;
	private String description;
	private Set<SysResource> resources = new HashSet<SysResource>(0);

	public SysRole() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SysResource> getResources() {
		return resources;
	}

	public void setResources(Set<SysResource> resources) {
		this.resources = resources;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

}