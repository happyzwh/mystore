package com.mystore.business.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * 增加job监听用于服务器关闭时停止job线程
 * 
 * */
public class JobListener implements ServletContextListener{
	
	private static Logger logger = Logger.getLogger(JobListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		 ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		 Scheduler job = (Scheduler)context.getBean("quartzScheduler");
		 logger.info("Stop the job scheduler bean \"quartzScheduler\":"+job+"!");
		 try {
			   if(job.isStarted()){
				     job.shutdown();
				     Thread.sleep(1000);
			   }
		  } catch (SchedulerException e) {
			  logger.error("Stop the job scheduler bean \"quartzScheduler\":"+job+" failure!");
		  } catch (InterruptedException e) {
			  logger.error("Stop the job scheduler bean \"quartzScheduler\":"+job+" failure!");
		  }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
