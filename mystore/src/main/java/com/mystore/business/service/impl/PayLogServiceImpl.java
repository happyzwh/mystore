package com.mystore.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.PayLogMapper;
import com.mystore.business.dto.PayLog;
import com.mystore.business.service.PayLogService;

@Service
public class PayLogServiceImpl implements PayLogService{
	
	@Autowired
	private PayLogMapper PayLogMapper;

	@Override
	public int addPayLog(PayLog payLog) {
		// TODO Auto-generated method stub
		return PayLogMapper.addPayLog(payLog);
	}

	@Override
	public int updatePayLogByOrderSn(PayLog payLog) {
		// TODO Auto-generated method stub
		return PayLogMapper.updatePayLogByOrderSn(payLog);
	}

	@Override
	public PayLog getPayLogByOrderSn(String sn) {
		// TODO Auto-generated method stub
		return PayLogMapper.getPayLogByOrderSn(sn);
	}

}
