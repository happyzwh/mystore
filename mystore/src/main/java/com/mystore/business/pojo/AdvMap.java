package com.mystore.business.pojo;

public enum AdvMap {
	
	INDEXTOPADV("首页顶部广告位","AA00001"),
	INDEXBANNERADV("首页banner广告位","AA00002"),
	INDEXHOTSALEDOWNADV("首页热卖推荐下广告位","AA00003"),
	INDEXNEWUPDOWNADV("首页新品上架下广告位","AA00004"),
	
	INDEXHOTSALEPROMODULE("首页热卖推荐商品模块","AB00001"),
	INDEXNEWUPMODULE("首页热新品上架商品模块","AB00002"),
	INDEXALLLIKEMODULE("首页大家鼓劲商品模块","AB00003"),
	
	INDEXHOTSALETXTMODULE("首页热卖推荐文字模块","AC00001"),
	INDEXNEWUPTXTMODULE("首页新品上架文字模块","AC00002");
	
	
	private String name;
	private String bh;
	
	private AdvMap(String name,String bh){
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
		
		System.out.println(AdvMap.INDEXTOPADV.bh);
		
	}
	

}
