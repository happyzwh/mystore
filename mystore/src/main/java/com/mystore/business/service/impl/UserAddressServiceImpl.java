package com.mystore.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.UserAddressMapper;
import com.mystore.business.dto.UserAddress;
import com.mystore.business.service.UserAddressService;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService{

	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Override
	public List<UserAddress> getByUserId(Integer id) {
		// TODO Auto-generated method stub
		return userAddressMapper.getByUserId(id);
	}

	@Override
	public UserAddress getById(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_user", userId);
		map.put("id", id);
		return userAddressMapper.getById(map);
	}

	@Override
	public void saveAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		userAddressMapper.saveAddress(userAddress);
	}

	@Override
	public void delAddress(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_user", userId);
		map.put("id", id);
		userAddressMapper.delAddress(map);
	}

	@Override
	public void updateAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		userAddressMapper.updateAddress(userAddress);
	}

	@Override
	public void updateDefaultAsNot(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		UserAddress userAddress = this.getById(userId, id);
		if(userAddress != null){
			userAddressMapper.updateDefaultAsNot(userId);
			userAddress.setIsDefault("1");
			userAddressMapper.updateAddress(userAddress);
			
		}
		
	}

}
