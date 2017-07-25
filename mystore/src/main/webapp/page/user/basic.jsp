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
<title>用户中心</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/center.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="<%=path%>/js/user/center.js"></script>
</head>
<body>
 <%@ include file="/page/static/top_top.html" %>
 <div class="homeBody">
      <div class="list_left">
      		<h1>
	      		<a href="<%=path%>/user_center.dhtml">
	      			<i></i><span>我</span>
	      		</a>
      		</h1>
      		<div class="userInfo">
      		    <div class="headImg"><a href="javascrpt:void(0);"><img src="http://localhost:8080/picService/upload/pro/img/8/20161022114304_small.jpg"/></a></div>
      		    <div class="userName">zwhzwh</div>
      		</div>
      		<div class="navList">
      		   <div class="func func1">
      		       <p class="title"><i></i><span>交易管理</span></p>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>我的订单</span><i></i></a>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>退货记录</span><i></i></a>
      		   </div>
      		   <div class="func func2">
      		       <p class="title"><i></i><span>我的钱包</span></p>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>我的余额</span><i></i></a>
      		   </div>
      		   <div class="func func3">
      		       <p class="title"><i></i><span>我的帐户</span></p>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>帐户安全</span><i></i></a>
      		       <a class="item on" href="/trademanage/my_order-9.htm" title=""><span>基本信息</span><i></i></a>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>收货地址</span><i></i></a>
      		       <a class="item" href="/trademanage/my_order-9.htm" title=""><span>快捷支付</span><i></i></a>
      		   </div>
      		</div>
      </div>
      <div class="list_right">
      </div>
      <div style="clear:both;"></div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>