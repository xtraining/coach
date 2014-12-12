/**
 * @author Zhang Zhipeng
 *
 * 2013-10-24
 */
package com.zhiqin.coach.admin.dto;

import com.zhiqin.coach.admin.common.Constants;

/**
 * @author Lenovo
 *
 */
public class PageInfoDTO {

	private Integer numPerPage = Constants.ADMIN_DEFAULT_PAGE_SIZE;
	private Integer pageNum = 1;
	public Integer getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
}
