package com.mystore.business.pojo;

public enum OrderPayStatus {
	
	UNPAY("未付款","0"),  //付0元
	PAYING("付款中","1"),  //部分付款
	PAYED("已付款","2");   //付全款
	
	private String name;
	private String value;
	
	private OrderPayStatus(String name,String value){
		this.name = name;
		this.value = value;
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

	public static void main(String[] args){
		
		System.out.println(OrderPayStatus.UNPAY.getValue());
		
	}

}
