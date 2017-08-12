package com.mystore.business.dto;

import java.math.BigDecimal;

public class UserStatistics  extends BasicDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private BigDecimal amountOrder;
	
	private BigDecimal amountOrderIng;
	
	private BigDecimal amountWithdrawFreeze;
	
	private BigDecimal amountCashBack;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
