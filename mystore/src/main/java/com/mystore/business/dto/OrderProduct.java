package com.mystore.business.dto;

import java.util.Date;

public class OrderProduct  extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_order;
	
	private Integer id_pro;
	
	private String sn;
	
	private String name;
	
	private Double markprice;
	
	private Double buyprice;
	
	private Integer number;
	
	private Double amount;
	
	private String isgived;
	
	private String status;
	
	private Date createDate;
	
	private Date lastDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_order() {
		return id_order;
	}

	public void setId_order(Integer id_order) {
		this.id_order = id_order;
	}

	public Integer getId_pro() {
		return id_pro;
	}

	public void setId_pro(Integer id_pro) {
		this.id_pro = id_pro;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMarkprice() {
		return markprice;
	}

	public void setMarkprice(Double markprice) {
		this.markprice = markprice;
	}

	public Double getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(Double buyprice) {
		this.buyprice = buyprice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIsgived() {
		return isgived;
	}

	public void setIsgived(String isgived) {
		this.isgived = isgived;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
