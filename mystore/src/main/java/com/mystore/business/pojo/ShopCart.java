package com.mystore.business.pojo;

import java.util.ArrayList;
import java.util.List;

public class ShopCart implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Goods> goodsList;
	
	private Double totalAmount;
	
	private Integer totalCount;
	
	private Double discount;
	
	public ShopCart(){
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
	
	
}