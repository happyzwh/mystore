package com.mystore.business.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

import com.mystore.business.common.Constants;

public class LoginFilter implements Filter{
	
	private final static String FILTER_URL_EXCLUDE = "excludeUrl";
    private static String[] excludeURLs;
	private final static String FILTER_URL_LOGIN = "loginUrl";
    private static String loginUrl;
    private final static String FILTER_ISFILETER = "isFilter";
    private static boolean isFilter = false;
    private final static String FILTER_URL_INCLUDE="includeUrl";
    private static String[] includeURLs;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;
		
		 if(!isFilter){
			  chain.doFilter(req,res); 
	   		  return;
	   	 }
		
		 String url = req.getRequestURI();
	    
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
	     
	     flag = false;
	     
	     if (excludeURLs != null && excludeURLs.length > 0) {
	            for(String excludeURL : excludeURLs) {
	                if (url.contains(excludeURL.trim())) {
	                    flag = true;
	                    break;
	                }
	            }
	    }
		
        if(flag){
        	chain.doFilter(req,res); 
        	return;
        }
        
        String sessionId = req.getSession().getId();
        
        RedisTemplate<String, Serializable> redisTemplate = (RedisTemplate<String, Serializable>)ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
        
        Object o = redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);

	    if (o == null){ 
	    	res.sendRedirect(req.getContextPath() +"/"+loginUrl); 
	    }else{ 
	    	chain.doFilter(req,res); 
	    }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 String inUrlStr = filterConfig.getInitParameter(FILTER_URL_INCLUDE);
		 includeURLs = StringUtils.split(inUrlStr, ",");
		 String exUrlStr = filterConfig.getInitParameter(FILTER_URL_EXCLUDE);
		 excludeURLs = StringUtils.split(exUrlStr, ",");
		 loginUrl = filterConfig.getInitParameter(FILTER_URL_LOGIN);
		 isFilter = Boolean.valueOf(filterConfig.getInitParameter(FILTER_ISFILETER));
	}

}
