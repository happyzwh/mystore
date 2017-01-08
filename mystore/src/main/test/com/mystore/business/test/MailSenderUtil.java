package com.mystore.business.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mystore.business.service.MailSenderService;

public class MailSenderUtil {

    public static void main(String[] args) throws Exception{  
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:*.xml");  
        MailSenderService mailSenderService = (MailSenderService) ac.getBean("mailSenderService");  
        
        List<String> receiver = new ArrayList<String>();
        receiver.add("happyzhangwenhui@163.com");
        receiver.add("459976146@qq.com");
        receiver.add("2847317191@qq.com");
        
        mailSenderService.send(receiver, "新年快乐", "新年快乐 新年快乐 新年快乐 新年快乐 新年快乐 新年快乐 新年快乐");
        
        
    }  
}
