package com.mystore.business.service;

import java.util.List;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.SearchProPoJo;

public interface SearchService {
	/**
	 * 
	 * 创建索引
	 * 
	 * */
	public void createIndex() throws Exception;
	
	/**
	 * 
	 * 条件检索
	 * 
	 * */
	public Pager<SearchProPoJo> search(Integer id_category,Integer id_brand,Integer id_country,Integer id_province,Integer price_low,Integer price_high,Integer type_sort_attr,Integer type_sort,Integer pageNo,Integer pageSize) throws Exception;

	/**
	 * 
	 * 模糊查询
	 * 
	 * */
    public List<SearchProPoJo> mhSearch(String keys);
}
