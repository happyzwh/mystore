package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.OrderProductMapper;
import com.mystore.business.dto.OrderProduct;
import com.mystore.business.service.OrderProductService;

@Service
public class OrderProductServiceImpl implements OrderProductService{
	
	@Autowired
	private OrderProductMapper orderProductMapper;

	@Override
	public int addOrderProduct(OrderProduct orderProduct) {
		// TODO Auto-generated method stub
		return orderProductMapper.addOrderProduct(orderProduct);
	}

	@Override
	public List<OrderProduct> getOrderProductByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return orderProductMapper.getOrderProductByOrderId(orderId);
	}

}
