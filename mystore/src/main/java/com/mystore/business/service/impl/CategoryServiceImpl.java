package com.mystore.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.dao.CategoryMapper;
import com.mystore.business.dto.Category;
import com.mystore.business.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getAllParentCategoryById(Integer id){
		List<Category> list = new ArrayList<Category>();
	    if(id != null){
	    	getCategoryById(list,id);
	    }
		return list;
	}
	
    private void getCategoryById(List<Category> list,Integer id){
    	Category cate = categoryMapper.getCateById(id);
    	if(cate != null){
	    	list.add(cate);
	    	if(cate.getPid() != null){
	    		getCategoryById(list,cate.getPid());
	    	}
    	}
    }
	
	@Override
	public List<Category> getAllSonCategoryById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
	    if(id != null){
	    	list = categoryMapper.selectCateListByPid(id);
	    	if(list != null && list.size() > 0){
	    		for(Category cate:list){
	    			getSonCategoryById(cate);
	    		}
	    	}
	    }
		return list;
	}
	
	private void getSonCategoryById(Category cate){
    	List<Category> cates = categoryMapper.selectCateListByPid(cate.getId());
    	if(cates != null && cates.size() > 0){
    		cate.setSons(cates);
    		for(Category cat:cates){
    			getSonCategoryById(cat);
    		}
    	}
    }

}
