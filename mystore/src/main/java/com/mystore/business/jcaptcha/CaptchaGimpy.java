package com.mystore.business.jcaptcha;

import com.octo.captcha.image.ImageCaptcha;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class CaptchaGimpy extends ImageCaptcha implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String response;

	CaptchaGimpy(String question, BufferedImage challenge, String response) {
		super(question, challenge);
		this.response = response;
	}

	public final Boolean validateResponse(Object response) {
		return (null != response) && ((response instanceof String)) ? validateResponse((String) response) : Boolean.FALSE;
	}

	private final Boolean validateResponse(String response) {
		return Boolean.valueOf((response.toLowerCase()).equals(this.response.toLowerCase()));
	}
}
