package com.mystore.business.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

public class TokenTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
        
		try{
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
		    
			String token = UUID.randomUUID().toString().replace("-", "");
			token = token.substring(0,5);
			token = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+token;
			
			RedisTemplate<String, Serializable> redisTemplate = (RedisTemplate<String, Serializable>)ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
			
			redisTemplate.opsForHash().put(Constants.KEY_TOKEN_SESSION,sessionId,token);
			redisTemplate.expire(Constants.KEY_TOKEN_SESSION, Constants.VALUE_TIME_TOKEN_SESSION, TimeUnit.MINUTES);
			
			String content = "<input type='hidden' name='token' value='"+token+"'/>";
			
			this.pageContext.getOut().println(content);  
		 
		}catch(Exception e){
			
		}
		
	    return SKIP_BODY;
	}

}
