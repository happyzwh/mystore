package com.mystore.business.cache;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.business.service.CacheService;
import com.mystore.business.util.MD5;

@Aspect
@Component
public class CacheAop {
	
	@Autowired 
	private CacheService cacheService;
	
	@Around(value="@annotation(Cache)")
    public Object cached(ProceedingJoinPoint jp) throws Throwable {
	   
		String key = MD5.GetMD5Code(getCacheKey(jp));
		if(!cacheService.exists(key)){
		   	 Method method = ((MethodSignature)jp.getSignature()).getMethod();
		   	 Cache cache = (Cache)(method.getAnnotation(Cache.class)); 
		   	 Object o = jp.proceed();
		   	 if(o != null){
		   		cacheService.setWithExpire(key, (Serializable)o, cache.expire());
		   	 }
		}
		
		return cacheService.get(key);
    }
 
    /**
     * 获取缓存的key值
     * 
     * @param pjp
     * @param cache
     * @return
     */
    public static String getCacheKey(JoinPoint jp) {
 
    	 StringBuilder key = new StringBuilder("");
    	 String className = jp.getTarget().getClass().getName();
    	 key.append(className).append("-");
    	 Method method = ((MethodSignature)jp.getSignature()).getMethod();
         String methodName = method.getName();
         key.append(methodName).append("-");
         Object[] args = jp.getArgs();
         for(Object arg:args){
        	 key.append(arg.getClass().toString()).append(arg.toString()).append("_");
         }
         
    	 return key.toString();
    }
}
