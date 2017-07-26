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
<title>订单详情</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/order/detail.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/common.js" type="text/javascript"></script>
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
<script src="<%=path%>/js/order/detail.js" type="text/javascript"></script>
</head>
<body>
  <!--#include file="<%=path%>/page/static/top_top.html" -->
  <div class="homeBody">
  	 <div class="user clearfix">
	    <div class="uBread"><a href="/index.htm"><b>我的哼哈乐</b></a><i>&gt;</i><a href="<%=path%>/order_list.dhtml">交易管理</a><i>&gt;</i><a href="<%=path%>/order_list.dhtml">我的订单</a><i>&gt;</i><em>订单详情</em></div>
	    <!-- div class="userOrderPro">重要提示：<span>请拨打客服电话：</span></div -->
	    <!-- 订单状态begin -->
	    <div class="zpOrderSum">
	      <div class="zpTopHalf clearfix">
	        <p class="zpTopHalf-l"><s:property value="order.sn"/></p>
	        <p class="zpTopHalf-l">状态：<strong style="display:none;"><s:property value="order.sn"/></strong>
				<span><s:property value="order.statusName"/>，<s:property value="order.deliveryName"/>，  <s:property value="order.payName"/></span>
			</p>
	        <p class="zpTopHalf-r" id="zpTopHalf-r"></p>
	      </div>
		  <!-- div class="zpBottomHalf">
			  尊敬的客户，我们还未收到该订单的款项，请您尽快付款（<a href="http://help.jiuxian.com/view-4-121.htm#pay_2" target="_blank">在线支付帮助</a>）该订单会为您保留24小时（从下单之日算起），24小时之后如果还未付款，系统将自动取消该订单。</div>
		  </div -->
	    <!-- 订单状态end -->
	    <!-- 进度条begin -->
	    <div class="zpProcess">
	      <div class="ProcessPic">
			    <div class="ProcessPic2 step1"></div>
		  </div>
	      <div class="Processtext clearfix">
			    <span class="step1">提交订单</span>
	            <span class="step2">正在配货</span>
	            <span class="step3">配送在途</span>
	            <span class="step4">完成</span>
			      </div>
	    </div>
	    <!-- 进度条end -->
	    <!-- 订单跟踪信息begin -->
	    <div class="zpDelivery">
	      <div class="zpHeadlineMain">订单跟踪信息
		  	  </div>
	      <div class="zpHeadlineSub clearfix">
	        <p>处理时间</p>
	        <p>订单跟踪信息</p>
	      </div>
	      <div class="zpContent">
			<!--		-->
						<div class="zpTimeline clearfix">
	              <p class="time">2017-07-21 00:14:20</p>
	              <p>您的订单已提交，等待系统审核</p>
	            </div>
						<div class="zpTimeline clearfix">
	              <p class="time">2017-07-22 00:15:57</p>
	              <p>您的订单已取消</p>
	            </div>
			      </div>
	    </div>
	    <!-- 订单跟踪信息end -->
	    <!-- 订单信息begin -->
	    <div class="zpOrderInfo">
	      <div class="zpHeadlineMain">订单信息 </div>
	      <div class="zpHeadlineSub clearfix">收货人信息</div>
	      <div class="zpContent">
	        <p>收件人：<s:property value="order.shipAddress.consignee" /></p>
	        <p>联系电话：<s:property value='order.shipAddress.mobile.replaceAll("^(\\\\d{3})(\\\\d{4})(\\\\d{4})$","$1****$3")'/></p>
	        <p>收件人地址：<s:property value="order.shipAddress.address" /></p>
	      </div>
	      <div class="zpHeadlineSub clearfix">支付及配送方式</div>
	      <div class="zpContent">
	        <p>支付方式：<s:property value="order.payLog.payPlatName"/></p>
	      </div>
	      <div class="zpHeadlineSub clearfix">发票信息</div>
	      <div class="zpContent">
	        <p>发票类型： 
	           <s:if test="order.orderInvoice.invoicetype == '1'">纸质发票</s:if>
	           <s:elseif test="order.orderInvoice.invoicetype == '2'">电子发票</s:elseif>
	        </p>
	        <p>发票抬头： <s:property value="order.invoicetype.invoicetop"/> </p>
	        <p>发票内容： 
	           <s:if test="order.orderInvoice.invoicecontext == '1'">明细</s:if>
	           <s:elseif test="order.orderInvoice.invoicecontext == '2'">办公用品</s:elseif>
	           <s:elseif test="order.orderInvoice.invoicecontext == '3'">电脑配件</s:elseif>
	           <s:elseif test="order.orderInvoice.invoicecontext == '4'">耗材</s:elseif>
	         </p>
	      </div>
	      <div class="zpHeadlineSub clearfix">商品清单</div>
	      <div class="zpContent zpProList">
	        <table cellpadding="0" cellspacing="0">
	          <tbody>
			    <tr class="firstRow">
	              <td width="170">商品编号</td>
	              <td width="425">商品名称</td>
	              <td width="110">单价</td>
	              <td width="100">商品数量</td>
	              <td width="100">总价</td>
	            </tr>
	            <s:iterator value="order.orderProudcts" status="inx" id="product">
		            <tr class="detialRow">
		                <td><s:property value="#product.sn" /></td>
						  <td class="clearfix">
		                        <a class="tableImg" href='<%=path%>/product_detail.dhtml?id=<s:property value="#product.id_pro" />' title='<s:property value="#product.name" />' target="_blank"><img src="<%=ConfigReader.getPath_pic_service()%>/<s:property value="#product.imgUrl.replace('big','mid')" />" width="54px" height="54px" alt=""></a>
		                        <div class="frameIE6"><a class="oneNameRow tableText" href='<%=path%>/product_detail.dhtml?id=<s:property value="#product.id_pro" />' title='<s:property value="#product.name" />' target="_blank"><s:property value="#product.name" /></a></div>
		                    </td>
						<td><p class="zpPri">￥<s:text name="format.numbers"><s:param value="#product.buyprice"/></s:text></p></td>
						<td><s:property value="#product.number" /></td>
						<td>￥<s:text name="format.numbers"><s:param value="#product.amount"/></s:text></td>
		            </tr>	
	            </s:iterator>		
			  </tbody>
	        </table>
	      </div>
	      <div class="cashier">
	        <div class="cashierTopHalf clearfix">
	          <ul>
				  <li class="cashierItem"><span class="cashierItemName">+商品总金额：</span><em>￥<s:text name="format.numbers"><s:param value="order.amount"/></s:text></em></li>
				  <li class="cashierItem"><span class="cashierItemName">-余            额：</span><em>￥<s:text name="format.numbers"><s:param value="order.amount_balance_pay"/></s:text></em></li>
				  <li class="cashierItem"><span class="cashierItemName">+运            费：</span><em>￥<s:text name="format.numbers"><s:param value="order.fare"/></s:text></em></li>
	          </ul>
	        </div>
	        <div class="cashierBottomHalf clearfix">
	          <p class="cashierItemGoPayAround"></p>
	          <p class="cashierItemAmount">￥<s:text name="format.numbers"><s:param value="order.amount_payable"/></s:text></p><p class="cashierItemName">应付总额：</p>
	        </div>
	      </div>
	    </div>
	    <!-- 订单信息end -->
	  </div>
  </div>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>