package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Brand;

public interface BrandService {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Brand getBrandById(Integer id);
	
	/**
	 * 根据分类求对象列表
	 * 
	 * */
	public List<Brand> selectBrandListByCateId(Integer cateId);

}
