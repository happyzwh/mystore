package com.mystore.business.dao;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.UserAddress;

public interface UserAddressMapper {
	
	public List<UserAddress> getByUserId(Integer id);
	
	public UserAddress getById(Map<String,Object> map);
	
	public void saveAddress(UserAddress userAddress);
	
	public void delAddress(Map<String,Object> map);
	
	public void updateAddress(UserAddress userAddress);

}
