package com.mystore.business.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mystore.business.service.MailSenderService;

@Service("mailSenderService")
public class MailSenderServiceImpl implements MailSenderService{

	 	@Autowired  
	 	private JavaMailSender mailSender;
	 	
	 	@Autowired
	    private SimpleMailMessage simpleMailMessage;  
	      
	    /** 
	     * 单发 
	     * 
	     * @param recipient 收件人 
	     * @param subject 主题 
	     * @param content 内容 
	     */  
	    public void send(String recipient,String subject,String content){  
//	        simpleMailMessage.setTo(recipient);  
//	        simpleMailMessage.setSubject(subject);  
//	        simpleMailMessage.setText(content);  
//	        mailSender.send(simpleMailMessage);  
	       
		    try{
			    MimeMessage mail = mailSender.createMimeMessage();
			    MimeMessageHelper helper = new MimeMessageHelper(mail);  
			    helper.setTo(recipient); 
			    helper.setSubject(subject);  
			    helper.setFrom(simpleMailMessage.getFrom());
			    // 邮件内容，第二个参数指定发送的是HTML格式  
			    helper.setText(content,true);  
			    mailSender.send(mail);  
		    }catch(Exception e){
		    	
		    }
	        
	    }  
	      
	    /** 
	     * 群发 
	     * 
	     * @param recipients 收件人 
	     * @param subject 主题 
	     * @param content 内容 
	     */  
	    public void send(List<String> recipients,String subject,String content){  
	        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));  
	        simpleMailMessage.setSubject(subject);  
	        simpleMailMessage.setText(content);  
	        mailSender.send(simpleMailMessage);  
	    }

}
