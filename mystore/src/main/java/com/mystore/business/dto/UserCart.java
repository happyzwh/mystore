package com.mystore.business.dto;

public class UserCart extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer id_user;
	
	private String cart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

}
