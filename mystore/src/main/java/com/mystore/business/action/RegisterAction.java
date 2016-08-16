package com.mystore.business.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.jlc.mkt.sms.interfaceapi.service.SmsApiService;
import com.jlc.mkt.sms.pojo.SmsEntranceMap;
import com.mystore.business.core.PublicKeyMap;
import com.mystore.business.core.RSAUtils;

@Controller("registerAction")
@Scope("prototype")
public class RegisterAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SmsApiService smsApiService;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	private Map<String, Object> model;
	
	public String goRegister() throws Exception{
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		smsApiService.smsCheckCode("15011297739", "12345", null, SmsEntranceMap.WEBMOBILEREG);
		
		redisTemplate.opsForValue().set("15011297739", 1);
		
		System.out.println(redisTemplate.opsForValue().get("15011297739"));
		
		return "goRegister";
		
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
