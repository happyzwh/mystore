package com.mystore.business.pojo;

public class SynCart implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id_user;

	private Long time;
	
	private Integer count;
	
	private String cart;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	
}
