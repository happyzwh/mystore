package com.mystore.business.pojo;

import java.util.List;

public class SearchPojo {
	
	//选择的分类
	private SearchCate selectedCate;
	//选择分类的所有父分类(含当前选择的分类)
	private List<SearchCate> selectedCates;
	//当前选择分类下可选下级所有分类
	private List<SearchCate> selectCates;
	//当前选择分类下选择的品牌
	private List<SearchBrand> selectedBrands;
	//当前选择分类下可选择的品牌
	private List<SearchBrand> selectBrands;
    //当前分类的所有属性
	
}
