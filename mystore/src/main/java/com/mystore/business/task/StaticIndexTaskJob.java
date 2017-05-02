package com.mystore.business.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.util.HtmlGenerator;
import com.mystore.business.util.PropertiesUtil;

@Controller("staticIndexTaskJob")
@Scope("prototype")
public class StaticIndexTaskJob {
	
	/** 
	* 要调度的具体任务
	*/  
	public void executeInternal(){
		
		PropertiesUtil p = new PropertiesUtil("config.properties");
    	String staticPath = p.readProperty("staticPath");
    	
		//正式环境请到constants中修改 静态文件生成路径undeal
		List<String> urlList=new ArrayList<String>();
		//urlList.add(Constants.HOST+"/index.html");//主页
		urlList.add(Constants.LOCALHOST+"/top_top.dhtml");//top
		urlList.add(Constants.LOCALHOST+"/index_index.dhtml");//index
		//urlList.add(Constants.HOST+"/invest_index.html");//我要投资
		
		HtmlGenerator h = new HtmlGenerator();
		
		for(String url:urlList)
		{
			String fileName=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
			h.createHtmlPage(url,staticPath+fileName+".html");
		}
	}
	
}  