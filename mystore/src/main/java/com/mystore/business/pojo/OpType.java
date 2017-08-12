package com.mystore.business.pojo;

public enum OpType {
	
	SYSTEM("系统","0"),
	CUSTOMERSERVICE("客服","1");
	
	private String name;
	private String value;
	
	private OpType(String name,String value){
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


}
