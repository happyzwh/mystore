package com.mystore.business.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.core.PublicKeyMap;
import com.mystore.business.core.RSAUtils;
import com.mystore.business.dto.User;
import com.mystore.business.jcaptcha.CaptchaServiceSingleton;
import com.mystore.business.service.UserService;
import com.mystore.business.util.MD5;

@Controller("registerAction")
@Scope("prototype")
public class RegisterAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	private Map<String, Object> model;
	
	private String userName;

	private  String password;
	
	private String verifyCode;
	
	public String goRegister() throws Exception{
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		return "goRegister";
		
	}
	
	public void register() throws IOException{
		
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)){
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession(true).getId();
			
			if(!CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, verifyCode)){
				code = -3;
				return;
			}
			
			password = RSAUtils.decryptStringByJs(password);
			password = new MD5().GetMD5Code(password);
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			
			if(userService.addUser(user)==0){
				code = -1;
			}
			
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
