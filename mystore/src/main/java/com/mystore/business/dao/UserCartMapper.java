package com.mystore.business.dao;

import com.mystore.business.dto.UserCart;

public interface UserCartMapper {
	
	public int addCart(UserCart cart);
	
	public int updateCartByUserId(UserCart cart);

	public UserCart getCartByUserId(Integer userId);
}
