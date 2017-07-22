package com.mystore.business.service;

import com.mystore.business.dto.PayLog;

public interface PayLogService {
	
	public int addPayLog(PayLog payLog);
	
	public int updatePayLogByOrderSn(PayLog payLog);
	
	public PayLog getPayLogByOrderSn(String sn);

}
