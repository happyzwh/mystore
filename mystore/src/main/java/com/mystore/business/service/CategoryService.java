package com.mystore.business.service;

import java.util.List;

import com.mystore.business.dto.Category;

public interface CategoryService {
	
	/**
	 * 
	 * 查询分类的所有父分类
	 * 
	 * */
	public List<Category> getAllParentCategoryById(Integer id) throws Exception;
	
	/**
	 * 
	 * 查询分类的所有子分类
	 * 
	 * */
	public List<Category> getAllSonCategoryById(Integer id) throws Exception;

}
