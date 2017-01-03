package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.UserAddress;

public interface UserAddressService {

	public List<UserAddress> getByUserId(Integer id);
	
	public UserAddress getById(Integer userId,Integer id);
	
	public void saveAddress(UserAddress userAddress);
	
	public void delAddress(Integer userId,Integer id);
	
	public void updateAddress(UserAddress userAddress);
	
}
