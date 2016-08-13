package com.mystore.business.jcaptcha;

import org.springframework.web.context.ContextLoader;

import com.octo.captcha.service.image.ImageCaptchaService;

public class CaptchaServiceSingleton {
	private static ImageCaptchaService instance = (ImageCaptchaService)ContextLoader.getCurrentWebApplicationContext().getBean("imageCaptchaService");

	public static ImageCaptchaService getInstance() {
		return instance;
	}

}
