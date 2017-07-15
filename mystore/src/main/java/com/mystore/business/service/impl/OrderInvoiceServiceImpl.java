package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.OrderInvoiceMapper;
import com.mystore.business.dto.OrderInvoice;
import com.mystore.business.service.OrderInvoiceService;

@Service
public class OrderInvoiceServiceImpl implements OrderInvoiceService{

	@Autowired
	private OrderInvoiceMapper orderInvoiceMapper;
	
	@Override
	public int addOrderInvoice(OrderInvoice OrderInvoice) {
		// TODO Auto-generated method stub
		return orderInvoiceMapper.addOrderInvoice(OrderInvoice);
	}

}
