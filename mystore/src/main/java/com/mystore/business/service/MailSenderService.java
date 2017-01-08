package com.mystore.business.service;

import java.util.List;

public interface MailSenderService {
	
	public void send(String recipient,String subject,String content);
	
	public void send(List<String> recipients,String subject,String content);

}
