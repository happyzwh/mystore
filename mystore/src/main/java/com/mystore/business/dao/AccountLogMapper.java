package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.AccountLog;

public interface AccountLogMapper {
	
	/**
	 * 增加帐户流水
	 * 
	 * */
	public int addAccountLog(AccountLog accountLog);
	
	/**
	 * 根据用户查询帐户流水数量
	 * 
	 * */
	public Integer getAccountLogCountByUserId(AccountLog accountLog);
	
	/**
	 * 根据用户查询帐户流水列表
	 * 
	 * */
	public List<AccountLog> getAccountLogListByUserId(AccountLog accountLog);

}
