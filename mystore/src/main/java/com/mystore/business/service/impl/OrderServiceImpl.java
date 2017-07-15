package com.mystore.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.OrderMapper;
import com.mystore.business.dto.Order;
import com.mystore.business.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	public OrderMapper orderMapper;
	

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.addOrder(order);
	}

	@Override
	public int updateOrderBySn(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderBySn(order);
	}

	@Override
	public Order getOrderBySn(String sn) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderBySn(sn);
	}

	@Override
	public Pager<Order> getOrderByUserId(Order order,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			order.setPageIndex(pageIndex);
			order.setPageSize(pageSize);
		}
		Pager<Order> pager = new Pager<Order>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = orderMapper.getOrderCountByUserId(order);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Order> list = orderMapper.getOrderByUserId(order);
		pager.setResultList(list);
		return pager;
	}


}
