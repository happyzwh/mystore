package com.mystore.business.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	/**
     * 读取所有cookie
     * 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
     * @param request
     * @param response
     */
    public static void showCookies(HttpServletRequest request,HttpServletResponse response ){
         
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null != cookies){
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }
         
    }
    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    /**
     * 修改cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    public static void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value,int maxAge){
    	Cookie cookie = getCookieByName(request,name);
        if (null != cookie){
             cookie.setValue(value);
//           cookie.setMaxAge(30 * 60);// 设置为30min
             cookie.setMaxAge(maxAge);
             cookie.setPath("/");
             response.addCookie(cookie);
         }else{
        	 addCookie(response,name,value,maxAge);
         }
    }
    /**
     * 删除cookie
     * @param request
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
    	Cookie cookie = getCookieByName(request,name);
        if (null != cookie){
             cookie.setValue(null);
             cookie.setMaxAge(0);
             response.addCookie(cookie);
         }
    }
    
    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
      
      
      
    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
