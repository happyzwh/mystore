package com.mystore.business.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constans;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserCart;
import com.mystore.business.pojo.CacheCart;
import com.mystore.business.pojo.Goods;
import com.mystore.business.pojo.ShopCart;
import com.mystore.business.pojo.SynCart;
import com.mystore.business.service.ProductService;
import com.mystore.business.service.UserCartService;
import com.mystore.business.util.CookieUtil;

@Controller("cartAction")
@Scope("prototype")
public class UserCartAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	private Integer proId;
	
	private Integer count;
	
	private ShopCart shopCart;
	
	private String cart;
	
	public void initCart(){
		
		if(StringUtils.isBlank(cart))return;
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		CacheCart cacheCart = (CacheCart)redisTemplate.opsForValue().get(Constans.KEY_COOKIE_CART+"_"+sessionId);
		if(cacheCart != null)return;
		
		cacheCart = new CacheCart(true);
		
		String[] goodsList = cart.split(Constans.CHAR_SPLIT_CART);
		if(goodsList != null && goodsList.length > 0){
			Map<Integer,Integer> cart = new HashMap<Integer,Integer>();
			cacheCart.setCart(cart);
			for(String goodstr:goodsList){
				String[] goods = goodstr.split(Constans.CHAR_SPLIT_CART_GOOD);
				cart.put(Integer.valueOf(goods[0]), Integer.valueOf(goods[1]));
			}
		}
		
		redisTemplate.delete(Constans.KEY_COOKIE_CART+"_"+sessionId);
		redisTemplate.opsForValue().set(Constans.KEY_COOKIE_CART+"_"+sessionId,cacheCart);
		redisTemplate.expire(Constans.KEY_COOKIE_CART+"_"+sessionId, Constans.VALUE_TIME_COOKIE_CART, TimeUnit.HOURS);
		
		User user = (User)redisTemplate.opsForValue().get(Constans.KEY_SESSION+"_"+sessionId);
		if(user != null){
			SynCart synCart = (SynCart)redisTemplate.opsForHash().get(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()));
			if(synCart != null){
				synCart.setCart(cart.toString());
				synCart.setCount(synCart.getCount()+1);
			}else{
				synCart = new SynCart();
				synCart.setId_user(user.getId());
				synCart.setCart(cart.toString());
				synCart.setCount(1);
				synCart.setTime(System.currentTimeMillis());
			}
			
			redisTemplate.opsForHash().put(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()),synCart);
			
		}
	}
	
	public void coverCart(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		
		CacheCart cacheCart = (CacheCart)redisTemplate.opsForValue().get(Constans.KEY_COOKIE_CART+"_"+sessionId);
		
		cacheCart = new CacheCart(true);
		
		String[] goodsList = cart.split(Constans.CHAR_SPLIT_CART);
		if(goodsList != null && goodsList.length > 0){
			Map<Integer,Integer> cart = new HashMap<Integer,Integer>();
			cacheCart.setCart(cart);
			for(String goodstr:goodsList){
				String[] goods = goodstr.split(Constans.CHAR_SPLIT_CART_GOOD);
				cart.put(Integer.valueOf(goods[0]), Integer.valueOf(goods[1]));
			}
		}
		
		redisTemplate.delete(Constans.KEY_COOKIE_CART+"_"+sessionId);
		redisTemplate.opsForValue().set(Constans.KEY_COOKIE_CART+"_"+sessionId,cacheCart);
		redisTemplate.expire(Constans.KEY_COOKIE_CART+"_"+sessionId, Constans.VALUE_TIME_COOKIE_CART, TimeUnit.HOURS);
	
		User user = (User)redisTemplate.opsForValue().get(Constans.KEY_SESSION+"_"+sessionId);
		if(user != null){
			SynCart synCart = (SynCart)redisTemplate.opsForHash().get(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()));
			if(synCart != null){
				synCart.setCart(cart.toString());
				synCart.setCount(synCart.getCount()+1);
			}else{
				synCart = new SynCart();
				synCart.setId_user(user.getId());
				synCart.setCart(cart.toString());
				synCart.setCount(1);
				synCart.setTime(System.currentTimeMillis());
			}
			
			redisTemplate.opsForHash().put(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()),synCart);
			
		}
		
		CookieUtil.editCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(),Constans.KEY_COOKIE_CART,cart.toString(),Constans.VALUE_TIME_COOKIE_CART.intValue()*60*60);
	}
	
	public void addCart() throws IOException, JSONException{
		int code = 1;
		JSONObject data = new JSONObject();
		
		try{
			if(proId == null || count ==null){
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			
			CacheCart cacheCart = (CacheCart)redisTemplate.opsForValue().get(Constans.KEY_COOKIE_CART+"_"+sessionId);
			
			cacheCart = (cacheCart == null)?new CacheCart(true):cacheCart;
			
			cacheCart.setChanged(true);
			
			int num = 0;
			if(cacheCart.getCart().containsKey(proId)){
				num = cacheCart.getCart().get(proId)+count;	
			}
			
			if(num <= 0){
				cacheCart.getCart().remove(proId);
			}else{
				cacheCart.getCart().put(proId,num);
			}
			
			StringBuilder cart = new StringBuilder("");
			
			for(Integer key:cacheCart.getCart().keySet()){
					if(cart.length() > 0){
						cart.append(Constans.CHAR_SPLIT_CART);
					}
					cart.append(key).append(Constans.CHAR_SPLIT_CART_GOOD).append(cacheCart.getCart().get(key));
			} 
			redisTemplate.delete(Constans.KEY_COOKIE_CART+"_"+sessionId);
			redisTemplate.opsForValue().set(Constans.KEY_COOKIE_CART+"_"+sessionId,cacheCart);
			redisTemplate.expire(Constans.KEY_COOKIE_CART+"_"+sessionId, Constans.VALUE_TIME_COOKIE_CART, TimeUnit.HOURS);
			
			User user = (User)redisTemplate.opsForValue().get(Constans.KEY_SESSION+"_"+sessionId);
			if(user != null){
				SynCart synCart = (SynCart)redisTemplate.opsForHash().get(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()));
				if(synCart != null){
					synCart.setCart(cart.toString());
					synCart.setCount(synCart.getCount()+1);
				}else{
					synCart = new SynCart();
					synCart.setId_user(user.getId());
					synCart.setCart(cart.toString());
					synCart.setCount(1);
					synCart.setTime(System.currentTimeMillis());
				}
				
				redisTemplate.opsForHash().put(Constans.KEY_CART_SYN_USER, String.valueOf(user.getId()),synCart);
				
			}
			
			CookieUtil.editCookie(ServletActionContext.getRequest(),ServletActionContext.getResponse(),Constans.KEY_COOKIE_CART,cart.toString(),Constans.VALUE_TIME_COOKIE_CART.intValue()*60*60);
		
			data.put("count",cacheCart.getTotalCount());
			
		}catch(Exception e){
			code = -1;
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			data.put("code", code);
			response.getWriter().print(data.toString());
		}	
	}

	public void cartSyn(){

		List<Object> list = (List<Object>)redisTemplate.opsForHash().values(Constans.KEY_CART_SYN_USER);
		if(list != null && list.size() > 0){
			for(Object o:list){
				SynCart synCart = (SynCart)o;
				if(synCart.getCount() >= Constans.COUNT_CART_SYN_USER || System.currentTimeMillis() <= synCart.getTime()+Constans.VALUE_CART_SYN_USER*60*1000){
					
					UserCart userCart = userCartService.getCartByUserId(synCart.getId_user());
					if(userCart == null){
						userCart = new UserCart();
					}
					
					userCart.setCart(synCart.getCart());
						
					if(userCart.getId() != null){
						userCartService.updateCartByUserId(userCart);
					}else{
						userCart.setId_user(synCart.getId_user());
						userCartService.addCart(userCart);
					}
					
					redisTemplate.opsForHash().delete(Constans.KEY_CART_SYN_USER, String.valueOf(synCart.getId_user()));
				}
			}
		}
	}
	
	public String myCart(){
		
		shopCart = new ShopCart();
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		
		CacheCart cacheCart = (CacheCart)redisTemplate.opsForValue().get(Constans.KEY_COOKIE_CART+"_"+sessionId);
		
		if(cacheCart != null && cacheCart.getCart() != null && !cacheCart.getCart().isEmpty()){
			
			for(Integer key:cacheCart.getCart().keySet()){
					
				Goods goods = new Goods();
				
				Product product = productService.getProById(key);
				
				goods.setId(product.getId());
				goods.setCount(cacheCart.getCart().get(key));
				goods.setName(product.getName());
				goods.setPath_img(product.getPath_img());
				
				ProPrice price = productService.getProPriceByProId(key);
				goods.setMarkPrice(price.getMarkPrice());
				goods.setPrice(price.getShopPrice());
				
				shopCart.getGoodsList().add(goods);
			}

		}
			
		return "myCart";
	}
	
	
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public ShopCart getShopCart() {
		return shopCart;
	}

	public void setShopCart(ShopCart shopCart) {
		this.shopCart = shopCart;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}
	
}
