package com.mystore.business.dao;

import com.mystore.business.dto.PayLog;

public interface PayLogMapper {
	
	public int addPayLog(PayLog payLog);
	
	public int updatePayLogByOrderSn(PayLog payLog);
	
	public PayLog getPayLogByOrderSn(String sn);

}
