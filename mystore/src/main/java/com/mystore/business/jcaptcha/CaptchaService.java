package com.mystore.business.jcaptcha;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import com.mystore.business.common.Constants;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.image.gimpy.Gimpy;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class CaptchaService extends DefaultManageableImageCaptchaService {
	
	private RedisTemplate<String, Serializable> redisTemplate;
	
	public CaptchaService() {
		super();
	}

	/**
	 * @param minSeconds
	 * @param maxStoreSize
	 *            最大缓存大小
	 * @param loadBefore
	 */
	public CaptchaService(int minSeconds, int maxStoreSize, int loadBefore) {
		super(minSeconds, maxStoreSize, loadBefore);
	}

	public CaptchaService(CaptchaStore captchaStore,
			CaptchaEngine captchaEngine, int minSeconds, int maxStroreSize,
			int loadBefore) {
		super(captchaStore, captchaEngine, minSeconds, maxStroreSize,
				loadBefore);
	}

	/**
	 * 重写验证方法 掩盖异常 当出现异常时判定为验证失败
	 */
	@Override
	public Boolean validateResponseForID(String ID, Object response) {
		Boolean isHuman = false;

		try {
			String code = (String)redisTemplate.opsForHash().get(Constants.KEY_VERIFYCODE, ID);
			if(response != null && StringUtils.isNotBlank(code) && ((String)response).equals(code)){
				isHuman = true;
			}
			redisTemplate.opsForHash().delete(Constants.KEY_VERIFYCODE, ID);
		} catch (Exception e) {
			isHuman = false;
		}

		return isHuman;
	}
	public String getResponse(String ID) {
		
		Gimpy gimpy = (Gimpy) this.store.getCaptcha(ID);
		return gimpy != null?gimpy.getResponse():"";
	}

	public RedisTemplate<String, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	} 
	
}
