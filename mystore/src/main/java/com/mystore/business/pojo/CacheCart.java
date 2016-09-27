package com.mystore.business.pojo;

import java.util.HashMap;
import java.util.Map;

public class CacheCart implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean changed;
	
	private Map<Integer,Integer> cart;
	
	private Integer count;
	
	private Integer totalCount;
	
	public CacheCart(boolean changed){
		this.changed = changed;
		cart = new HashMap<Integer,Integer>();
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public Map<Integer, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, Integer> cart) {
		this.cart = cart;
	}

	public Integer getCount() {
		if(count == null){
			count = (cart != null && !cart.isEmpty())?cart.size():0;
		}
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotalCount() {
		if(totalCount == null){
			totalCount = 0;
			if(cart != null && !cart.isEmpty()){
				for(Integer key:cart.keySet()){
					totalCount += cart.get(key);
				}
			}
		}
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	 
}
