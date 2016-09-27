package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.UserCartMapper;
import com.mystore.business.dto.UserCart;
import com.mystore.business.service.UserCartService;

@Service("userCartService")
public class UserCartServiceImpl implements UserCartService{
	
	@Autowired
	private UserCartMapper userCartMapper;

	@Override
	public int addCart(UserCart cart) {
		// TODO Auto-generated method stub
		return userCartMapper.addCart(cart);
	}

	@Override
	public int updateCartByUserId(UserCart cart) {
		// TODO Auto-generated method stub
		return userCartMapper.updateCartByUserId(cart);
	}

	@Override
	public UserCart getCartByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userCartMapper.getCartByUserId(userId);
	}

}
