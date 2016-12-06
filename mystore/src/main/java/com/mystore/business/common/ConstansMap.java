package com.mystore.business.common;

public enum ConstansMap {
	
	HELP("帮助中心","help");
	
	private String name;
	private String code;
	
	private ConstansMap(String name,String code){
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
