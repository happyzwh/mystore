package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Account;

public interface AccountService {
	
	/**
	 * 增加帐户
	 * 
	 * */
	public int addAccount(Account account);
	
	
	/**
	 * 修改帐户
	 * 
	 * */
	public int upateAccount(Account account);
	
	/**
	 * 查询帐户
	 * 
	 * */
	public Account getAccount(Account account);
	

	/**
	 * 查询帐户list
	 * 
	 * */
	public List<Account> getAccountListByUserId(Integer userId);

}
