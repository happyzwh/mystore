package com.mystore.business.pojo;

public enum PayPlat {
	
	ZFB("支付宝","1"),
	WX("微信","2"),
	UNIONPAY("银联","3");
	
	private String name;
	private String value;
	
	private PayPlat(String name,String value){
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
		
		System.out.println(PayPlat.UNIONPAY.getValue());
		
	}

}
