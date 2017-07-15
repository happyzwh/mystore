package com.mystore.business.pojo;

public enum OrderCreateType {
	
	USERPCORDER("1","用户官网下单"),
	CUSTOMERSERVICEORDER("2","客服下单"),
	CUSTOMERSERVICEBILLORDER("3","客服拆单"),
	CUSTOMERSERVICEFILLORDER("4","客服补单");
	
	private String value;
	private String name;
	private OrderCreateType(String value,String name){
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
