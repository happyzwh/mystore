package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.OrderProduct;

public interface OrderProductMapper {
	
	public int addOrderProduct(OrderProduct orderProduct);
	
	public List<OrderProduct> getOrderProductByOrderId(Integer orderId);

}
