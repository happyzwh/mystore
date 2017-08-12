package com.mystore.business.dao;

import com.mystore.business.dto.AccountStatistics;

public interface AccountStatisticsMapper {
	
	/**
	 * 增加帐户统计数据
	 * 
	 * */
	public int addAccountStatistics(AccountStatistics accountStatistics);
	
	/**
	 * 查询帐户统计数据
	 * 
	 * */
	public AccountStatistics getAccountStatisticsByAccountId(Integer accountId);
	
	/**
	 * 更新帐户统计数据
	 * 
	 * */
	public int updateAccountStatisticsByUserId(AccountStatistics accountStatistics);

}
