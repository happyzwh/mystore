package com.mystore.business.action;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserAddress;
import com.mystore.business.pojo.Goods;
import com.mystore.business.pojo.ShopOrder;
import com.mystore.business.service.ProductService;
import com.mystore.business.service.UserAddressService;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837343756407683168L;
	
	@Autowired
	private UserAddressService userAddressService;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private ProductService productService;
	
	private List<UserAddress> address;
	
	private String orderGoods;
	
	private ShopOrder shopOrder;
	
	public String order(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
		address = userAddressService.getByUserId(user.getId());
		
		String orderStr[] = orderGoods.split("_");
		
		shopOrder = new ShopOrder();
		
		for(String s:orderStr){
			
			Goods goods = new Goods();
			
			String[] goodstr = s.split(",");
			
			Product product = productService.getProById(Integer.valueOf(goodstr[0]));

			goods.setId(product.getId());
			goods.setCount(Integer.valueOf(goodstr[1]));
			goods.setName(product.getName());
			goods.setPath_img(product.getPath_img());
			
			ProPrice price = productService.getProPriceByProId(Integer.valueOf(goodstr[0]));
			goods.setMarkPrice(price.getMarkPrice());
			goods.setPrice(price.getShopPrice());
			
			shopOrder.getGoodsList().add(goods);
			
		}
		
		return "order";
	}

	public List<UserAddress> getAddress() {
		return address;
	}

	public void setAddress(List<UserAddress> address) {
		this.address = address;
	}

	public String getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(String orderGoods) {
		this.orderGoods = orderGoods;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}
	
}
