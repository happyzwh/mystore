<%@ page language="java" import="com.mystore.business.common.ConfigReader,com.mystore.business.pojo.MenuMap" contentType="text/html; charset=UTF-8"%>
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
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/address.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/user/address.js"></script>
</head>
<body>
  <%@ include file="/page/static/top_top.html" %>
 <div class="homeBody">
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.ADDRESS.getBh()%></s:param>
      </s:action>
      <div class="list_right">
			    <div class="rightTit"><h2><i></i>收货地址</h2> <a href="#addInput" class="addressTrue">添加新地址</a> </div>
			    <div class="rightCon">
			    	<div class="ln_addressBox1" style="display:block;position:relative">
			        	<table class="ln_addressBoxTit" cellspacing="0" cellpadding="0" border="0">
							<tbody>
								<tr>
						            <th class="men"><p>收货人</p></th>
						            <th class="adr">地址</th>
						            <th class="pho">电话/手机</th>
						            <th class="control">操作</th>
				                </tr>
				                <s:iterator value="addList" status="ind">
				                	<tr>
										<td class="men">
											<p><s:property value='receiver'/></p>
										</td>	
										<td class="adr">
											<p class="adr-td">
												<span style="margin-left:10px"><s:property value='address'/> </span>
											</p>
										</td>	
										<td class="pho">
											<s:property value='mobile'/>			
										</td>
										<td class="control"><a href="<%=path%>/address_index.dhtml?id=<s:property value='id'/>#addInput" class="controlChange">修改</a>  | <a href="javascript:deleteAddress('<s:property value='id'/>');" class="controlRemove" >删除</a></td>
						             </tr>
				               </s:iterator>		
			              </tbody>
			            </table>				
			        </div>
			        <div class="ln_addressBox" id="addInput">
			            <div class="ln_addressBoxTit">
							<div class="ln_addressBoxTitL" c="0">添加新地址</div>				
			                <!-- div class="ln_addressBoxTitR">最多保存<span>20</span>个地址</div-->
			            </div>
						<input name="code" id="code" value="" type="hidden">
							<input id="id" name="address.id" value="<s:property value='address.id'/>" type="hidden">
				            <div class="ln_addressCon">
				                <div class="ln_addressTr">
				                	<span class="ln_addressTrTit">
				                		<i>*</i>收货人：
				                	</span>
				                	<input id="receiver" name="address.receiver" value="<s:property value='address.receiver'/>" class="qjwSubmit4" type="text">
				                	<div class="acc">
				                		<div class="accNotic" id="receiverTS" style="display:none">
					                		<i></i>
					                		<span>请输入收货人姓名</span>
				                		</div>
				                	</div>
				                </div>
				                <div class="ln_addressTr">
				                	 <span class="ln_addressTrTit">
				                	 	<i>*</i>详细地址：
				                	 </span>
				                	 <input id="address" name="address.address" class="qjwSubmit4" value="<s:property value='address.address'/>" maxlength="50" type="text">
				                	 <div class="acc">
				                	 	<div id="addressTS" class="accNotic" style="display:none">
					                	 	<i></i>
					                	 	<span>请输入详细地址</span>
					                	 </div>
					                 </div>
					            </div>
				                <div class="ln_addressTr">
				                	<span class="ln_addressTrTit">
				                		<i>*</i>手机：
				                	</span>
				                	<input id="mobile" name="address.mobile" class="qjwSubmit4" value="<s:property value='address.mobile'/>" type="text">
				                	<div class="acc">
				                		<div id="mobileTS" class="accNotic" style="display:none">
					                		<i></i>
					                		<span>请输入收货人手机号</span>
					                	</div>
					                </div>
					            </div> 
				                <div class="ln_addressTr pt10">
				                	<input name="" class="qjwSubmit2" value="提&nbsp;交" id="save" type="button"/>
				                </div>
				            </div>
			        </div>
			    </div>
      </div>
      <div style="clear:both;"></div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>