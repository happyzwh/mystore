package com.mystore.business.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mystore.business.dto.Order;
import com.mystore.business.dto.PayLog;
import com.mystore.business.pay.AlipayConfig;
import com.mystore.business.pojo.PayPlat;
import com.mystore.business.pojo.PayStatus;
import com.mystore.business.pojo.RetStatusPay;
import com.mystore.business.service.OrderService;
import com.mystore.business.service.PayService;

@Controller("payAction")
@Scope("prototype")
public class PayAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(PayAction.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AlipayConfig alipayConfig;
	
	@Autowired
	private PayService payService;
	
	private Integer id;
	
	private String sn;
	
	private String tradeNo;
	
	private String amountTrade;
	
	private Order order;
	
	private String payChannel;
	
	private String payStatus;

	public String pay(){
		
		order = orderService.getOrderBySn(sn);
		
		return "pay";
		
	}

	public void submit()  throws IOException{
		
		order = orderService.getOrderBySn(sn);
		
		if(payChannel.equals(PayPlat.ZFB.getValue())){
			alipaySubmit();
		}
		
		
		
	}
	
    private void alipaySubmit() throws IOException{
    	
    	log.info("订单号:"+sn+",支付宝支付请求提交开始");
    	
    	PayLog payLog = new PayLog();
    	
    	try{
    		
	    	payLog.setAmount(order.getAmount_payable());
	    	payLog.setIdOrder(order.getId());
	    	payLog.setPayPlat(PayPlat.ZFB.getValue());
	    	payLog.setSnOrder(order.getSn());
	    	
	    	payService.addPayLog(payLog);
	    	
	    	AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getMerchantPrivateKey(), "json", alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
	    	//设置请求参数
	    	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	    	alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
	    	alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
	    	
	    	JSONObject bizContent = new JSONObject();
	    	bizContent.put("out_trade_no", order.getSn());
	    	bizContent.put("total_amount", order.getAmount_payable());
	    	bizContent.put("subject", order.getSn());
//	    	bizContent.put("timeout_express", "10m");
	    	bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
	    	
	    	alipayRequest.setBizContent(bizContent.toJSONString());
	    	
	    	String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    	log.info("订单号:"+sn+",支付宝支付请求提交成功");
	      
	        payLog.setStatus(PayStatus.SUBMITSUCCESS.getValue());
	        payService.updatePayLogByOrderSn(payLog);
	        
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write(form);//直接将完整的表单html输出到页面
	        response.getWriter().flush();
	        response.getWriter().close();
	        
    	}catch(Exception e){
    		log.info("订单号:"+sn+",支付宝支付请求提交失败");
    		log.error(e.getMessage());
    		payLog.setStatus(PayStatus.SUBMITFAIL.getValue());
    		payService.updatePayLogByOrderSn(payLog);
    	}
        
    	log.info("订单号:"+sn+",支付宝支付请求提交结束");
    	
    }
    
    public String return_zfb() throws UnsupportedEncodingException, AlipayApiException{
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
    	
    	//商户订单号
    	sn = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
    
    	log.info("订单号:"+sn+",支付宝支付同步响应开始");
    	
    	//获取支付宝GET过来反馈信息
    	Map<String,String> params = new HashMap<String,String>();
    	Map<String,String[]> requestParams = request.getParameterMap();
    	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//乱码解决，这段代码在出现乱码时使用
    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
    		params.put(name, valueStr);
    	}
    	
    	log.info("订单号:"+sn+",支付宝支付同步响应数据："+params.toString());
    	
    	boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名

//		//支付宝交易号
//		tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//		//付款金额
//		amountTrade = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
    	
		log.info("订单号:"+sn+",支付宝支付同步响应验签结果:"+signVerified);
		
    	if(!signVerified) {
    		payStatus = RetStatusPay.SIGNERROR.getValue();
    	}else {
    		payStatus = RetStatusPay.SUCCESS.getValue();
    	}

    	log.info("订单号:"+sn+",支付宝支付同步响应结束");
    	
    	return "return_zfb";
    }
	
    public void notify_zfb() throws AlipayApiException, IOException{
    	
    	String retCode = "";
    	
    	try{
    	
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
			//商户订单号
			sn = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			log.info("订单号:"+sn+",支付宝支付异步响应开始");
	    	
	    	//获取支付宝POST过来反馈信息
	    	Map<String,String> params = new HashMap<String,String>();
	    	Map<String,String[]> requestParams = request.getParameterMap();
	    	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    		String name = (String) iter.next();
	    		String[] values = (String[]) requestParams.get(name);
	    		String valueStr = "";
	    		for (int i = 0; i < values.length; i++) {
	    			valueStr = (i == values.length - 1) ? valueStr + values[i]
	    					: valueStr + values[i] + ",";
	    		}
	    		//乱码解决，这段代码在出现乱码时使用
	    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	    		params.put(name, valueStr);
	    	}
	    	
	    	log.info("订单号:"+sn+",支付宝支付异步响应数据："+params.toString());
	    	
	    	//支付宝交易号
    		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
    	
    		//交易状态
    		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
    		
    		String total_amount = request.getParameter("total_amount");
    		
    		String app_id = request.getParameter("app_id");
    		
      		Order order = orderService.getOrderBySn(sn);
    		
    		if(order == null || order.getAmount_payable().compareTo(new BigDecimal(total_amount)) != 0 || !app_id.equals(alipayConfig.getAppId())){
    			log.info("订单号:"+sn+",支付宝支付异步响应验证失败");
    			retCode = "failure";
    			return;
    		}
    		
    		PayLog payLog = payService.getPayLogByOrderSn(sn);
    		
    		if(payLog.getStatus().equals(PayStatus.PAYSUCCESS.getValue()) || payLog.getStatus().equals(PayStatus.PAYFAIL.getValue()) || payLog.getStatus().equals(PayStatus.FINISH.getValue())){
    			log.info("订单号:"+sn+",支付宝支付异步响应验证失败,重复处理");
    			retCode = "failure";
    			return;
    		}
	    	
	    	boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.alipayPublicKey, alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名
	
	    	//——请在这里编写您的程序（以下代码仅作参考）——
	    	
	    	/* 实际验证过程建议商户务必添加以下校验：
	    	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	    	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	    	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	    	4、验证app_id是否为该商户本身。
	    	*/
	    	if(signVerified) {//验证成功
	    		
	    		log.info("订单号:"+sn+",支付宝支付异步响应验签结果:"+signVerified);
	    		
	    		if(trade_status.equals("TRADE_FINISHED")){
	    			//判断该笔订单是否在商户网站中已经做过处理
	    			//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
	    			//如果有做过处理，不执行商户的业务程序
	    			payStatus = PayStatus.FINISH.getValue();
	    			//注意：
	    			//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
	    		}else if (trade_status.equals("TRADE_SUCCESS")){
	    			//判断该笔订单是否在商户网站中已经做过处理
	    			//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
	    			//如果有做过处理，不执行商户的业务程序
	    			payStatus = PayStatus.PAYSUCCESS.getValue();
	    			//注意：
	    			//付款完成后，支付宝系统发送该交易状态通知
	    		}
	    		
	        	payService.doPay(sn, trade_no, payStatus);
	    		
	    		retCode = "success";
	    	}else {//验证失败
	    		retCode = "failure";
	    		payStatus = PayStatus.PAYFAIL.getValue();
//	    		调试用，写文本函数记录程序运行情况是否正常
//	    		String sWord = AlipaySignature.getSignCheckContentV1(params);
//	    		AlipayConfig.logResult(sWord);
	    	}
	    	
    	}catch(Exception e){
    		retCode = "failure";
    		log.info("订单号:"+sn+",支付宝支付异步响应失败");
    	}finally{
    		ServletActionContext.getResponse().getWriter().println(retCode);
    	}

    	log.info("订单号:"+sn+",支付宝支付异步响应结束");
    	
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

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAmountTrade() {
		return amountTrade;
	}

	public void setAmountTrade(String amountTrade) {
		this.amountTrade = amountTrade;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
