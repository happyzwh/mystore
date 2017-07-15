package com.mystore.business.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.mystore.business.common.Constants;
import com.mystore.business.dto.Order;
import com.mystore.business.dto.OrderInvoice;
import com.mystore.business.dto.OrderProduct;
import com.mystore.business.dto.OrderShipAddress;
import com.mystore.business.dto.ProPrice;
import com.mystore.business.dto.Product;
import com.mystore.business.dto.User;
import com.mystore.business.dto.UserAddress;
import com.mystore.business.pojo.Goods;
import com.mystore.business.pojo.OrderCreateType;
import com.mystore.business.pojo.OrderStatus;
import com.mystore.business.pojo.ShopOrder;
import com.mystore.business.service.OrderInvoiceService;
import com.mystore.business.service.OrderProductService;
import com.mystore.business.service.OrderService;
import com.mystore.business.service.OrderShipAddressService;
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
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@Autowired
	private OrderInvoiceService orderInvoiceService;
	
	@Autowired
	private OrderShipAddressService orderShipAddressService;
	
	private List<UserAddress> address;
	
	private String orderGoods;
	
	private ShopOrder shopOrder;
	
	private Integer addreId;
	
	private String payWay;
	
	private String isInv;
	
	private String invType;
	
	private String invToptype;
	
	private String invTop;
	
	private String invCon;
	
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

	public String save(){
		
		String sessionId = ServletActionContext.getRequest().getSession().getId();
		User user = (User)redisTemplate.opsForValue().get(Constants.KEY_SESSION+"_"+sessionId);
		
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
			goods.setSn(product.getSn());
			
			ProPrice price = productService.getProPriceByProId(Integer.valueOf(goodstr[0]));
			goods.setMarkPrice(price.getMarkPrice());
			goods.setPrice(price.getShopPrice());
			
			shopOrder.getGoodsList().add(goods);
			
		}
		
		
		Order order = new Order();
		order.setAmount(new BigDecimal(shopOrder.getTotalAmount()));
		order.setAmount_disc(new BigDecimal(0));
		order.setAmount_paid(new BigDecimal(0));
		order.setAmount_payable(new BigDecimal(shopOrder.getTotalAmount()).subtract(new BigDecimal(shopOrder.getFare())));
		order.setAmount_return(new BigDecimal(0));
		order.setCreatetype(OrderCreateType.USERPCORDER.getValue());
		order.setFare(new BigDecimal(shopOrder.getFare()));
		order.setId_user(user.getId());
		order.setSn(UUID.randomUUID().toString());
		order.setIsdelivery("0");
		order.setIspaid("0");
		order.setPayWay(payWay);
		order.setRepstatus("1");
		order.setSource("0");
		order.setStatus(OrderStatus.UNCONFIRM.getValue());
		
		orderService.addOrder(order);
		
		UserAddress userAddress = userAddressService.getById(user.getId(), addreId);
		OrderShipAddress orderShipAddress = new OrderShipAddress();
		orderShipAddress.setAddress(userAddress.getProvinceName()+userAddress.getCityName()+userAddress.getCityName()+userAddress.getAddress());
		orderShipAddress.setConsignee(userAddress.getReceiver());
		orderShipAddress.setId_order(order.getId());
		orderShipAddress.setMobile(userAddress.getMobile());
		orderShipAddress.setStatus("1");
		
		orderShipAddressService.addOrderShipAddress(orderShipAddress);
		
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
			
			orderProductService.addOrderProduct(orderProduct);
		}
		
		if(StringUtils.isNotBlank(isInv) && isInv.equals("1")){
			OrderInvoice  orderInvoice = new OrderInvoice();
			orderInvoice.setId_order(order.getId());
			orderInvoice.setInvoicecontext(invCon);
			orderInvoice.setInvoicetop(invTop);
			orderInvoice.setInvoicetype(invType);
			orderInvoice.setInvocetoptype(invToptype);
			orderInvoice.setStatus("1");
			
			orderInvoiceService.addOrderInvoice(orderInvoice);
		}
		
		return order();
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

	public Integer getAddreId() {
		return addreId;
	}

	public void setAddreId(Integer addreId) {
		this.addreId = addreId;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getIsInv() {
		return isInv;
	}

	public void setIsInv(String isInv) {
		this.isInv = isInv;
	}

	public String getInvType() {
		return invType;
	}

	public void setInvType(String invType) {
		this.invType = invType;
	}

	public String getInvToptype() {
		return invToptype;
	}

	public void setInvToptype(String invToptype) {
		this.invToptype = invToptype;
	}

	public String getInvTop() {
		return invTop;
	}

	public void setInvTop(String invTop) {
		this.invTop = invTop;
	}

	public String getInvCon() {
		return invCon;
	}

	public void setInvCon(String invCon) {
		this.invCon = invCon;
	}
	
}
