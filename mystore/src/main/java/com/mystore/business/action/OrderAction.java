package com.mystore.business.action;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserAddress;
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
	
	private List<UserAddress> address;
	
	public String order(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
		address = userAddressService.getByUserId(user.getId());
		
		
		
		
		return "order";
	}

	public List<UserAddress> getAddress() {
		return address;
	}

	public void setAddress(List<UserAddress> address) {
		this.address = address;
	}
	

}
