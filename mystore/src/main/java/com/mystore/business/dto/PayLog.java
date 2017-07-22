package com.mystore.business.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PayLog  extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer idOrder;
	
	private String snOrder;
	
	private String payPlat;
	
	private String payChannel;
	
	private String snPayPlat;
	
	private String bankCode;
	
	private BigDecimal amount;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public String getSnOrder() {
		return snOrder;
	}

	public void setSnOrder(String snOrder) {
		this.snOrder = snOrder;
	}

	public String getSnPayPlat() {
		return snPayPlat;
	}

	public void setSnPayPlat(String snPayPlat) {
		this.snPayPlat = snPayPlat;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayPlat() {
		return payPlat;
	}

	public void setPayPlat(String payPlat) {
		this.payPlat = payPlat;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
