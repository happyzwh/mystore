package com.mystore.business.pojo;

public enum RetStatusPay {
	SUCCESS("成功","1"),
	FAIL("失败","0"),
	SIGNERROR("验签失败","-1");
	
	private String name;
	private String value;
	
	private RetStatusPay(String name,String value){
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
		
		System.out.println(RetStatusPay.SUCCESS.getValue());
		
	}

}
