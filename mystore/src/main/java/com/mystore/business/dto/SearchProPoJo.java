package com.mystore.business.dto;

public class SearchProPoJo extends BasicDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//0：商品、1：分类、2：品牌
	private Integer type;
	
	private Integer id;
	
	private String name;
	
	private String url;
	
	private String path_pic;
	
	private String markPrice;
	
	private String shopPrice;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath_pic() {
		return path_pic;
	}

	public void setPath_pic(String pathPic) {
		path_pic = pathPic;
	}

	public String getMarkPrice() {
		return markPrice;
	}

	public void setMarkPrice(String markPrice) {
		this.markPrice = markPrice;
	}

	public String getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}
	
	
	
}
