package com.mystore.business.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.business.common.Pager;
import com.mystore.business.dao.OrderInvoiceMapper;
import com.mystore.business.dao.OrderMapper;
import com.mystore.business.dao.OrderProductMapper;
import com.mystore.business.dao.OrderShipAddressMapper;
import com.mystore.business.dao.UserAddressMapper;
import com.mystore.business.dto.Order;
import com.mystore.business.dto.OrderInvoice;
import com.mystore.business.dto.OrderProduct;
import com.mystore.business.dto.OrderShipAddress;
import com.mystore.business.dto.UserAddress;
import com.mystore.business.pojo.Goods;
import com.mystore.business.pojo.OrderCreateType;
import com.mystore.business.pojo.OrderStatus;
import com.mystore.business.pojo.ShopOrder;
import com.mystore.business.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderInvoiceMapper orderInvoiceMapper;
	
	@Autowired
	private OrderProductMapper orderProductMapper;
	
	@Autowired
	private OrderShipAddressMapper orderShipAddressMapper;
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.addOrder(order);
	}

	@Override
	public int updateOrderBySn(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderBySn(order);
	}

	@Override
	public Order getOrderBySn(String sn) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderBySn(sn);
	}

	@Override
	public Pager<Order> getOrderByUserId(Order order,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageNum != null && pageNum == 0)pageNum =1;
		if(pageSize != null && pageSize == 0)pageSize = 10;
		if(pageNum != null && pageSize != null){
			int pageIndex = (pageNum - 1)*pageSize;
			order.setPageIndex(pageIndex);
			order.setPageSize(pageSize);
		}
		Pager<Order> pager = new Pager<Order>();
		if(pageNum != null && pageSize != null){
			pager.setPageNo(pageNum);
			pager.setPageSize(pageSize);
		}
		int count = orderMapper.getOrderCountByUserId(order);
		if(count == 0)return null;
		pager.setRowCount(count);
		if(pageNum != null && pageSize != null){
			pager.setPageCount(count%pageSize==0?(count/pageSize):(count/pageSize+1));
		}
		List<Order> list = orderMapper.getOrderByUserId(order);
		pager.setResultList(list);
		return pager;
	}

	@Override
	public Order saveOrder(Integer userId,ShopOrder shopOrder){
		
		Order order = new Order();
		order.setAmount(new BigDecimal(shopOrder.getTotalAmount()));
		order.setAmount_disc(new BigDecimal(0));
		order.setAmount_paid(new BigDecimal(0));
		order.setAmount_payable(new BigDecimal(shopOrder.getTotalAmount()).subtract(new BigDecimal(shopOrder.getFare())));
		order.setAmount_return(new BigDecimal(0));
		order.setCreatetype(OrderCreateType.USERPCORDER.getValue());
		order.setFare(new BigDecimal(shopOrder.getFare()));
		order.setId_user(userId);
		order.setSn(UUID.randomUUID().toString().replace("-", ""));
		order.setIsdelivery("0");
		order.setIspaid("0");
		order.setPayWay(shopOrder.getPayWay());
		order.setRepstatus("1");
		order.setSource("0");
		order.setStatus(OrderStatus.UNCONFIRM.getValue());
		
		orderMapper.addOrder(order);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_user", userId);
		map.put("id", shopOrder.getAddreId());
		UserAddress userAddress = userAddressMapper.getById(map);
		OrderShipAddress orderShipAddress = new OrderShipAddress();
		orderShipAddress.setAddress(userAddress.getProvinceName()+userAddress.getCityName()+userAddress.getCityName()+userAddress.getAddress());
		orderShipAddress.setConsignee(userAddress.getReceiver());
		orderShipAddress.setId_order(order.getId());
		orderShipAddress.setMobile(userAddress.getMobile());
		orderShipAddress.setStatus("1");
		
		orderShipAddressMapper.addOrderShipAddress(orderShipAddress);
		
		for(Goods product:shopOrder.getGoodsList()){
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setAmount(product.getTotalPrice());
			orderProduct.setBuyprice(product.getPrice());
			orderProduct.setId_order(order.getId());
			orderProduct.setId_pro(product.getId());
			orderProduct.setIsgived("0");
			orderProduct.setMarkprice(product.getMarkPrice());
			orderProduct.setName(product.getName());
			orderProduct.setNumber(product.getCount());
			orderProduct.setSn(product.getSn());
			orderProduct.setStatus("1");
			
			orderProductMapper.addOrderProduct(orderProduct);
		}
		
		if(StringUtils.isNotBlank(shopOrder.getIsInv()) && shopOrder.getIsInv().equals("1")){
			OrderInvoice  orderInvoice = new OrderInvoice();
			orderInvoice.setId_order(order.getId());
			orderInvoice.setInvoicecontext(shopOrder.getInvCon());
			orderInvoice.setInvoicetop(shopOrder.getInvTop());
			orderInvoice.setInvoicetype(shopOrder.getInvType());
			orderInvoice.setInvocetoptype(shopOrder.getInvToptype());
			orderInvoice.setStatus("1");
			
			orderInvoiceMapper.addOrderInvoice(orderInvoice);
		}
		return order;
		
	}
}
