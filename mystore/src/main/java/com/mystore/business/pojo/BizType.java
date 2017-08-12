package com.mystore.business.pojo;

import org.apache.commons.lang3.StringUtils;

public enum BizType {
	
	RECHARGE("充值","0"),
	WITHDRAWFREEZE("提现冻结","1"),
	WITHDRAWPAY("提现付款","2"),
	ORDERFREEZE("下单冻结","3"),
	ORDERPAY("下单付款","4"),
	REFUNDS("退款","5"),
	CASHBACK("返现","6");
	
	private String name;
	private String value;
	
	private BizType(String name,String value){
		this.name = name;
		this.value = value;
	}
	
	public static BizType getBizTypeByValue(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		for(BizType bizType:BizType.values()){
			if(bizType.getValue().equals(value)){
				return bizType;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
