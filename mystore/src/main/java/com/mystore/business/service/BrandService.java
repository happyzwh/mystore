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
	
	/**
	 * 根据分类求所有对象列表(含所有子分类)
	 * 
	 * */
	public List<Brand> selectAllBrandListByCateId(Integer cateId);
	
	/**
	 * 
	 * 查询分类的所有父品牌
	 * 
	 * */
	public List<Brand> getAllParentBrandById(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询分类的所有子品牌
	 * 
	 * */
	public List<Brand> getAllSonBrandById(Integer id) throws Exception;

}
