package com.mystore.business.service;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.Order;
import com.mystore.business.pojo.ShopOrder;

public interface OrderService {
	
	public int addOrder(Order order);
	
	public int updateOrderBySn(Order order);
	
	public Order getOrderBySn(String sn);
	
	public Pager<Order> getOrderByUserId(Order order,Integer pageNum,Integer pageSize);
	
	public Order saveOrder(Integer userId,ShopOrder shopOrder);

}
