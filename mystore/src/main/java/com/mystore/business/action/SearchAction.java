package com.mystore.business.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.PageInfo;
import com.mystore.business.common.Pager;
import com.mystore.business.dto.Brand;
import com.mystore.business.dto.SearchProPoJo;
import com.mystore.business.pojo.SearchPojo;
import com.mystore.business.service.BrandService;
import com.mystore.business.service.CategoryService;
import com.mystore.business.service.ProductService;
import com.mystore.business.service.SearchService;

@Controller("searchAction")
@Scope("prototype")
public class SearchAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductService productService;
	
	private String keys;
	
	private Integer cateId;
	
	private String brandIds;
	
	private String attrValueIds;
	
	private Double lowPrice;
	
	private Double highPrice;
	
	private Integer orderType;
	
	private Integer asc;
	
	private SearchPojo searchPojo;
	
	private List<SearchProPoJo> list;
	
	public void mhSearch() throws IOException, JSONException{
		JSONObject data = new JSONObject();
		Integer returnCode = 0;
		try{
			
			if(StringUtils.isBlank(keys)){
				returnCode = -2;
				return;
			}
			
			List<SearchProPoJo> list = searchService.mhSearch(keys);
			if(list != null && list.size() > 0){
				JSONArray jsonList = new JSONArray();
				for(SearchProPoJo searchProPoJo:list){
					JSONObject ent = new JSONObject();
					ent.put("name", searchProPoJo.getName());
					ent.put("url", searchProPoJo.getUrl());
					jsonList.put(ent);
				}
				data.put("list", jsonList);
			}
			
		}catch(Exception e){
			returnCode = -1;
		}finally{
			data.put("returnCode",returnCode);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(data.toString());
		}

	}
	
	public String list(){
		
		if(StringUtils.isBlank(keys)){
			return "list";
		}
		
		String[] key = keys.split("-");
		
		if(key.length < 8){
			return "list";
		}
		
		try{
			
			Integer id_category = Integer.valueOf(key[0]);
			Integer id_brand = Integer.valueOf(key[1]);
			Integer id_country = Integer.valueOf(key[2]);
			Integer id_province = Integer.valueOf(key[3]);
			
			double price_low = Double.valueOf(key[4]);
			double price_high = Double.valueOf(key[5]);
			
			Integer type_sort_attr = Integer.valueOf(key[6]);
			Integer type_sort = Integer.valueOf(key[7]);
			
			Pager<SearchProPoJo> pager =  searchService.search(id_category, id_brand, id_country, id_province, price_low, price_high, type_sort_attr, type_sort, pageNo, pageSize);
			
			if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
				  list = pager.getResultList();
				  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "list";
	}

	public String lists(){
		
		try{
			
			SearchPojo searchPojo = new SearchPojo();
			searchPojo.setCateId(cateId);
			searchPojo.setLowPrice(lowPrice);
			searchPojo.setHighPrice(highPrice);
			searchPojo.setOrderType(orderType);
			searchPojo.setAsc(asc);
			
			if(StringUtils.isNotBlank(brandIds)){
				List<Integer> brands = new ArrayList<Integer>();
				String[] ids = brandIds.split(",");
				for(String s:ids){
					brands.add(Integer.valueOf(s));
				}
				searchPojo.setBrandIds(brands);
			}
			
			if(StringUtils.isNotBlank(attrValueIds)){
				List<Integer> attrValues = new ArrayList<Integer>();
				String[] ids = attrValueIds.split(",");
				for(String s:ids){
					attrValues.add(Integer.valueOf(s));
				}
				searchPojo.setAttrValueIds(attrValues);
			}
			
			Pager<SearchProPoJo> pager =  searchService.search(searchPojo, pageNo, pageSize);
			
			if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
				  list = pager.getResultList();
				  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
			}
			
			searchPojo.setSelectedCates(categoryService.getAllParentCategoryById(cateId));
			
			searchPojo.setSelectCates(categoryService.getAllSonCategoryById(cateId));
			
			if(StringUtils.isNotBlank(brandIds)){
				String[] ids = brandIds.split(",");
				searchPojo.setSelectedBrands(new ArrayList<Brand>());
				for(String s:ids){
					searchPojo.getSelectedBrands().add(brandService.getBrandById(Integer.valueOf(s)));
				}
			}
			
			List<Brand> allBrandlist = brandService.selectBrandListByCateId(cateId);
			if(allBrandlist != null && allBrandlist.size() > 0){
				for(Brand brand:allBrandlist){
					if(brandIds.indexOf(String.valueOf(brand.getId())) != -1){
						allBrandlist.remove(brand);
					}
				}
			}
			
			searchPojo.setSelectBrands(allBrandlist);
			
			List<Map<String,Object>> allAttrValue = productService.getProAttrMapByCateId(cateId);
			List<Map<String,Object>> selectedAttrValue = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> selectAttrValue = new ArrayList<Map<String,Object>>();;
			
			if(allAttrValue != null && allAttrValue.size() > 0){
				for(Map<String,Object> map:allAttrValue){
					if(!map.isEmpty() && map.get("vid") != null){
						String[] str = map.get("vid").toString().split(",");
						boolean tf = false;
						for(String s:str){
							if((","+attrValueIds+",").indexOf(","+s+",") != -1){
								selectedAttrValue.add(map);
								tf = true;
								break;
							}
						}
						if(!tf){
							selectAttrValue.add(map);
						}
					}
				}
			}
			
			if(selectedAttrValue.size() > 0){
				List<Map<String,Map<String,Object>>> selectedCateAttrs = new ArrayList<Map<String,Map<String,Object>>>();
				searchPojo.setSelectedCateAttrs(selectedCateAttrs);
				for(Map<String,Object> map : selectedAttrValue){
					Map<String,Map<String,Object>> smap = new HashMap<String,Map<String,Object>>();
					selectedCateAttrs.add(smap);
					Map<String,Object> attr = new HashMap<String,Object>();
//					smap.put("attr", map.get(""));
				}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "list";
	}
	
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public List<SearchProPoJo> getList() {
		return list;
	}

	public void setList(List<SearchProPoJo> list) {
		this.list = list;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public String getAttrValueIds() {
		return attrValueIds;
	}

	public void setAttrValueIds(String attrValueIds) {
		this.attrValueIds = attrValueIds;
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

	public SearchPojo getSearchPojo() {
		return searchPojo;
	}

	public void setSearchPojo(SearchPojo searchPojo) {
		this.searchPojo = searchPojo;
	}

}
