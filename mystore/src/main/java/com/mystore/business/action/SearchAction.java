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
			keys = "0-0-0-0-0-0-0-0";
		}
		
		String[] key = keys.split("-");
		
		if(key.length < 8){
			keys = "0-0-0-0-0-0-0-0";
		}
		
		try{
			
			cateId = Integer.valueOf(key[0]);
			Integer brandId = Integer.valueOf(key[1]);
			
			if(brandId != 0){
				brandIds = brandId.toString();
			}
			
			Integer id_country = Integer.valueOf(key[2]);
			Integer id_province = Integer.valueOf(key[3]);
			
			double lowPrice = Double.valueOf(key[4]);
			double highPrice = Double.valueOf(key[5]);
			
			Integer orderType = Integer.valueOf(key[6]);
			Integer asc = Integer.valueOf(key[7]);
			
			Pager<SearchProPoJo> pager =  searchService.search(cateId, brandId, id_country, id_province, lowPrice, highPrice, orderType, asc, pageNo, pageSize);
			
			if(pager != null && pager.getResultList() != null && pager.getResultList().size() > 0){
				  list = pager.getResultList();
				  pageInfo = PageInfo.setPage(pager.getPageNo(), pager.getPageSize(), pager.getPageCount(), pager.getRowCount());
			}
			
			searchPojo = new SearchPojo();
			searchPojo.setCateId(cateId);
			searchPojo.setBrandIds(new ArrayList<Integer>(brandId));
			searchPojo.setLowPrice(lowPrice);
			searchPojo.setHighPrice(highPrice);
			searchPojo.setOrderType(orderType);
			searchPojo.setAsc(asc);
			
			searchPojo.setSelectedCates(categoryService.getAllParentCategoryById(cateId));
			
			searchPojo.setSelectCates(categoryService.getAllSonCategoryById(cateId));
			
			if(StringUtils.isNotBlank(brandIds)){
				brandIds=","+brandIds+",";
				String[] ids = brandIds.split(",");
				searchPojo.setSelectedBrands(new ArrayList<Brand>());
				for(String s:ids){
					if(StringUtils.isNotBlank(s)){
						searchPojo.getSelectedBrands().add(brandService.getBrandById(Integer.valueOf(s)));
					}
				}
			}
			
			List<Brand> allBrandlist = brandService.selectAllBrandListByCateId(cateId);
			if(StringUtils.isNotBlank(brandIds) && allBrandlist != null && allBrandlist.size() > 0){
				for(Brand brand:allBrandlist){
					if(brandIds.indexOf(","+String.valueOf(brand.getId())+",") != -1){
						brand.setChecked(true);
					}
				}
			}
			
			searchPojo.setSelectBrands(allBrandlist);
			
			List<Map<String,Object>> allAttrValue = productService.getProAttrMapByCateId(cateId);
			
			if(allAttrValue != null && allAttrValue.size() > 0){
				
				Map<String, String> selectCateAttrNames = new HashMap<String, String>();
				searchPojo.setSelectCateAttrNames(selectCateAttrNames);
				searchPojo.setSelectCateAttrIds(new ArrayList<String>());
				
				for(Map<String,Object> map:allAttrValue){
					searchPojo.getSelectCateAttrNames().put(map.get("baid").toString(), map.get("name").toString());
					searchPojo.getSelectCateAttrIds().add(map.get("baid").toString());
				}
			}
			
			if(allAttrValue != null && allAttrValue.size() > 0){
				
				List<Map<String,String>> selectedCateAttrNames = new ArrayList<Map<String,String>>();
				Map<String,List<Map<String,Object>>> selectCateAttrs = new HashMap<String,List<Map<String,Object>>>();
				
				searchPojo.setSelectedCateAttrNames(selectedCateAttrNames);
				searchPojo.setSelectCateAttrs(selectCateAttrs);
				
				for(Map<String,Object> map:allAttrValue){
					
					String baid = map.get("baid").toString();
					String name = map.get("name").toString();
					String[] vids = map.get("vid").toString().split(",");
					String[] values = map.get("value").toString().split(",");
					
					for(int i=0;i<vids.length;i++){
						String vid = vids[i];
						String value = values[i];
						
						if(!searchPojo.getSelectCateAttrs().containsKey(baid)){
							searchPojo.getSelectCateAttrs().put(baid, new ArrayList<Map<String,Object>>());
						}
							
						Map<String,Object> selectAttrMap = new HashMap<String,Object>();
						searchPojo.getSelectCateAttrs().get(baid).add(selectAttrMap);
							
						selectAttrMap.put("vid", vid);
						selectAttrMap.put("value", value);
						
						if(StringUtils.isNotBlank(attrValueIds) && attrValueIds.indexOf(","+vid+",") != -1){
							Map<String,String> seledAttrMap = new HashMap<String,String>();
							searchPojo.getSelectedCateAttrNames().add(seledAttrMap);
							seledAttrMap.put("baid", baid);
							seledAttrMap.put("name", name);
							seledAttrMap.put("vid", vid);
							seledAttrMap.put("value", value);
							
							selectAttrMap.put("checked", true);
						}	
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "list";
	}

	public String lists(){
		
		try{
			
			searchPojo = new SearchPojo();
			searchPojo.setCateId(cateId);
			searchPojo.setLowPrice(lowPrice);
			searchPojo.setHighPrice(highPrice);
			searchPojo.setOrderType(orderType);
			searchPojo.setAsc(asc);
			
			if(StringUtils.isNotBlank(brandIds)){
				List<Integer> brands = new ArrayList<Integer>();
				String[] ids = brandIds.split(",");
				for(String s:ids){
					if(StringUtils.isNotBlank(s)){
						brands.add(Integer.valueOf(s));
					}
				}
				searchPojo.setBrandIds(brands);
			}
			
			if(StringUtils.isNotBlank(attrValueIds)){
				List<Integer> attrValues = new ArrayList<Integer>();
				String[] ids = attrValueIds.split(",");
				for(String s:ids){
					if(StringUtils.isNotBlank(s)){
						attrValues.add(Integer.valueOf(s));
					}
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
					if(StringUtils.isNotBlank(s)){
						searchPojo.getSelectedBrands().add(brandService.getBrandById(Integer.valueOf(s)));
					}
				}
			}
			
			List<Brand> allBrandlist = brandService.selectAllBrandListByCateId(cateId);
			if(StringUtils.isNotBlank(brandIds) && allBrandlist != null && allBrandlist.size() > 0){
				for(Brand brand:allBrandlist){
					if(brandIds.indexOf(","+String.valueOf(brand.getId())+",") != -1){
						brand.setChecked(true);
					}
				}
			}
			
			searchPojo.setSelectBrands(allBrandlist);
			
			List<Map<String,Object>> allAttrValue = productService.getProAttrMapByCateId(cateId);
			
			if(allAttrValue != null && allAttrValue.size() > 0){
				
				Map<String, String> selectCateAttrNames = new HashMap<String, String>();
				searchPojo.setSelectCateAttrNames(selectCateAttrNames);
				
				for(Map<String,Object> map:allAttrValue){
					searchPojo.getSelectCateAttrNames().put(map.get("baid").toString(), map.get("name").toString());
				}
			}
			
			if(allAttrValue != null && allAttrValue.size() > 0){
				
				List<Map<String,String>> selectedCateAttrNames = new ArrayList<Map<String,String>>();
				Map<String,List<Map<String,Object>>> selectCateAttrs = new HashMap<String,List<Map<String,Object>>>();
				
				searchPojo.setSelectedCateAttrNames(selectedCateAttrNames);
				searchPojo.setSelectCateAttrs(selectCateAttrs);
				
				searchPojo.setSelectCateAttrIds(new ArrayList<String>());
				
				for(Map<String,Object> map:allAttrValue){
					
					String baid = map.get("baid").toString();
					String name = map.get("name").toString();
					String[] vids = map.get("vid").toString().split(",");
					String[] values = map.get("value").toString().split(",");
					
					searchPojo.getSelectCateAttrIds().add(baid);
					
					for(int i=0;i<vids.length;i++){
						String vid = vids[i];
						String value = values[i];
						
						if(!searchPojo.getSelectCateAttrs().containsKey(baid)){
							searchPojo.getSelectCateAttrs().put(baid, new ArrayList<Map<String,Object>>());
						}
							
						Map<String,Object> selectAttrMap = new HashMap<String,Object>();
						searchPojo.getSelectCateAttrs().get(baid).add(selectAttrMap);
							
						selectAttrMap.put("vid", vid);
						selectAttrMap.put("value", value);
						
						if(StringUtils.isNotBlank(attrValueIds) && attrValueIds.indexOf(","+vid+",") != -1){
							Map<String,String> seledAttrMap = new HashMap<String,String>();
							searchPojo.getSelectedCateAttrNames().add(seledAttrMap);
							seledAttrMap.put("baid", baid);
							seledAttrMap.put("name", name);
							seledAttrMap.put("vid", vid);
							seledAttrMap.put("value", value);
							
							selectAttrMap.put("checked", true);
						}	
					}
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
