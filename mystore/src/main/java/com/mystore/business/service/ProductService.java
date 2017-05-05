package com.mystore.business.service;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.ProAttr;
import com.mystore.business.dto.ProImg;
import com.mystore.business.dto.ProInfo;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;

public interface ProductService {
	/**
	 * 根据主键查询商品
	 * 
	 * */
	public Product getProById(Integer id);
	
	/**
	 * 根据商品id得商品属性
	 * 
	 * */
	public List<ProAttr> getProAttrByProId(Integer proId);
	
	/**
	 * 根据商品id得商品属性
	 * 
	 * */
	public List<Map<String,Object>> getProAttrMapByProId(Integer proId);
	
	/**
	 * 根据主键查询商品图片
	 * 
	 * */
	public List<ProImg> getProImgListByProId(Integer proId);
	
	/**
	 * 根据主键查询商品详情
	 * 
	 * */
	public ProInfo getProInfoByProId(Integer proId);
	
	/**
	 * 根据主键查询商品价格
	 * 
	 * */
	public ProPrice getProPriceByProId(Integer proId);
	
	/**
	 * 根据分类id得商品属性
	 * 
	 * */
	public List<Map<String,Object>> getProAttrMapByCateId(Integer cateId);
	
	/**
	 * 查询所有商品id
	 * 
	 * */
	public List<Integer> getAllProId();
}
