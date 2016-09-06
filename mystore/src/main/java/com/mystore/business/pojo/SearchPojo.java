package com.mystore.business.pojo;

import java.util.List;
import java.util.Map;

import com.mystore.business.dto.Brand;
import com.mystore.business.dto.Category;

public class SearchPojo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//选择的分类id
	private Integer cateId;
	
	//选择分类的所有父分类(含当前选择的分类)
	private List<Category> selectedCates;
	
	//当前选择分类下可选下级所有分类
	private List<Category> selectCates;
	
	//当前选择分类下选择的品牌
	private List<Integer> brandIds;
	
	//当前选择分类下已选择的品牌
	private List<Brand> selectedBrands;
	
	//当前选择分类下可选择的品牌
	private List<Brand> selectBrands;
	
	//已选属性值id
	private List<Integer> attrValueIds;
	
	//当前分类的已选属性名
	private List<Map<String,String>> selectedCateAttrNames;
	
	//当前分类的已选属性值
	private List<Map<String,Map<String,Object>>> selectedCateAttrs;
	
	//当前分类的所有属性名
	private List<Map<String,String>> selectCateAttrNames;
	
    //当前分类的所有属性
	private List<Map<String,List<Map<String,Object>>>> selectCateAttrs;
	
	private Double lowPrice;
	
	private Double highPrice;
	
	private Integer orderType;
	
	private Integer asc;
	
	public List<Category> getSelectedCates() {
		return selectedCates;
	}
	
	public void setSelectedCates(List<Category> selectedCates) {
		this.selectedCates = selectedCates;
	}
	
	public List<Category> getSelectCates() {
		return selectCates;
	}
	
	public void setSelectCates(List<Category> selectCates) {
		this.selectCates = selectCates;
	}
	
	public List<Brand> getSelectBrands() {
		return selectBrands;
	}
	
	public void setSelectBrands(List<Brand> selectBrands) {
		this.selectBrands = selectBrands;
	}

	public List<Integer> getAttrValueIds() {
		return attrValueIds;
	}

	public void setAttrValueIds(List<Integer> attrValueIds) {
		this.attrValueIds = attrValueIds;
	}

	public List<Map<String, Map<String, Object>>> getSelectedCateAttrs() {
		return selectedCateAttrs;
	}

	public void setSelectedCateAttrs(
			List<Map<String, Map<String, Object>>> selectedCateAttrs) {
		this.selectedCateAttrs = selectedCateAttrs;
	}

	public List<Map<String, String>> getSelectedCateAttrNames() {
		return selectedCateAttrNames;
	}

	public void setSelectedCateAttrNames(
			List<Map<String, String>> selectedCateAttrNames) {
		this.selectedCateAttrNames = selectedCateAttrNames;
	}

	public List<Map<String, String>> getSelectCateAttrNames() {
		return selectCateAttrNames;
	}

	public void setSelectCateAttrNames(List<Map<String, String>> selectCateAttrNames) {
		this.selectCateAttrNames = selectCateAttrNames;
	}

	public List<Map<String, List<Map<String, Object>>>> getSelectCateAttrs() {
		return selectCateAttrs;
	}

	public void setSelectCateAttrs(
			List<Map<String, List<Map<String, Object>>>> selectCateAttrs) {
		this.selectCateAttrs = selectCateAttrs;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public List<Integer> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Integer> brandIds) {
		this.brandIds = brandIds;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getAsc() {
		return asc;
	}

	public void setAsc(Integer asc) {
		this.asc = asc;
	}

	public List<Brand> getSelectedBrands() {
		return selectedBrands;
	}

	public void setSelectedBrands(List<Brand> selectedBrands) {
		this.selectedBrands = selectedBrands;
	}
	
}
