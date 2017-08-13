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
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true"/>
      <div class="list_right">
     	  <div class="account">
     	    <!--第一行账户余额等4个block begin-->
			<div class="money">
				<ul class="clearfix">
					<li class="first">
						<div class="title">
							<i></i><span>余额</span>
						</div>
						<p><a href="account_index.dhtml" class="_capitalAccount">¥<s:text name="format.numbers"><s:param value="account.amount"/></s:text></a></p>
					</li>
					<!-- li class="second">
						<div class="title">
							<i></i><span>返现</span>
						</div>
						<p><a href="/purse/my_surplus-2.htm" class="_backAccount">¥0.00</a></p>
					</li>
					<li class="third">
						<div class="title">
							<i></i><span>金币</span>
						</div>
						<p><a id="myGoldCoin" href="/purse/my_gold_coin-1.htm" class="_goldAccount">0</a></p>
					</li>
					<li class="fourth">
						<div class="title">
							<i></i><span>优惠券</span>
						</div>
						<p><a href="/purse/my_coupon-11.htm" class="_couponNums">0</a></p>
					</li -->
				</ul>
			</div>
			<!--第一行账户余额等4个block end-->
			<!--第二行账户安全 begin-->
			<div class="security clearfix">
				<div class="improve">
					<span>账户安全：</span>
						   	  <s:if test="level == @com.mystore.business.pojo.SecurityLevelMap@HIGH.bh"> 
					  	  	  	  	<strong class="high">高</strong>
									<i class="high"><em></em></i>
					  	  	  </s:if>
					  	  	  <s:elseif test="level == @com.mystore.business.pojo.SecurityLevelMap@MIDDLE.bh"> 
					  	  	  	  	<strong class="middle">中</strong>
									<i class="middle"><em></em></i>
					  	  	  </s:elseif>
					  	  	  <s:else> 
					  	  	  	   	<strong class="low">低</strong>
									<i class="low"><em></em></i>
					  	  	  </s:else>
				</div>
				<div class="yzHome clearfix">
					<div class="phone yzType">
						<i class="titIcon"></i>
						<span class="yzTit">手机：</span>
						<a class="res" href="javascript:;">
							<s:if test='user.isMobileValid != "1" '>
								<i class="wyz"></i>未验证
							</s:if>
							<s:else>
								<i></i>已验证
							</s:else>
						</a>
						<div class="edit clearfix" style="display:none;">
							   <s:if test='user.isMobileValid != "1" '>
							       <span>未验证</span><a href="<%=path%>/page/user/mailedit.jsp" title="修改手机">验证</a>
							   </s:if>
							   <s:else>
									<span><s:property value='mobile.replaceAll("^(.)(.+)(.@.+)$","$1****$3")'/></span><a href="<%=path%>/page/user/mailedit.jsp" title="修改手机">修改</a>
							   </s:else>
						</div>
					</div>
					<div class="mail yzType">
						<i class="titIcon"></i>
						<span class="yzTit">邮箱：</span>
						<a class="res" href="javascript:;">
							<s:if test='user.isEmailValid != "1" '>
								<i class="wyz"></i>未验证
							</s:if>
							<s:else>
								<i></i>已验证
							</s:else>
						</a>
						<div class="edit clearfix" style="display:none;">
							   <s:if test='user.isEmailValid != "1" '>
							       <span>未验证</span><a href="<%=path%>/page/user/mailedit.jsp" title="修改邮箱">验证</a>
							   </s:if>
							   <s:else>
									<span><s:property value='user.email.replaceAll("^(.)(.+)(.@.+)$","$1****$3")'/></span><a href="<%=path%>/page/user/mailedit.jsp" title="修改邮箱">修改</a>
							   </s:else>
						</div>
					</div>
				</div>
			</div>
			<!--第二行账户安全 end-->
			<!-- 第三行最近订单 begin -->
			<!--  div class="middle order">
				<div class="line clearfix">
					<div class="title">
						<i></i><span>最近订单</span>
					</div>
					<div class="status">
						<ul>
							<li><a href="/trademanage/my_order-0.htm?status=0" title="待付款订单">待付款<span id="uc_dfkdd">0</span></a><i></i></li>
							<li><a href="/trademanage/my_order-6.htm?status=6" title="待收货订单">待收货<span id="uc_dshdd">0</span></a><i></i></li>
							<li><a href="/jxcircle/listComment.htm" title="待评价商品">待评价<span id="uc_dpjsp">0</span></a></li>
						</ul>
					</div>
					<a class="more" href="order_list.dhtml" title="查看全部订单">查看全部订单 &gt;</a>
				</div>		
				<div class="proListEmpty emptyFrame clearfix">
					<i></i><span>您买的东西太少了，这里空空的，快去挑选合适的商品吧！</span>
				</div>
			</div -->
			<!-- 第三行最近订单 end -->
		</div>
      </div>
      <div style="clear:both;"></div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>