<%@ page language="java" import="com.mystore.business.common.ConfigReader,com.mystore.business.pojo.MenuMap" contentType="text/html; charset=UTF-8"%>
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
<title>我的订单</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/order/list.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/user/center.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/common.js" type="text/javascript"></script>
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
<script src="<%=path%>/js/order/list.js" type="text/javascript"></script>
</head>
<body>
  <!--#include file="<%=path%>/page/static/top_top.html" -->
 <div class="homeBody">
     <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.MYORDER.getBh()%></s:param>
      </s:action>
      <div class="list_right">
	    <div class="rightTit">
	     	<h2><i></i>我的订单</h2>
	    </div>
	    <div class="rightCon"> 	    
		    <div class="myordCon"> 	 
			     <ul class="moConHead">
				      <li class="moh1">商品信息</li>
				      <li class="moh2">收货人</li>
				      <li class="moh3">实付金额</li>
				      <li class="moh4">状态</li>
				      <li class="moh5">操作</li>
				      <div class="clear"></div>
			      </ul>
			 	  <div class="moConBox">
			 	   	  <s:iterator value="list" status="ind" id="order">
				   	      <div class="moCon-ord">
			        		 <div class="ord-num">
			        		  	<div class="ord-num-box">
			        		  		<span>订单编号：<a href="<%=path%>/order_detail.dhtml?sn=<s:property value="#order.sn"/>" target="_blank"><s:property value="#order.sn"/></a></span><i>|</i><span class="ord-tim">下单时间：<em><s:date name='createDate' format="yyyy-MM-dd HH:mm:ss" /></em></span>
			        		  	</div>
			        		  	<div class="ord-num-box" style="margin-left:10px;font-size:16px;">
			        		  		¥<s:text name="format.numbers"><s:param value="#order.amount"/></s:text>
			        		  	</div>
								<!--  div class="shopIcon">
									<a target="_blank" href="http://shop.jiuxian.com/index-1255.htm"><i class="shop-icon dsfShop"></i><span></span></a>
								</div -->
								<!--  div class="shopTel">
									<i class="shop-icon"></i><span></span>
								</div -->
								<i title="删除" orderid="109303942" class="rec recdel"></i>  
							 </div>
			        		 <table class="ord-detailTab" cellpadding="0" cellspacing="0" border="0">
			        			<tbody>
			        			    <s:iterator value="#order.orderProudcts" status="inx" id="product">
				        				<tr class= '<s:if test="#inx.last">last</s:if>'>
									        <td class="tdmoh1">
									          <div class="ordPicBox clearfix">
											  	   <a class="proId" value="37836" href='<%=path%>/product_detail.dhtml?id=<s:property value="#product.id_pro" />' target="_blank" title='<s:property value="#product.name" />'>
												 	  <img src='<%=ConfigReader.getPath_pic_service()%>/<s:property value="#product.imgUrl.replace('big','mid')" />' width="50" height="50" alt=""  title='<s:property value="#product.name" />'>
												  </a>
											  </div>
									        </td>
									        <td class="tdmoh2"><s:property value="#order.shipAddress.consignee" /></td>
									        <td class="tdmoh3">
									          	<!-- TODO ¥$formatUtils.scaleAndRoundHalfUpFormat($!item.payPrice,2)-->
												<p class="ord-price">¥<s:text name="format.numbers"><s:param value="#product.buyprice"/></s:text></p>
												<p><s:property value="#order.payLog.payPlatName"/></p>
											</td>
									        <td class="tdmoh4">
												<div class="opePending"><s:property value="#order.statusName"/></div>
										    </td>
									        <td class="tdmoh5 last">
												<p><a class="ope01" target="_blank" href='<%=path%>/product_detail.dhtml?id=<s:property value="#product.id_pro" />'>查看</a></p>
												<!-- p><a class="ope03" target="_blank" href="javascript:void(0);"></a></p -->
											</td>
									      </tr>
								      </s:iterator>
							        </tbody>
							      </table>
							</div>
				 	  </s:iterator>
				  </div>
			 </div>     
		 </div>    
		 <div class="Pagination"> 
			<div style="float:right;">
				${pageInfo}					
			</div>
		</div>
	 </div>
 </div>
 <form id="myForm" action="order_list.dhtml" method="post" target="_self">
 	<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='pageNo'/>" />
 </form>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>