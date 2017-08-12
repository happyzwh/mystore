package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.AccountMapper;
import com.mystore.business.dto.Account;
import com.mystore.business.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.addAccount(account);
	}

	@Override
	public int upateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.upateAccount(account);
	}

	@Override
	public Account getAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.getAccount(account);
	}

	@Override
	public List<Account> getAccountListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return accountMapper.getAccountListByUserId(userId);
	}

}
