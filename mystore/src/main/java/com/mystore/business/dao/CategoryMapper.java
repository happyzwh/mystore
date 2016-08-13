package com.mystore.business.dao;

import java.util.List;

import com.mystore.business.dto.Category;


public interface CategoryMapper {
	
	/**
	 * 根据主键得实体
	 * 
	 * */
	public Category getCateById(Integer id);
	
	/**
	 * 根据父id求对象列表
	 * 
	 * */
	public List<Category> selectCateListByPid(Integer pid);
	
}
