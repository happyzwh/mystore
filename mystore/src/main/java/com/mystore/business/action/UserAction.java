package com.mystore.business.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constans;
import com.mystore.business.core.PublicKeyMap;
import com.mystore.business.core.RSAUtils;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserCart;
import com.mystore.business.jcaptcha.CaptchaServiceSingleton;
import com.mystore.business.pojo.CacheCart;
import com.mystore.business.service.UserCartService;
import com.mystore.business.service.UserService;
import com.mystore.business.util.CookieUtil;
import com.mystore.business.util.MD5;

@Controller("userAction")
@Scope("prototype")
public class UserAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	private Map<String, Object> model;
	
	private Integer id;
	
	private String userName;

	private  String password;
	
	private String verifyCode;
	
	public String goRegister(){
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		return "goRegister";
		
	}
    
	/**
	 * 注册
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @param verifyCode 验证码
	 * 
	 * @return 1 注册成功
	 * @return -1 系统异常
	 * @return -2 参数错误
	 * @return -3 验证码错误
	 * 
	 * */
	public void register() throws IOException{
		
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)){
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession(false).getId();
			
			if(!CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, verifyCode)){
				code = -3;
				return;
			}
			
			password = RSAUtils.decryptStringByJs(password);
			password = new MD5().GetMD5Code(password);
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			
			if(userService.addUser(user)==0){
				code = -1;
				return;
			}
			
			user  = userService.getUserByAccount(userName);
			
			redisTemplate.opsForValue().set(Constans.KEY_SESSION+"_"+sessionId, user);
			redisTemplate.expire(Constans.KEY_VERIFYCODE+"_"+sessionId, Constans.VALUE_TIME_SESSION, TimeUnit.HOURS);
			
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
	}
	
	public String goLogin(){
		
		model = new HashMap<String, Object>();
		
		PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
		
		model.put("exponent", publicKeyMap.getExponent());
		model.put("modulus", publicKeyMap.getModulus());
		
		return "goLogin";
		
	}
	
	/**
	 * 登录
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @param verifyCode 验证码
	 * 
	 * @return 1 注册成功
	 * @return -1 系统异常
	 * @return -2 参数错误
	 * @return -3 验证码错误
	 * @return -4 用户名或密码错误
	 * @return -5 用户暂不可用
	 * 
	 * */
	public void login() throws IOException{
		
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)){
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession(false).getId();
			
			if(!CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, verifyCode)){
				code = -3;
				return;
			}
			
			password = RSAUtils.decryptStringByJs(password);
			password = new MD5().GetMD5Code(password);
			
			User user  = userService.getUserByAccount(userName);
			
			if(user == null || !user.getPassword().equals(password)){
				code = -4;
				return;
			}
			
			if(!user.getStatus().equals("1")){
				code = -5;
			}
			
			redisTemplate.opsForValue().set(Constans.KEY_SESSION+"_"+sessionId, user);
			redisTemplate.expire(Constans.KEY_VERIFYCODE+"_"+sessionId, Constans.VALUE_TIME_SESSION, TimeUnit.HOURS);
			
			CacheCart cacheCart = (CacheCart)redisTemplate.opsForValue().get(Constans.KEY_COOKIE_CART+"_"+sessionId);
			
			cacheCart = (cacheCart == null)?new CacheCart(true):cacheCart;
			
			if(cacheCart.isChanged()){
				UserCart userCart = userCartService.getCartByUserId(user.getId());
				if(userCart != null && StringUtils.isNotBlank(userCart.getCart())){
					String[] carts = userCart.getCart().split(";");
					if(carts != null && carts.length > 0){
						for(String cartStr:carts){
							String[] cart = cartStr.split("|");
							if(cart != null && cart.length == 2){
								if(cacheCart.getCart().containsKey(Integer.valueOf(cart[0]))){
									cacheCart.getCart().put(Integer.valueOf(cart[0]), Integer.valueOf(cart[1])+cacheCart.getCart().get(Integer.valueOf(cart[0])));
								}else{
									cacheCart.getCart().put(Integer.valueOf(cart[0]), Integer.valueOf(cart[1]));
								}
							}
						}
					}
				}
			}
			
			StringBuilder cart = new StringBuilder("");
			if(cacheCart.isChanged() && !cacheCart.getCart().isEmpty()){
				for(Integer key:cacheCart.getCart().keySet()){
					if(!cart.equals("")){
						cart.append(";");
					}
					cart.append(key).append("|").append(cacheCart.getCart().get(key));
				} 
				cacheCart.setChanged(false);
				redisTemplate.opsForValue().set(Constans.KEY_COOKIE_CART+"_"+sessionId,cacheCart);
				redisTemplate.expire(Constans.KEY_COOKIE_CART+"_"+sessionId, Constans.VALUE_TIME_COOKIE_CART, TimeUnit.HOURS);
			}
			
			if(cart.length() > 0){
				
				UserCart userCart = userCartService.getCartByUserId(user.getId());
				if(userCart == null){
					userCart = new UserCart();
				}
				userCart.setCart(cart.toString());
				userCart.setId_user(user.getId());
				if(userCart.getId() != null){
					userCartService.updateCartByUserId(userCart);
				}else{
					userCartService.addCart(userCart);
				}
				
				CookieUtil.editCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(),Constans.KEY_COOKIE_CART,cart.toString(),Constans.VALUE_TIME_COOKIE_CART.intValue()*60*60);
			}
			
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
	}
	
	/**
	 * 验证用户名是否存在
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @param verifyCode 验证码
	 * 
	 * @return 1 已存在
	 * @return -1系统异常
	 * @return -2 参数错误
	 * @return 0  不存在
	 * 
	 * */
	
	public void isAccountExist() throws IOException{
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(userName)){
				code = -2;
				return;
			}
			
			User user = userService.getUserByAccount(userName);
			
			if(user == null){
				code = 0;
				return;
			}
			
			if(id != null && user.getId().intValue() == id.intValue()){
				code = 0;
			}
		
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
	}
	
	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
