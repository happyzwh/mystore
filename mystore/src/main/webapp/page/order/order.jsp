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
<title>订单结算</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/order/order.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/common.js" type="text/javascript"></script>
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
<script src="<%=path%>/js/region.js" type="text/javascript"></script>
<script src="<%=path%>/js/order/order.js" type="text/javascript"></script>
</head>
<body>
  <!--#include file="<%=path%>/page/static/top_top.html" -->
 <div class="homeBody">
    <div class="order">
    	<div class="header">
    		<div id="logo">
    			<a href="#none" class="title"><b></b></a>
    		</div>
    		<div class="schedule">
			        <dl class="first done">
			            <dt class="s-num">1</dt>
			            <dd class="s-text">1.我的购物车<s></s><b></b></dd>
			        </dl>
			        <dl class="normal doing">
			            <dt class="s-num">2</dt>
			            <dd class="s-text">2.填写核对订单信息<s></s><b></b></dd>
			        </dl>
			        <dl class="normal last">
			            <dt class="s-num">3</dt>
			            <dd class="s-text">3.成功提交订单<s></s><b></b></dd>
			        </dl>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
        <!-- div class="note">
        	<span class="tit-txt">填写并核对订单信息</span>
        </div-->
        <div class="orderInfo">
			   <div class="orderItemsBox orderAddress">
				<div class="itemsTitle">
					<b>收货地址</b><!-- a href="javascript:;" class="addNewAdd" style="">使用新地址</a -->
				</div>
				<div class="itemsWrap">
					<ul class="addressList clearfix">
					 	<s:iterator value="address" status="ind">
							<li name="<s:property value='id'/>" class="item <s:if test="#ind.first">on</s:if>" einv="false">
							    <input type="hidden" id="<s:property value='id'/>_receiver" value="<s:property value='receiver'/>"/>
							    <input type="hidden" id="<s:property value='id'/>_provinceId" value="<s:property value='provinceId'/>"/>
							    <input type="hidden" id="<s:property value='id'/>_cityId" value="<s:property value='cityId'/>"/>
							    <input type="hidden" id="<s:property value='id'/>_countyId" value="<s:property value='countyId'/>"/>
							    <input type="hidden" id="<s:property value='id'/>_address" value="<s:property value='address'/>"/>
							    <input type="hidden" id="<s:property value='id'/>_mobile" value="<s:property value='mobile'/>"/>
								<div class="address-tit">
									<b id="<s:property value='id'/>_receiver_text"><s:property value='receiver'/></b>
									<span id="<s:property value='id'/>_mobile_text"><s:property value='mobile.replaceAll("^(\\\\d{3})(\\\\d{4})(\\\\d{4})$","$1****$3")'/></span>
								</div>
								<div class="address-con" id="<s:property value='id'/>_area_text"><s:property value='provinceName'/><s:property value='cityName'/><s:property value='countyName'/><s:property value='address'/></div>
								<div class="address-ope">
									<a class="addAlter" href="javascript:;" id="<s:property value='id'/>">修改</a>
									<a class="addDelete" href="javascript:;" id="<s:property value='id'/>">删除</a>
								</div>
								<div class="defaultBtn" id="<s:property value='id'/>">设为默认地址</div>
								<div class="addDefault oIcon"></div>
							</li>
						</s:iterator>	
						<li class="add">
							<div class="address-add">
								<i class="oIcon"></i>
								<span>增加新地址</span>
							</div>
						</li>
					</ul>
					<div class="addOpenBox">
						<span class="addressOpen" style="display: none;"><b>展开</b><i class="oIcon"></i></span>
					</div>
				</div>
			</div>
			<div class="orderItemsBox orderPayment">
				<div class="itemsTitle">
					<b>支付方式</b>
				</div>
				<div class="itemsWrap">
					<ul class="paymentList">
						<li><label><input name="sel_pay" type="radio" value="1" _ind="0" id="huodaofukuan"><span class="listTitle">货到付款</span></label><span class="listPro">送货上门使用现金或刷银行卡付款，无手续费。</span><em class="conPro03" style="display: none;">所选商品不支持货到付款！</em></li>
						<li><label><input name="sel_pay" type="radio" value="2" _ind="1" checked="checked" class="zhifu" id="zhifu"><span class="listTitle">在线支付</span></label><span class="listPro">支持多家银行借记卡;支持支付宝、微信等多种支付平台。</span></li>
					</ul>
				</div>
			</div>
			<div class="orderItemsBox orderInvoice" id="inv_href">
				<div class="itemsTitle">
					<b>发票信息</b><!-- a href="javascript:;" class="modifyPayment">修改</a-->
				</div>
				<div class="itemsWrap">
					<div class="contentTitle">
						<input type="checkbox" id="is_inv" class="isinv" value="" />
						<label for="invoice">
							<span class="listTitle2">发票</span>
						</label>
						<span class="listPro2">说明：是否开具发票</span>
						<!-- a class="listLink" href="" target="_blank">发票说明</a-->
					</div>
					<ul class="contentBox">
						<li>
							<span>发票类型：</span>
							<label><input type="radio" name="inv_type" title="纸质发票" value="1" class="inv_type2" checked="checked">纸质发票</label>
							<label><input type="radio" name="inv_type" class="inv_type1" value="2" title="电子发票" disabled="disabled">电子发票</label>
						</li>				
						<li class="invTitLi">
							<span>发票抬头：</span>
							<label><input type="radio" name="invtop_type" class="inv_type1" id="invtop_type_personal" value="1" title="个人"  checked="checked">个人</label>
							<label><input type="radio" name="invtop_type" title="公司" id="invtop_type_company" value="2" class="inv_type2">公司</label>
							<label><input class="inv_top" type="text" maxlength="50" id="inv_top"></label>
						</li>
						<li>
							<span>发票内容：</span>
								<label><input type="radio" name="inv_con" checked="checked"  title="明细" value="1">明细</label>
								<label><input type="radio" name="inv_con" title="办公用品"  value="2">办公用品</label>
								<label><input type="radio" name="inv_con" title="电脑配件"  value="3">电脑配件</label>
								<label><input type="radio" name="inv_con" title="耗材"  value="4">耗材</label>
						</li> 	
					</ul>
				</div>
			</div>
			<div id="goods_list_container">		
				<div class="orderItemsBox orderCommodity">
				    <div class="itemsTitle"><b>商品信息</b><a href="cart_myCart.dhtml">返回购物车</a></div>
					<div></div>
					<div class="shopWrap">
					<!-- div class="pubTitle">
							<span>自营</span>
					</div-->
				    <div class="itemsWrap">
				    	<table class="comTable">
				        <thead><tr><th width="460">商品名称</th><th width="140">商城价</th><th width="140">购买数量</th><th width="140">库存</th><th width="138">小计</th></tr></thead>
					      <tbody> 
					      		<s:iterator value="shopOrder.goodsList" status="ind">       	        						
					        		<tr>
					        	        <td width="542">
					        		        <div class="productImg">
					        		        	<a href="product_detail.dhtml?id=<s:property value="id" />'" target="_blank">
					        		        		<img src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_img.replace("big","small")'/>" alt="<s:property value="name" />" width="52" height="52">
					        		        	</a>
					        		        </div>
					        		        <div class="productName">
					        				    <p class="twoName">
					        						<a title="<s:property value="name" />" target="_blank" href="product_detail.dhtml?id=<s:property value="id" />'"><s:property value="name" /></a>
												</p>
					        		            <p class="twoName">
					        		            	<span title="<s:property value="name" />"></span>
					        		            </p>
					        				</div>
					        	        </td>			
									    <td width="165">
					        		        <div class="productPriM">￥<s:text name="format.numbers"><s:param value="price"/></s:text></div>
					        		    </td>
					        		    <td width="130">
					        		        	<div class="productNum"><span><s:property value="count"/></span></div>
					        		    </td>
										<td width="140">
					        		        <div class="com productStock">有货</div>         		        
					        		    </td>
										<td width="150" rowspan="1" class="bb">
					        		        <div class="productPri">￥<s:text name="format.numbers"><s:param value="totalPrice"/></s:text></div>
					        		    </td>
					        		</tr>
				        		</s:iterator>
				    		</tbody>
						</table>
				    </div>
				  </div>
				</div>
		 	</div
			<div class="orderItemsBox orderAccount">
				<div class="itemsTitle">
					<b>结算信息</b>
				</div>
				<div class="itemsWrap">
					<div class="settlementList">
						<ul class="accountInfo">
							<li id="cxyh" style="display: none;">
								<div class="settlementWrap">
									<div class="settlementTitle" id="cuxiaoyouhui">
										<i class="oIcon off"></i><span>促销优惠</span>
									</div>
								</div>
								<div class="settlementCon" id="cxyhdetail"></div>
							</li>
							<li class="bb">
								<div class="comTotalBox">
									<p>
										<span><b id="productcount"><s:property value="shopOrder.totalCount"/></b>件商品，商品金额：</span><span class="comTotalSum" id="zje_i">￥<s:text name="format.numbers"><s:param value="shopOrder.totalAmount"/></s:text></span>
									</p>
									<div id="yf_div" style="">
										<span>运费：</span> <span class="comTotalSum red" id="yf_i">￥<s:text name="format.numbers"><s:param value="shopOrder.fare"/></s:text></span>
										<div class="shopIcon" id="shopPop">
									<p id="ye_div" style="display: none">
										<span>余额：</span><span class="comTotalSum" id="ye_i"></span>
									</p>
									<p id="fx_div" style="display: none">
										<span>返现：</span><span class="comTotalSum" id="fx_i"></span>
									</p>
									<p id="lpk_div" style="display: none">
										<span>礼品卡：</span><span class="comTotalSum" id="lpk_i"></span>
									</p>
								</div>
							</li>
						</ul>
						<div class="step_offset"></div>
						<div class="orderTotal" id="orderTotal"  style="clear:both;">
							<div class="orderTotalCon">
								<div class="subBtnBox">
									<span class="subBtn"><i class="oIcon" id="orderSave"></i></span>
									<div class="submit-pro"></div>
								</div>
								<div class="toPay">
									<strong>应付总金额：</strong><b id="payPrice">￥<s:text name="format.numbers"><s:param value="shopOrder.payAmount"/></s:text></b>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>
 </div>
 <div class="addresPopBox">
	<div class="addresPop">
		<div class="addPopTit">
			<b></b><strong>新增地址</strong><i class="popClose oIcon"></i>
		</div>
		<div class="addPopCon">
		    <input type="hidden" id="addressId" value=""/>
			<ul>
				<li>
					<span class="addTit">
						<i class="xing">*</i>收货人：
					</span>
					<input name="" type="text" class="addFrom f1 receName" value="" id="receiver" a="0" maxlength="10"/>
					<input name="" type="hidden" id="ID" value=""/>
					<em class="addPro" id="alertstyle1">请填写收货人姓名</em>
				</li>
				<li>
					<span class="addTit">
						<i class="xing">*</i>选择地区：
					</span>
					<select name="new_province" id="provinceId">
						<option value="">请选择</option>
					</select> 
					<select name="new_city" id="cityId" class="city">
						<option value="">请选择</option>
					</select>
					<select name="new_county" id="countyId" class="country">
						<option value="">请选择</option>
					</select>
					<em class="addPro" id="alertstyle2">请选择地区</em>
				</li>
				<li>
					<span class="addTit"><i class="xing">*</i>详细地址：</span>
					<input class="addFrom f2 detailAdd" type="text" maxlength="50" id="address">
					<em class="addPro" id="alertstyle3">请填写收货人详细地址</em>
				</li>
				<li>
					<span class="addTit"><i class="xing">*</i>手机：</span>
					<input class="addFrom f1 recePhone" type="text" id="mobile" maxlength="11">
					<em class="addPro" id="alertstyle5">请填写手机号码</em>
				</li>
				<li class="btnBox"><a href="javascript:;" class="addSubmit">确定</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="popMask" style="height: 643px;"></div>
<form action ="order_save.dhtml" id="myform" method="post" target="_self">
	<input type="hidden" name="orderGoods" value='<s:property value="orderGoods"/>'/>
	<input type="hidden" name="addreId" id="addreId" value=''/>
	<input type="hidden" name="payWay" id="payWay" value=''/>
	<input type="hidden" name="isInv" id="isInv" value=''/>
	<input type="hidden" name="invType" id="invType" value=''/>
	<input type="hidden" name="invToptype" id="invToptype" value=''/>
	<input type="hidden" name="invTop" id="invTop" value=''/>
	<input type="hidden" name="invCon" id="invCon" value=''/>
</form>
  <!--#include file="<%=path%>/page/foot.html" --> 
</body>
</html>