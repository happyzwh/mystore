package com.mystore.business.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.service.InfoService;
import com.mystore.business.util.HtmlGenerator;
import com.mystore.business.util.PropertiesUtil;

@Controller("staticOtherTaskJob")
@Scope("prototype")
public class StaticOtherTaskJob {
	
	@Autowired
	private InfoService infoService;
	
	/** 
	* 要调度的具体任务
	*/  
	public void executeInternal(){
		
		PropertiesUtil p = new PropertiesUtil("config.properties");
    	String staticPath = p.readProperty("staticPath");
    	HtmlGenerator h = new HtmlGenerator();
    	
    	List<Integer> infoCateIds = infoService.getAllInfoId();
    	
    	if(infoCateIds != null && infoCateIds.size() > 0){
    		for(Integer id:infoCateIds){
    			h.createHtmlPage(Constants.LOCALHOST+"/help_center.dhtml?id="+id,staticPath+"help_center_"+id+".html");
    		}
    	}
    	
	}
	
}  