package com.mystore.business.service;

import com.mystore.business.dto.OrderShipAddress;

public interface OrderShipAddressService {
	
	public int addOrderShipAddress(OrderShipAddress orderShipAddress);
	
	public OrderShipAddress getShipAddressByOrderId(Integer orderId);

}
