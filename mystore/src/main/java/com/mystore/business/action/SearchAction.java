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

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	
	
	

}
