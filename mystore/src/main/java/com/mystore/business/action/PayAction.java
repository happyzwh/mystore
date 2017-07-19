package com.mystore.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mystore.business.dto.Order;
import com.mystore.business.service.OrderService;

@Controller("payAction")
@Scope("prototype")
public class PayAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrderService orderService;
	
	private Integer id;
	
	private String sn;
	
	private Order order;

	public String pay(){
		
		order = orderService.getOrderBySn(sn);
		
		
		
		
		return "pay";
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
}
