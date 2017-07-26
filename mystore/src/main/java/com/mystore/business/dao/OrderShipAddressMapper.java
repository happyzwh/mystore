package com.mystore.business.dao;

import com.mystore.business.dto.OrderShipAddress;

public interface OrderShipAddressMapper {
	
	public int addOrderShipAddress(OrderShipAddress orderShipAddress);
	
	public OrderShipAddress getShipAddressByOrderId(Integer orderId);
	
}
