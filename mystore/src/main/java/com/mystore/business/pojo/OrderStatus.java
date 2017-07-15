package com.mystore.business.pojo;

public enum OrderStatus {
	
	UNCONFIRM("0","未确认"),
	CONFIRM("1","已确认"),
	CANCEL("2","取消"),
	INVALID("3","无效"),
	LOCKED("4","锁定"),
	DEALING("5","处理中"),
	SHIP("6","已发货"),
	RECEIVECONFIRM("7","已收货确认"),
	RETURNING("8","退货中"),
	RETURNSOME("9","部分退货"),
	RETURNALL("10","全部退货"),
	OVER("11","完成"),
	RETURNOVER("12","退货完成");
	
	private String value;
	private String name;
	private OrderStatus(String value,String name){
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
