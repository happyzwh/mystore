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

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

import com.mystore.business.common.Constants;

public class TokenFilter implements Filter{
	
	private static String errorUrl;
    private final static String ERRORURL="errorUrl";

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;
		 
		 String token = req.getParameter("token");
		 if(StringUtils.isNotBlank(token)){
			 
			 String sessionId = req.getSession().getId();
			 RedisTemplate<String, Serializable> redisTemplate = (RedisTemplate<String, Serializable>)ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
			 
			 if(redisTemplate.opsForHash().hasKey(Constants.KEY_TOKEN_SESSION, sessionId)){
				 redisTemplate.opsForHash().delete(Constants.KEY_TOKEN_SESSION, sessionId);
				 chain.doFilter(req,res); 
			 }else{
				 res.sendRedirect(req.getContextPath()+errorUrl); 
			 }
			 return;
		 }
		 
		chain.doFilter(req,res); 
		 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		errorUrl = filterConfig.getInitParameter(ERRORURL);
	}

}
