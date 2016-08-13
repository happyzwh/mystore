package com.mystore.business.dto;

import java.util.Date;

public class DocPoJo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer type;   //0:商品;1:分类;2:品牌
    
	private Integer id;
	
	private String name;
	
	private Integer id_brand;
	
	private Integer id_cate;
	
	private String ids_cate;
	
	private String enTitle;
	
	private String rome;
	
	private String jianPin;
	
	private String keyWords;
	
	private String sn;
	
	private String shortTitle;
	
	private String subTitle;
	
	private String path_pic;
	
	private Double shopPrice;
	
	private Double markPrice;
	
	private Date onSaleTime;
	
	private Integer id_country;
	
	private Integer id_province;
	
	private Integer sort;

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

	public Integer getId_brand() {
		return id_brand;
	}

	public void setId_brand(Integer idBrand) {
		id_brand = idBrand;
	}

	public String getIds_cate() {
		return ids_cate;
	}

	public void setIds_cate(String idsCate) {
		ids_cate = idsCate;
	}

	public String getEnTitle() {
		return enTitle;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
	}

	public String getRome() {
		return rome;
	}

	public void setRome(String rome) {
		this.rome = rome;
	}

	public String getJianPin() {
		return jianPin;
	}

	public void setJianPin(String jianPin) {
		this.jianPin = jianPin;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getPath_pic() {
		return path_pic;
	}

	public void setPath_pic(String pathPic) {
		path_pic = pathPic;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Double getMarkPrice() {
		return markPrice;
	}

	public void setMarkPrice(Double markPrice) {
		this.markPrice = markPrice;
	}

	public Date getOnSaleTime() {
		return onSaleTime;
	}

	public void setOnSaleTime(Date onSaleTime) {
		this.onSaleTime = onSaleTime;
	}

	public Integer getId_country() {
		return id_country;
	}

	public void setId_country(Integer idCountry) {
		id_country = idCountry;
	}

	public Integer getId_province() {
		return id_province;
	}

	public void setId_province(Integer idProvince) {
		id_province = idProvince;
	}

	public Integer getId_cate() {
		return id_cate;
	}

	public void setId_cate(Integer idCate) {
		id_cate = idCate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
