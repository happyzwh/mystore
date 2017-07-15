package com.mystore.business.pojo;

import java.util.ArrayList;
import java.util.List;

public class ShopOrder implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Goods> goodsList;
	
	private Double totalAmount = 0d;
	
	private Double totalMarkAmount = 0d;
	
	private Integer totalCount;
	
	private Double discount = 0d;
	
	private Double fare = 0d;
	
	private Double payAmount = 0d;
	
	public ShopOrder(){
		goodsList = new ArrayList<Goods>();
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Double getTotalAmount() {
		totalAmount = 0d;
		for(Goods goods:goodsList){
			totalAmount += goods.getTotalPrice();
		}
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalCount() {
		totalCount = 0;
		for(Goods goods:goodsList){
			totalCount += goods.getCount();
		}
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Double getDiscount() {
		discount = 0d;
		for(Goods goods:goodsList){
			discount += goods.getMarkPrice() - goods.getPrice();
		}
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTotalMarkAmount() {
		totalMarkAmount = 0d;
		for(Goods goods:goodsList){
			totalMarkAmount += goods.getTotalMarkPrice();
		}
		return totalMarkAmount;
	}

	public void setTotalMarkAmount(Double totalMarkAmount) {
		this.totalMarkAmount = totalMarkAmount;
	}

	public Double getFare() {
		fare = 0d;
		if(this.getTotalAmount() < 199){
			fare = 10d;
		}
		return fare;
	}

	public Double getPayAmount() {
		payAmount = this.getTotalAmount() + this.getFare();
		return payAmount;
	}
	
	
}
