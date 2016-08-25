package com.mystore.business.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Pager;
import com.mystore.business.dto.SearchProPoJo;
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
	
	private String keys;
	
	private Pager<SearchProPoJo> pager;
	
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
			
			pager =  searchService.search(id_category, id_brand, id_country, id_province, price_low, price_high, type_sort_attr, type_sort, pageNo, pageSize);
			
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

	public Pager<SearchProPoJo> getPager() {
		return pager;
	}

	public void setPager(Pager<SearchProPoJo> pager) {
		this.pager = pager;
	}

}
