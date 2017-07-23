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
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
<style type="text/css">
.payIcon {
    background: url("../images/public-24.png") no-repeat;
}
.successCon .sucTit i {
    display: inline-block;
    position: absolute;
    left: -46px;
    top: 17px;
    width: 42px;
    height: 42px;
    background-position: -82px -288px;
}
.successWrap .successCon {
    padding: 0 70px 22px 70px;
}
</style>
</head>
 <body>
  <!--#include file="<%=path%>/page/static/top_top.html" -->
 <div class="homeBody">
    <div class="order">
    	<div class="payBox">
			<div class="payMain">
				<div class="successWrap">
					<ul class="successCon">
						<li class="sucTit"><i class="payIcon"></i>页面重复提交或已过期</li>
					</ul>
				</div>
			</div>
		</div>
    </div>	
 </div>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>