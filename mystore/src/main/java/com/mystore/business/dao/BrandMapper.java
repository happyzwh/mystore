package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Brand;

public interface BrandMapper {
	
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
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Brand> selectBrandListByPid(Integer pid);

}
