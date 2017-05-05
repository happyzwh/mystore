package com.mystore.business.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.service.ProductService;
import com.mystore.business.util.HtmlGenerator;
import com.mystore.business.util.PropertiesUtil;

@Controller("staticProDetailTaskJob")
@Scope("prototype")
public class StaticProDetailTaskJob {
	
	@Autowired
	private ProductService productService;
	
	/** 
	* 要调度的具体任务
	*/  
	public void executeInternal(){
		
		PropertiesUtil p = new PropertiesUtil("config.properties");
    	String staticPath = p.readProperty("staticPath");
    	HtmlGenerator h = new HtmlGenerator();
    	
    	List<Integer> proIds = productService.getAllProId();
    	
    	if(proIds != null && proIds.size() > 0){
    		for(Integer id:proIds){
    			h.createHtmlPage(Constants.LOCALHOST+"/product_detail.dhtml?id="+id,staticPath+"product_detail_"+id+".html");
    		}
    	}
    	
	}
	
}  