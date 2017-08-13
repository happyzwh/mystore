package com.mystore.business.action;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Account;
import com.mystore.business.dto.AccountLog;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserStatistics;
import com.mystore.business.pojo.AccountType;
import com.mystore.business.pojo.BizType;
import com.mystore.business.service.AccountLogService;
import com.mystore.business.service.AccountService;
import com.mystore.business.service.UserStatisticsService;

@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserStatisticsService userStatisticsService;
	
	@Autowired
	private AccountLogService accountLogService;
	
	private List<AccountLog> list;
	
	private Account account;
	
	private UserStatistics userStatistics;
	
	private String accountType;
	
	private String bizType;
	
	public String index(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
		Account accountModel = new Account();
		accountModel.setUserId(user.getId());
		accountModel.setType(AccountType.GENERAL.getValue());
		account = accountService.getAccount(accountModel);
		
		userStatistics = userStatisticsService.getUserStatisticsByUserId(user.getId());
		
		return "index";
		
	}

	public String list(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
		Pager<AccountLog> pager = accountLogService.getAccountLogByUserId(user.getId(),AccountType.getAccountTypeByValue("0"),BizType.getBizTypeByValue(bizType),this.pageNo, this.pageSize);
		if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
			  list = pager.getResultList();
			  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
		}
		
		return "list";
	}

	public List<AccountLog> getList() {
		return list;
	}

	public void setList(List<AccountLog> list) {
		this.list = list;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public UserStatistics getUserStatistics() {
		return userStatistics;
	}

	public void setUserStatistics(UserStatistics userStatistics) {
		this.userStatistics = userStatistics;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
