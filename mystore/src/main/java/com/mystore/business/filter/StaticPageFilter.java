package com.mystore.business.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mystore.business.common.Constants;
import com.mystore.business.util.HtmlGenerator;
import com.mystore.business.util.PropertiesUtil;

public class StaticPageFilter implements Filter{
	
    private final static String FILTER_ISFILETER = "isFilter";
    private static boolean isFilter = false;
    private final static String FILTER_URL_INCLUDE="includeUrl";
    private static String[] includeURLs;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 String inUrlStr = filterConfig.getInitParameter(FILTER_URL_INCLUDE);
		 includeURLs = StringUtils.split(inUrlStr, ",");
		 isFilter = Boolean.valueOf(filterConfig.getInitParameter(FILTER_ISFILETER));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;
		
		 if(!isFilter){
			  chain.doFilter(req,res); 
	   		  return;
	   	 }
		
		 String url = req.getRequestURI().replaceAll("^(/mystore)(.*)", "$2");
	    
		 boolean flag = false;
	     
	     if (includeURLs != null && includeURLs.length > 0) {
	            for(String includeURL : includeURLs) {
	                if (url.contains(includeURL.trim())) {
	                    flag = true;
	                    break;
	                }
	            }
	     }
	     
	     if(!flag){
	        	chain.doFilter(req,res); 
	        	return;
	     }
	     
	     try{
		     String newUrl = url.replaceAll("(.*)\\.dhtml\\?id=(\\d+)", "$1_$2\\.dhtml").replaceAll("dhtml", "html");
		     
		     if(!new File(newUrl).exists()){
			     PropertiesUtil p = new PropertiesUtil("config.properties");
			     String staticPath = p.readProperty("staticPath");
			     HtmlGenerator h = new HtmlGenerator();
			     h.createHtmlPage(Constants.LOCALHOST+url,staticPath+newUrl);
		     }
		     
		     res.sendRedirect(req.getContextPath() +"/"+newUrl); 
	     }catch(Exception e){
	    	 chain.doFilter(req,res); 
	     }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
