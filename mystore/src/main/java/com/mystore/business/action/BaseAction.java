package com.mystore.business.action;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Integer pageNo = 1;
	
	protected Integer pageSize = 20;
	
	protected String pageInfo;

	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	

}
