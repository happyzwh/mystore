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

}
