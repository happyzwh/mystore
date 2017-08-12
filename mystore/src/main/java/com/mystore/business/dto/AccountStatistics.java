package com.mystore.business.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountStatistics  extends BasicDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer accountId;
	
	private BigDecimal amountOrder;
	
	private BigDecimal amountOrderIng;
	
	private BigDecimal amountWithdrawFreeze;
	
	private BigDecimal amountCashBack;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAmountOrder() {
		return amountOrder;
	}

	public void setAmountOrder(BigDecimal amountOrder) {
		this.amountOrder = amountOrder;
	}

	public BigDecimal getAmountOrderIng() {
		return amountOrderIng;
	}

	public void setAmountOrderIng(BigDecimal amountOrderIng) {
		this.amountOrderIng = amountOrderIng;
	}

	public BigDecimal getAmountWithdrawFreeze() {
		return amountWithdrawFreeze;
	}

	public void setAmountWithdrawFreeze(BigDecimal amountWithdrawFreeze) {
		this.amountWithdrawFreeze = amountWithdrawFreeze;
	}

	public BigDecimal getAmountCashBack() {
		return amountCashBack;
	}

	public void setAmountCashBack(BigDecimal amountCashBack) {
		this.amountCashBack = amountCashBack;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
}
