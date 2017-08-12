package com.mystore.business.pojo;

public enum IoType {
	
	out("出帐","0"),
	in("入帐","1");
	
	private String name;
	private String value;
	
	private IoType(String name,String value){
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
