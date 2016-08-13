package com.mystore.business.jcaptcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.mystore.business.captcha.image.gimpy.Gimpy;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class CaptchaService extends DefaultManageableImageCaptchaService {
	
	private Boolean autoTest = false;
	
	private String autoTestCode;

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
		Boolean isHuman;

		try {
			//自动化测试
//			if(autoTest && response.toString().equals(autoTestCode)){
//				return  true;
//			}
			//自动化测试
			isHuman = super.validateResponseForID(ID, response);
		} catch (Exception e) {
			isHuman = false;
		}

		return isHuman;
	}
	public String getResponse(String ID) {
		
		Gimpy gimpy = (Gimpy) this.store.getCaptcha(ID);
		return gimpy != null?gimpy.getResponse():"";
	}

	public Boolean getAutoTest() {
		return autoTest;
	}

	public void setAutoTest(Boolean autoTest) {
		this.autoTest = autoTest;
	}

	public String getAutoTestCode() {
		return autoTestCode;
	}

	public void setAutoTestCode(String autoTestCode) {
		this.autoTestCode = autoTestCode;
	} 
	
}
