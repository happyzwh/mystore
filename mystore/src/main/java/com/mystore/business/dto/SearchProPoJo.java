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
	
	private Double markPrice;
	
	private Double shopPrice;
	//库存
	private Integer count_stock;
	//评论数量
	private Integer count_comment;
	//销量
	private Integer count_sale;
	//信用
	private float credit;


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

	public Double getMarkPrice() {
		return markPrice;
	}

	public void setMarkPrice(Double markPrice) {
		this.markPrice = markPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getCount_stock() {
		return count_stock;
	}

	public void setCount_stock(Integer count_stock) {
		this.count_stock = count_stock;
	}

	public Integer getCount_comment() {
		return count_comment;
	}

	public void setCount_comment(Integer count_comment) {
		this.count_comment = count_comment;
	}

	public Integer getCount_sale() {
		return count_sale;
	}

	public void setCount_sale(Integer count_sale) {
		this.count_sale = count_sale;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}
	
}
