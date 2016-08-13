/*     */ package com.mystore.business.captcha.image.service;
/*     */ 
/*     */ /*     */ import java.util.Locale;

/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;

import com.octo.captcha.Captcha;
/*     */ import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.captchastore.CaptchaStore;
/*     */ /**
 * 			解决ajax 检验验证码不友好的情况
 * @author wangfd@date： 日期：2014-8-7 时间：下午4:45:30
 */
/*     */ public abstract class AbstractCaptchaService
/*     */   implements CaptchaService
/*     */ {
/*     */   protected CaptchaStore store;
/*     */   protected CaptchaEngine engine;
/*     */   protected Log logger;
/*     */ 
/*     */   protected AbstractCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine)
/*     */   {
/*  35 */     if ((captchaEngine == null) || (captchaStore == null))
/*  36 */       throw new IllegalArgumentException("Store or gimpy can't be null");
/*  37 */     this.engine = captchaEngine;
/*  38 */     this.store = captchaStore;
/*     */ 
/*  40 */     this.logger = LogFactory.getLog(getClass());
/*     */ 
/*  42 */     this.logger.info("Init " + this.store.getClass().getName());
/*  43 */     this.store.initAndStart();
/*     */   }
/*     */ 
/*     */   public Object getChallengeForID(String ID)
/*     */     throws CaptchaServiceException
/*     */   {
/*  55 */     return getChallengeForID(ID, Locale.getDefault());
/*     */   }
/*     */ 
/*     */   public Object getChallengeForID(String ID, Locale locale)
/*     */     throws CaptchaServiceException
/*     */   {
/*     */     Captcha captcha;
/*  71 */     if (!this.store.hasCaptcha(ID))
/*     */     {
/*  73 */       captcha = generateAndStoreCaptcha(locale, ID);
/*     */     }
/*     */     else {
/*  76 */       captcha = this.store.getCaptcha(ID);
/*  77 */       if (captcha == null) {
/*  78 */         captcha = generateAndStoreCaptcha(locale, ID);
/*     */       }
/*  81 */       else if (captcha.hasGetChalengeBeenCalled().booleanValue())
/*     */       {
/*  83 */         captcha = generateAndStoreCaptcha(locale, ID);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  88 */     Object challenge = getChallengeClone(captcha);
/*  89 */     captcha.disposeChallenge();
/*     */ 
/*  91 */     return challenge;
/*     */   }
/*     */ 
/*     */   public String getQuestionForID(String ID, Locale locale)
/*     */     throws CaptchaServiceException
/*     */   {
/*     */     Captcha captcha;
/* 106 */     if (!this.store.hasCaptcha(ID))
/*     */     {
/* 108 */       captcha = generateAndStoreCaptcha(locale, ID);
/*     */     } else {
/* 110 */       captcha = this.store.getCaptcha(ID);
/* 111 */       if (captcha == null) {
/* 112 */         captcha = generateAndStoreCaptcha(locale, ID);
/* 113 */       } else if (locale != null) {
/* 114 */         Locale storedlocale = this.store.getLocale(ID);
/* 115 */         if (!locale.equals(storedlocale)) {
/* 116 */           captcha = generateAndStoreCaptcha(locale, ID);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 121 */     return captcha.getQuestion();
/*     */   }
/*     */ 
/*     */   public String getQuestionForID(String ID)
/*     */     throws CaptchaServiceException
/*     */   {
/* 132 */     return getQuestionForID(ID, Locale.getDefault());
/*     */   }
/*     */ 
/*     */   public Boolean validateResponseForID(String ID, Object response)
/*     */     throws CaptchaServiceException
/*     */   {
/* 145 */     if (!this.store.hasCaptcha(ID)) {
/* 146 */       throw new CaptchaServiceException("Invalid ID, could not validate unexisting or already validated captcha");
/*     */     }
/* 148 */     Boolean valid = this.store.getCaptcha(ID).validateResponse(response);
			  this.store.removeCaptcha(ID);
			  if(this.store.getSize() > 1000){
				   this.store.empty();
			  }
/* 150 */     return valid;
/*     */   }
/*     */ 
/*     */   protected Captcha generateAndStoreCaptcha(Locale locale, String ID)
/*     */   {
/* 156 */     Captcha captcha = this.engine.getNextCaptcha(locale);
/* 157 */     this.store.storeCaptcha(ID, captcha, locale);
/* 158 */     return captcha;
/*     */   }
/*     */ 
/*     */   protected abstract Object getChallengeClone(Captcha paramCaptcha);
/*     */ }

/* Location:           F:\work\workspace\ebank\src\main\webapp\WEB-INF\lib\jcaptcha-1.0-all.jar
 * Qualified Name:     com.octo.captcha.service.AbstractCaptchaService
 * JD-Core Version:    0.6.0
 */