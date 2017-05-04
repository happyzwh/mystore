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
<script type="text/javascript" language="javascript"  src="<%=path%>/js/common.js"></script>
<script type="text/javascript" language="javascript"  src="<%=path%>/js/user/mailedit.js"></script>
</head>
<body>
 <%@ include file="/page/static/top_top.html" %>
 <div class="homeBody">
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.SECURITY.getBh()%></s:param>
      </s:action>
      <div class="list_right">
			    <div class="rightTit"><h2><i></i>编辑邮箱</h2></div>
			    <div class="rightCon">
			        <div class="ln_addressBox" id="addInput">
				            <div class="ln_addressBoxTit">
								<div class="ln_addressBoxTitL" c="0">编辑邮箱</div>				
				            </div>
				            <div class="ln_addressCon">
				                <div class="ln_addressTr">
				                	<span class="ln_addressTrTit">
				                		<i>*</i>邮箱：
				                	</span>
				                	<input id="mail" name="mail" value="" class="qjwSubmit4" type="text">
				                	<div class="acc">
				                		<div class="accNotic" style="display:none">
					                		<i></i>
					                		<span>请输入邮箱</span>
				                		</div>
				                	</div>
				                </div>
				                <div class="ln_addressTr">
				                	 <span class="ln_addressTrTit">
				                	 	<i>*</i>验证码：
				                	 </span>
				                	 <input id="checkCode" name="checkCode" class="qjwSubmit4" style="width:220px;" value="" maxlength="5" type="text">
				                	 <img width="108" height="35" alt="看不清？请刷新"  src="<%=path%>/CheckCode.pic?" id="flushcode" valign="middle" style="float:left;margin-right:10px;cursor: pointer;"/>
				                	 <div class="acc">
				                	 	<div id="addressTS" class="accNotic" style="display:none">
					                	 	<i></i>
					                	 	<span>请输入验证码</span>
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