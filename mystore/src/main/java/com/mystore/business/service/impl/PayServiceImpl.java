package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.PayLogMapper;
import com.mystore.business.dto.Order;
import com.mystore.business.dto.PayLog;
import com.mystore.business.pojo.OrderPayStatus;
import com.mystore.business.pojo.PayStatus;
import com.mystore.business.service.OrderService;
import com.mystore.business.service.PayService;

@Service
public class PayServiceImpl implements PayService{
	
	@Autowired
	private PayLogMapper payLogMapper;
	
	@Autowired
	private OrderService orderService;

	@Override
	public int addPayLog(PayLog payLog) {
		// TODO Auto-generated method stub
		return payLogMapper.addPayLog(payLog);
	}

	@Override
	public int updatePayLogByOrderSn(PayLog payLog) {
		// TODO Auto-generated method stub
		return payLogMapper.updatePayLogByOrderSn(payLog);
	}

	@Override
	public PayLog getPayLogByOrderSn(String sn) {
		// TODO Auto-generated method stub
		return payLogMapper.getPayLogByOrderSn(sn);
	}

	@Override
	public void doPay(String snOrder, String snPayPlat,String payStatus) {
		// TODO Auto-generated method stub
		
		PayLog payLog = payLogMapper.getPayLogByOrderSn(snOrder);
		payLog.setSnPayPlat(snPayPlat);
		payLog.setStatus(payStatus);
		
		payLogMapper.updatePayLogByOrderSn(payLog);
		
		Order order = orderService.getOrderBySn(snOrder);
		
		if(payStatus.equals(PayStatus.PAYSUCCESS.getValue())){
		    
			//0、更新订单支付状态
			order.setIspaid(OrderPayStatus.PAYED.getValue());
			orderService.updateOrderBySn(order);
			
			//1、如果有余额操作  需将帐户冻结金额划出 且 增加帐户流水
			
		}
	}

}
