package com.mystore.business.service;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.AccountLog;
import com.mystore.business.pojo.AccountType;
import com.mystore.business.pojo.BizType;

public interface AccountLogService {
	
	/**
	 * 增加帐户流水
	 * 
	 * */
	public int addAccountLog(AccountLog accountLog);
	
	/**
	 * 根据主键查询商品评论
	 * 
	 * */
	public Pager<AccountLog> getAccountLogByUserId(Integer userId,AccountType accountType,BizType bizType,Integer pageNum,Integer pageSize);

}
