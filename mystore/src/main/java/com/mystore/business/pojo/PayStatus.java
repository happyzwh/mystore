package com.mystore.business.pojo;

public enum PayStatus {

	UNDEAL("未处理","0"),
	SUBMITSUCCESS("提交成功","1"),
	SUBMITFAIL("提交失败","2"),
	PAYSUCCESS("支付成功","3"),
	PAYFAIL("支付失败","4"),
	FINISH("完成","9");
	
	private String name;
	private String value;
	
	private PayStatus(String name,String value){
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
		
		System.out.println(PayStatus.FINISH.getValue());
		
	}
	
	
}
