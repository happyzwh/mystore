package com.mystore.business.pojo;

public enum MenuMap {
	
	MYORDER("我的订单","100"),
	BACKLOG("退货记录","101"),
	
	MYACCOUNT("我的余额","200"),
	
	SECURITY("帐户安全","300"),
	BASICINFO("基本信息","301"),
	ADDRESS("收货地址","302"),
	QUCKPAY("快捷支付","303");
	
	private String name;
	private String bh;
	
	private MenuMap(String name,String bh){
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
		
		System.out.println(MenuMap.ADDRESS.getBh());
		
	}
	
}
