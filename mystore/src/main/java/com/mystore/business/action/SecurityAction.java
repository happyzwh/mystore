package com.mystore.business.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constans;
import com.mystore.business.core.PublicKeyMap;
import com.mystore.business.core.RSAUtils;
import com.mystore.business.dto.User;
import com.mystore.business.jcaptcha.CaptchaServiceSingleton;
import com.mystore.business.pojo.SecurityLevelMap;
import com.mystore.business.service.UserService;
import com.mystore.business.util.MD5;

@Controller("securityAction")
@Scope("prototype")
public class SecurityAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private UserService userService;
	
	private Map<String, Object> model;
	
	private String level;
	
	private User user;
	
	private String oldPwd;
	
	private String pwd;
	
	private String pwdCheck;
	
	public String index(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
	    user = (User)redisTemplate.opsForValue().get(Constans.KEY_SESSION+"_"+sessionId);
		Object o = redisTemplate.opsForHash().get(Constans.KEY_LEVEL_SECURITY, user.getId().toString());
		
		if(o == null){
			
			int low = 0;
			int mid = 0;
			int high = 0;
			int leve = 0;
			
			if(StringUtils.isNotBlank(user.getIsMobileValid()) && user.getIsMobileValid().equals("1")){
				low = 1;
			}
			if(StringUtils.isNotBlank(user.getIsEmailValid()) && user.getIsEmailValid().equals("1")){
				mid = 2;
			}
			if(StringUtils.isNotBlank(user.getPwdPay())){
				high = 4;
			}
			
			leve = low|mid|high;
			
			if(leve == 7){
				level = SecurityLevelMap.HIGH.getBh();
			}else if(leve > 4 || leve == 3){
				level = SecurityLevelMap.MIDDLE.getBh();
			}else{
				level = SecurityLevelMap.LOW.getBh();
			}
			
			redisTemplate.opsForHash().put(Constans.KEY_LEVEL_SECURITY, user.getId().toString(),level);
			redisTemplate.expire(Constans.KEY_LEVEL_SECURITY, Constans.VALUE_TIME_LEVEL_SECURITY, TimeUnit.HOURS);
		}else{
			level = (String)o;
		}
		
		return "index";
	}
	
	public String toUpdPwd(){
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		return "toUpdPwd";
	}

	public void updatePwd() throws IOException{
		
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(oldPwd) || StringUtils.isBlank(pwd) || StringUtils.isBlank(pwdCheck)){
				code = -2;
				return;
			}
			
			if(!pwd.equals(pwdCheck)){
				code = -3;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			user = (User)redisTemplate.opsForValue().get(Constans.KEY_SESSION+"_"+sessionId);
			
			oldPwd = RSAUtils.decryptStringByJs(oldPwd);
			oldPwd = new MD5().GetMD5Code(oldPwd);
			
			if(!oldPwd.equals(user.getPassword())){
				code = -4;
				return;
			}
			
			pwd = RSAUtils.decryptStringByJs(pwd);
			pwd = new MD5().GetMD5Code(pwd);
			
			user = userService.getUserByAccount(user.getUserName());
			user.setPassword(pwd);
			userService.updateUser(user);
			
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
	}
	
	public static void main(String[] args){
		int low = 1;
		int mid = 2;
		int high = 4;
		
		System.out.println(low|mid|high);
		
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdCheck() {
		return pwdCheck;
	}

	public void setPwdCheck(String pwdCheck) {
		this.pwdCheck = pwdCheck;
	}
	
}
