package com.mystore.business.pojo;

public enum SecurityLevelMap {
	
	LOW("低","low"),
	MIDDLE("中","mid"),
	HIGH("高","high");
	
	private String name;
	private String bh;
	
	private SecurityLevelMap(String name,String bh){
		this.name = name;
		this.bh = bh;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public static void main(String[] args){
		
		System.out.println(SecurityLevelMap.LOW.getBh());
		
	}
	
}
