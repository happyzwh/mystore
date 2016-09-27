package com.mystore.business.service;

import com.mystore.business.dto.UserCart;

public interface UserCartService {
	
	public int addCart(UserCart cart);
	
	public int updateCartByUserId(UserCart cart);

	public UserCart getCartByUserId(Integer userId);

}
