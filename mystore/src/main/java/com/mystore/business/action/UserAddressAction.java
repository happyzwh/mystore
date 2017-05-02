package com.mystore.business.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserAddress;
import com.mystore.business.service.UserAddressService;

@Controller("userAddressAction")
@Scope("prototype")
public class UserAddressAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Autowired
	private UserAddressService userAddressService;
	
	private List<UserAddress> addList;
	
	private UserAddress address;
	
	private Integer id;
	
	private String mobile;
	
	private String addre;
	
	private String receiver;
	
	public String index(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
		addList = userAddressService.getByUserId(user.getId());
		
		if(id != null){
			address = userAddressService.getById(user.getId(), id);
		}
		
		return "index";
	}
	
	public void edit() throws IOException{
		
		int code = 1;
		
		try{
			
			if(StringUtils.isBlank(addre) || StringUtils.isBlank(mobile) || StringUtils.isBlank(receiver)) {
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
			
			UserAddress address = new UserAddress();
			if(id != null){
				address =  userAddressService.getById(user.getId(), id);
			}
			address.setAddress(addre);
			address.setId_user(user.getId());
			address.setMobile(mobile);
			address.setReceiver(receiver);
			if(id != null){
				userAddressService.updateAddress(address);
			}else{
				userAddressService.saveAddress(address);
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
	
	public void delete() throws IOException{
		
		int code = 1;
		
		try{
			
			if(id == null) {
				code = -2;
				return;
			}
			
			String sessionId = ServletActionContext.getRequest().getSession().getId();
			User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
			
		
		   userAddressService.delAddress(user.getId(), id);
		
		}catch(Exception e){
			code = -1;
			e.printStackTrace();
		}finally{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(code);
		}
		
	}

	public List<UserAddress> getAddList() {
		return addList;
	}

	public void setAddList(List<UserAddress> addList) {
		this.addList = addList;
	}

	public UserAddress getAddress() {
		return address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddre() {
		return addre;
	}

	public void setAddre(String addre) {
		this.addre = addre;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
}
