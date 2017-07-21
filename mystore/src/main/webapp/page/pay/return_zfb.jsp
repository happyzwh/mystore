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
    	<div class="payBox">
			<div class="payMain">
				<div class="successWrap">
					<ul class="successCon">
						<li class="sucTit"><i class="payIcon"></i><s:if test="payStatus == '1'"></>支付成功</s:if><s:else>支付失败</s:else></li>
						<li class="sucPro">订单<span>24小时</span>内有效，逾期将被取消！
						</li>
						<li class="sucCon">订单编号：<em>  <a href="order_detail.htm"><s:property value='sn'/></a></em>订单金额：<b>￥<s:property value='amountTrade'/></b><span class="sucCon-pro"><i class="payIcon"></i></span></li>
					</ul>
				</div>
			</div>
		</div>
    </div>	
 </div>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>