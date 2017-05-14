<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
</head>
<body>
<div class="list_left">
      		<h1>
	      		<a href="user_center.dhtml">
	      			<i></i><span>我的哼哈乐</span>
	      		</a>
      		</h1>
      		<div class="userInfo">
      		    <div class="headImg"><a href="javascrpt:void(0);"><img src="http://localhost:8080/picService/upload/pro/img/8/20161022114304_small.jpg"/></a></div>
      		    <div class="userName"><s:property value='user.userName'/></div>
      		</div>
      		<div class="navList">
      		   <div class="func func1">
      		       <p class="title"><i></i><span>交易管理</span></p>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@MYORDER.bh'>on</s:if>" href="/trademanage/my_order-9.htm" title=""><span>我的订单</span><i></i></a>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@BACKLOG.bh'>on</s:if>" href="/trademanage/my_order-9.htm" title=""><span>退货记录</span><i></i></a>
      		   </div>
      		   <div class="func func2">
      		       <p class="title"><i></i><span>我的钱包</span></p>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@MYACCOUNT.bh'>on</s:if>" href="/trademanage/my_order-9.htm" title=""><span>我的余额</span><i></i></a>
      		   </div>
      		   <div class="func func3">
      		       <p class="title"><i></i><span>我的帐户</span></p>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@SECURITY.bh'>on</s:if>" href="<%=path%>/security_index.dhtml" title=""><span>帐户安全</span><i></i></a>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@BASICINFO.bh'>on</s:if>" href="/trademanage/my_order-9.htm" title=""><span>基本信息</span><i></i></a>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@ADDRESS.bh'>on</s:if>" href="<%=path%>/address_index.dhtml" title=""><span>收货地址</span><i></i></a>
      		       <a class="item <s:if test='bh==@com.mystore.business.pojo.MenuMap@QUCKPAY.bh'>on</s:if>" href="/trademanage/my_order-9.htm" title=""><span>快捷支付</span><i></i></a>
      		   </div>
      		</div>
      </div>
	</body>
</html>