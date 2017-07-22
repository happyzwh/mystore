package com.mystore.business.service;

import com.mystore.business.dto.PayLog;

public interface PayService {
	
	public int addPayLog(PayLog payLog);
	
	public int updatePayLogByOrderSn(PayLog payLog);
	
	public PayLog getPayLogByOrderSn(String sn);
	
	public void doPay(String snOrder,String snPayPlat,String payStatus);

}
