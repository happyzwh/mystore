<%@ page language="java" import="com.mystore.business.common.ConfigReader" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
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
    </div>	
 </div>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>