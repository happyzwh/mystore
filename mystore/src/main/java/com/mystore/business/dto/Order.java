package com.mystore.business.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String sn;
	
	private Integer id_user;
	
	private String source;
	
	private BigDecimal amount;
	
	private BigDecimal amount_paid;
	
	private BigDecimal amount_payable;
	
	private BigDecimal  amount_return;

	private BigDecimal amount_disc;
	
	private BigDecimal amount_balance_pay;
	
	private BigDecimal  fare;
	
	private String ispaid;
	
	private String payName;
	
	private Date time_pay;
	
	private String isdelivery;
	
	private String deliveryName;

	private String status;
	
	private String statusName;
	
	private String createtype;
	
	private String repstatus;
	
	private String payWay;
	
	private Date createDate;
	
	private Date lastDate;

	private List<OrderProduct> orderProudcts;
	
	private PayLog payLog;
	
	private OrderShipAddress shipAddress;
	
	private OrderInvoice orderInvoice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}

	public BigDecimal getAmount_payable() {
		return amount_payable;
	}

	public void setAmount_payable(BigDecimal amount_payable) {
		this.amount_payable = amount_payable;
	}

	public BigDecimal getAmount_return() {
		return amount_return;
	}

	public void setAmount_return(BigDecimal amount_return) {
		this.amount_return = amount_return;
	}

	public BigDecimal getAmount_disc() {
		return amount_disc;
	}

	public void setAmount_disc(BigDecimal amount_disc) {
		this.amount_disc = amount_disc;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public String getIspaid() {
		return ispaid;
	}

	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}

	public Date getTime_pay() {
		return time_pay;
	}

	public void setTime_pay(Date time_pay) {
		this.time_pay = time_pay;
	}

	public String getIsdelivery() {
		return isdelivery;
	}

	public void setIsdelivery(String isdelivery) {
		this.isdelivery = isdelivery;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetype() {
		return createtype;
	}

	public void setCreatetype(String createtype) {
		this.createtype = createtype;
	}

	public String getRepstatus() {
		return repstatus;
	}

	public void setRepstatus(String repstatus) {
		this.repstatus = repstatus;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
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

	public BigDecimal getAmount_balance_pay() {
		return amount_balance_pay;
	}

	public void setAmount_balance_pay(BigDecimal amount_balance_pay) {
		this.amount_balance_pay = amount_balance_pay;
	}

	public List<OrderProduct> getOrderProudcts() {
		return orderProudcts;
	}

	public void setOrderProudcts(List<OrderProduct> orderProudcts) {
		this.orderProudcts = orderProudcts;
	}

	public PayLog getPayLog() {
		return payLog;
	}

	public void setPayLog(PayLog payLog) {
		this.payLog = payLog;
	}

	public OrderShipAddress getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(OrderShipAddress shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getStatusName() {
		statusName = "未确认";
		if(status.equals("1")){
			statusName = "已确认";
		}else if(status.equals("2")){
			statusName = "已取消";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public OrderInvoice getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(OrderInvoice orderInvoice) {
		this.orderInvoice = orderInvoice;
	}
	
	public String getDeliveryName() {
		deliveryName = "未发货";
		if(isdelivery.equals("1")){
			deliveryName = "已配送";
		}else if(isdelivery.equals("2")){
			deliveryName = "已拒收";
		}else if(isdelivery.equals("3")){
			deliveryName = "已签收";
		}
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getPayName() {
		payName = "未付款";
		if(ispaid.equals("1")){
			payName = "付款中";
		}else if(ispaid.equals("2")){
			payName = "已付款";
		}
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	
}
