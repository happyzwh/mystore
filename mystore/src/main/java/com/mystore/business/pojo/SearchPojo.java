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
	
	private String keys;

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
	private List<String> attrValueIds;
	
	//当前分类的已选属性名
	private List<Map<String,String>> selectedCateAttrNames;
	
	//当前分类的所有属性名
	private Map<String,String> selectCateAttrNames;
	
    //当前分类的所有属性
	private Map<String,List<Map<String,Object>>> selectCateAttrs;
	
	//当前分类的所有属性id
	private List<String> selectCateAttrIds;
	
	private Boolean haseLeaf;
	
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

	public List<String> getAttrValueIds() {
		return attrValueIds;
	}

	public void setAttrValueIds(List<String> attrValueIds) {
		this.attrValueIds = attrValueIds;
	}

	public List<Map<String, String>> getSelectedCateAttrNames() {
		return selectedCateAttrNames;
	}

	public void setSelectedCateAttrNames(
			List<Map<String, String>> selectedCateAttrNames) {
		this.selectedCateAttrNames = selectedCateAttrNames;
	}

	public Map<String, String> getSelectCateAttrNames() {
		return selectCateAttrNames;
	}

	public void setSelectCateAttrNames(Map<String, String> selectCateAttrNames) {
		this.selectCateAttrNames = selectCateAttrNames;
	}

	public Map<String, List<Map<String, Object>>> getSelectCateAttrs() {
		return selectCateAttrs;
	}

	public void setSelectCateAttrs(
			Map<String, List<Map<String, Object>>> selectCateAttrs) {
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

	public List<String> getSelectCateAttrIds() {
		return selectCateAttrIds;
	}

	public void setSelectCateAttrIds(List<String> selectCateAttrIds) {
		this.selectCateAttrIds = selectCateAttrIds;
	}

	public Boolean getHaseLeaf() {
		if(haseLeaf != null)return haseLeaf;
		if(selectCates != null && selectCates.size() > 0){
			for(Category cate:selectCates){
				if(cate.getSons() == null || cate.getSons().size() == 0){
					haseLeaf = true;
					return haseLeaf;
				}
			}
			haseLeaf = false;
		}
		return haseLeaf;
	}

	public void setHaseLeaf(Boolean haseLeaf) {
		this.haseLeaf = haseLeaf;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
	
}
