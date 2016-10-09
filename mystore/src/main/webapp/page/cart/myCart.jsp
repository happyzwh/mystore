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
<title>我的购物车</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/cart/cart.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/cart/cart.js" type="text/javascript"></script>
</head>
<body>
 <s:action name="topAction!top" namespace="/top" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
    <div class="cart">
    	 <div class="title">
    	 	 <div class="column checkBox"><input type="checkbox"  class="checkAll"/>  全选</div>
    	 	 <div class="column goods">商品</div>
    	 	 <div class="column props"></div>
    	 	 <div class="column price">单价(元)</div>
    	 	 <div class="column count">数量</div>
    	 	 <div class="column sum">小计(元)</div>
    	 	 <div class="column op">操作</div>
    	 </div>
    	 <div class="list">
    	 	 <div class="item">
    	 	 	<div class="cell p-checkBox"><input type="checkbox"  class="check"/></div>
    	 	 	<div class="cell p-goods">
    	 	 		<div class="goods-item">
    	 	 		   	<div class="p-img">
    	 	 		   	     <a href="//item.jd.com/10083094124.html" target="_blank"><img alt="朗汇电动自行车成人折叠电动助力车可拆卸锂电池电单车男女同款电动车自行车電動車 蓝-男款助力理论续航约70KM" src="//img10.360buyimg.com/cms/s80x80_jfs/t1876/177/1759077932/165459/8f08279e/56722989Nca8b9095.jpg"></a>
    	 	 		   	</div> 
    	 	 		   	<div class="item-msg">
							<div class="p-name">
								<a href="//item.jd.com/10083094124.html" target="_blank">朗汇电动自行车成人折叠电动助力车可拆卸锂电池电单车男女同款电动车自行车電動車 蓝-男款助力理论续航约70KM</a>
							</div>
						</div>
    	 	 		</div>
    	 	 	</div>
    	 	 	<div class="cell p-props">
    	 	 		<div class="props-txt" title="蓝-男款助力理论续航约70KM">颜色：蓝-男款助力理论续航约70KM</div>
    	 	 	</div>
    	 	 	<div class="cell p-price">
    	 	 		<strong>1696.00</strong>
    	 	 	</div>
    	 	 	<div class="cell p-count">
    	 	 		<div class="count-form">
						<a href="javascript:void(0);" class="decrement">-</a>
						<input autocomplete="off" class="itxt" value="1" type="text">
						<a href="javascript:void(0);" class="increment">+</a>
					</div>
    	 	 	</div>
    	 	 	<div class="cell p-sum">
    	 	 		<strong>1696.00</strong>
    	 	 	</div>
    	 	 	<div class="cell p-op">
    	 	 		<a class="cart-remove" href="javascript:void(0);">删除</a>
    	 	 	</div>
    	 	 	<div style="clear:both;"></div>
    	 	 </div>
    	 	 <div style="clear:both;"></div>
    	 </div>
    	 <div class="toolbar">
    	      <div class="column checkBox"><input type="checkbox"  class="checkAll"/>  全选</div>
    	      <div class="toolbar-right">
							<div class="btn-area">
								<a href="javascript:void(0);" class="submit-btn">去结算<b></b></a>
							</div>
							<div class="price-sum">
								<div>
									<span class="txt txt-new">总价：</span>
									<span class="price sumPrice"><em data-bind="0.00">¥0.00</em></span>
									<br>
									<span class="txt">已节省：</span>
									<span class="price totalRePrice">- ¥0.00</span>
								</div>
							</div>
							<div class="amount-sum">
								已选择<em>0</em>件商品
							</div>
				</div>
    	 </div>
    	 <div style="clear:both;"></div>
    </div>  
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>