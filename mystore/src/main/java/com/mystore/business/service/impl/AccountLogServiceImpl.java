package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.AccountLogMapper;
import com.mystore.business.dto.AccountLog;
import com.mystore.business.pojo.AccountType;
import com.mystore.business.pojo.BizType;
import com.mystore.business.service.AccountLogService;

@Service("accountLogService")
public class AccountLogServiceImpl implements AccountLogService{
	
	@Autowired
	private AccountLogMapper accountLogMapper;

	@Override
	public int addAccountLog(AccountLog accountLog) {
		// TODO Auto-generated method stub
		return accountLogMapper.addAccountLog(accountLog);
	}

	@Override
	public Pager<AccountLog> getAccountLogByUserId(Integer userId,AccountType accountType,BizType bizType,
			Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		AccountLog accountLog = new AccountLog();
		accountLog.setUserId(userId);
		accountLog.setAccountType(accountType != null?accountType.getValue():null);
		accountLog.setBizType(bizType != null?bizType.getValue():null);
		if(pageNum == null || (pageNum != null && pageNum == 0))pageNum =1;
		if(pageSize == null || (pageSize != null && pageSize == 0))pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			accountLog.setPageIndex(pageIndex);
			accountLog.setPageSize(pageSize);
		}
		Pager<AccountLog> pager = new Pager<AccountLog>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = accountLogMapper.getAccountLogCountByUserId(accountLog);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<AccountLog> list = accountLogMapper.getAccountLogListByUserId(accountLog);
		pager.setResultList(list);
		return pager;
	}

}
