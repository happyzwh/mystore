package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.OrderProduct;

public interface OrderProductService {
	
	public int addOrderProduct(OrderProduct orderProduct);
	
	public List<OrderProduct> getOrderProductByOrderId(Integer orderId);

}
