<%@ page language="java" import="com.mystore.business.common.ConfigReader" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib uri="/token-tags" prefix="token" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单支付</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/pay/pay.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/order/order.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/common.js" type="text/javascript"></script>
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
<script src="<%=path%>/js/pay/pay.js" type="text/javascript"></script>
</head>
 <body>
  <!--#include file="<%=path%>/page/static/top_top.html" -->
 <div class="homeBody">
    <div class="order">
    	<div class="header">
    		<div id="logo">
    			<a href="#none" class="title"><b>支付页</b></a>
    		</div>
    		<div class="schedule">
			        <dl class="first done">
			            <dt class="s-num">1</dt>
			            <dd class="s-text">1.我的购物车<s></s><b></b></dd>
			        </dl>
			        <dl class="normal done">
			            <dt class="s-num">2</dt>
			            <dd class="s-text">2.填写核对订单信息<s></s><b></b></dd>
			        </dl>
			        <dl class="normal doing">
			            <dt class="s-num">3</dt>
			            <dd class="s-text">3.成功提交订单<s></s><b></b></dd>
			        </dl>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<div class="payBox">
			<div class="payMain">
				<div class="successWrap">
					<ul class="successCon">
						<li class="sucTit"><i class="payIcon"></i>您的订单已提交成功，请尽快付款！</li>
						<li class="sucPro">订单<span>24小时</span>内有效，逾期将被取消！
						</li>
						<li class="sucCon">订单编号：<em>  <a href="order_detail.htm"><s:property value='order.sn'/></a></em>订单金额：<b>￥<s:text name="format.numbers"><s:param value="order.amount_payable"/></s:text></b><span class="sucCon-pro">商品库存保留30分钟，过时无效，请尽快支付<i class="payIcon"></i></span></li>
					</ul>
				</div>
				<div class="payWrap">
					<div class="paymentList">
						<div class="paymenWrap" style="display: block;">
							<ul class="clearfix">
								<li class="Payment payment_1" title="支付宝" value="1"><b class="payIcon p_1"></b></li>
								<li class="Payment payment_2" title="微信支付" value="2"><b class="payIcon p_1"></b></li>
								<li class="Payment payment_4" title="在线支付" value="3"><b class="payIcon p_1"></b></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="payBtnBox">
					<a class="payBtn" href="javascript:void(0);"><i class="payIcon"></i></a>
				</div>
				<div class="payProWrap">
					<h3>注意事项：</h3>
					<p>"订单提交成功"仅表示平台收到您的订单，只有您的订单通过审核后，才代表订单正式生效；</p>
					<p>选择货到付款的客户，请您务必认真检查所有货物，如有不符，您可以拒收；</p>
					<p>选择其他方式的客户，请您认真检查外包装。如有明显损坏迹象，您可以拒收该货品，并及时通知我们。</p>
				</div>
			</div>
		</div>
    </div>	
 </div>
 <form action ="pay_submit.dhtml" id="myform" method="post" target="_self">
	<input type="hidden" name="sn" value='<s:property value="order.sn"/>'/>
	<input type="hidden" name="payChannel" id="payChannel" value=''/>
	<token:token/>
</form>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>