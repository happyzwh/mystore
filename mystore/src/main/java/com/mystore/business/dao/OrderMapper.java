package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Order;

public interface OrderMapper {
	
	public int addOrder(Order order);
	
	public int updateOrderBySn(Order order);
	
	public Order getOrderBySn(String sn);
	
	public List<Order> getOrderByUserId(Order order);
	
	public int getOrderCountByUserId(Order order);

}
