package com.mystore.business.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.mystore.business.common.Constants;

/**
 * 静态页面引擎技术（突乱了乱码问题UTF-8）
 * @author
 *
 */
public class HtmlGenerator {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	HttpClient httpClient = null; //HttpClient实例
	GetMethod getMethod =null; //GetMethod实例
	BufferedWriter fw = null;
	String page = null;
	String webappname = null;
	BufferedReader br = null;
	InputStream in = null;
	StringBuffer sb = null;
	String line = null;
	String realpath = null;
	
	//构造方法
	public HtmlGenerator(){
		this.webappname = /*Constants.HOST+*/"/static";
	}
	
	/** 根据模版及参数产生静态页面 */
	public boolean createHtmlPage(String url,String htmlFileName){
		
		boolean status = false;	
		int statusCode = 0;	
		try{
			//创建一个HttpClient实例充当模拟浏览器
			httpClient = new HttpClient();
			//设置httpclient读取内容时使用的字符集
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");			
			//创建GET方法的实例
			getMethod = new GetMethod(url);
			//使用系统提供的默认的恢复策略，在发生异常时候将自动重试3次
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			//设置Get方法提交参数时使用的字符集,以支持中文参数的正常传递
			getMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
			//执行Get方法并取得返回状态码，200表示正常，其它代码为异常
			statusCode = httpClient.executeMethod(getMethod);
			//log.info(statusCode);
			if (statusCode!=200) {
				log.info("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错!");
			}else{
				//读取解析结果
				sb = new StringBuffer();
				in = getMethod.getResponseBodyAsStream();
				//br = new BufferedReader(new InputStreamReader(in));//此方法默认会乱码，经过长时期的摸索，下面的方法才可以
				br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
				while((line=br.readLine())!=null){
					sb.append(line+"\n");
				}
				if(br!=null)br.close();
				page = sb.toString();
				
				if (page.indexOf("404页") > -1) {
					status = false;
					return status;
				}
				
				//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
				page = formatPage(page);
				//将解析结果写入指定的静态HTML文件中，实现静态HTML生成
				writeHtml(htmlFileName,page);
				status = true;
			}			
		}catch(Exception ex){	
			log.info("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错:"+ex.getMessage());
        }finally{
        	//释放http连接
        	getMethod.releaseConnection();
        }
		return status;
	}
	
	//将解析结果写入指定的静态HTML文件中
	private synchronized void writeHtml(String htmlFileName,String content) throws Exception{
		fw = new BufferedWriter(new FileWriter(htmlFileName));
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8");
		fw.write(page);	
		if(fw!=null)fw.close();		
	}
	
	//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
	private String formatPage(String page){	
		page = page.replaceAll("help_center\\.dhtml\\?id=(\\d+)", "page/static/help_center_$1\\.html");
		page = page.replaceAll("product_detail\\.dhtml\\?id=(\\d+)", "page/static/product_detail_$1\\.html");
		return page;
	}
	
	public List<String> getIndexUrl(){
		
		List<String> urlList=new ArrayList<String>();
		urlList.add(Constants.LOCALHOST+"/index_index.html");
		
		return urlList;//主页
	}
	public List<String> getOtherList(){
		
		List<String> urlList=new ArrayList<String>();
		
		urlList.add(Constants.LOCALHOST+"/about_safetyAbj.html");// 安保金管理方法
		return urlList;
	}
	
	//测试方法
	public static void main(String[] args){		
		String str = "/mystore/index_index.dhtml";
//		System.out.println(str.replaceAll("(.*)\\.dhtml\\?id=(\\d+)", "$1_$2.dhtml").replaceAll("dhtml", "html"));
		
		System.out.println(str.replaceAll("^(/mystore)(.*)", "$2"));
	}

}