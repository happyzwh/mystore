/*    */ package com.mystore.business.captcha.image.gimpy;
/*    */ 
/*    */ import com.octo.captcha.image.ImageCaptcha;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Gimpy
/*    */   extends ImageCaptcha
/*    */   implements Serializable
/*    */ {
/*    */   private String response;
/*    */   
/*    */   Gimpy(String question, BufferedImage challenge, String response)
/*    */   {
/* 27 */     super(question, challenge);
/* 28 */     this.response = response;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public final Boolean validateResponse(Object response)
/*    */   {
/* 38 */     return (null != response) && ((response instanceof String)) ? validateResponse((String)response) : Boolean.FALSE;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private final Boolean validateResponse(String response)
/*    */   {
/* 48 */     return Boolean.valueOf(response.equals(this.response));
/*    */   }
public String getResponse(){
	return response;
}
/*    */ }

/* Location:           D:\apache-maven-3.0.4\repo\com\jyd\ebank\jcaptcha-all\1.0\jcaptcha-all-1.0.jar
 * Qualified Name:     com.octo.captcha.image.gimpy.Gimpy
 * Java Class Version: 1.3 (47.0)
 * JD-Core Version:    0.7.1
 */