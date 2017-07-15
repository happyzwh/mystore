package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.OrderShipAddressMapper;
import com.mystore.business.dto.OrderShipAddress;
import com.mystore.business.service.OrderShipAddressService;

@Service
public class OrderShipAddressServiceImpl implements OrderShipAddressService{

	@Autowired
	private OrderShipAddressMapper orderShipAddressMapper;
	
	@Override
	public int addOrderShipAddress(OrderShipAddress orderShipAddress) {
		// TODO Auto-generated method stub
		return orderShipAddressMapper.addOrderShipAddress(orderShipAddress);
	}

}
