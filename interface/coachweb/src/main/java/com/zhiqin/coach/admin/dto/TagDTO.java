/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import java.sql.Timestamp;



/**
 * @author Lenovo
 *
 */
public class TagDTO {

	private Long id;
	private String name;
	private Integer tagOrder;
	private Integer status;
	private Timestamp createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTagOrder() {
		return tagOrder;
	}
	public void setTagOrder(Integer tagOrder) {
		this.tagOrder = tagOrder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
