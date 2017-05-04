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
</head>
<body>
<input type="hidden" id = "exponent" value="${model.exponent}"/>
<input type="hidden" id="modulus" value="${model.modulus}"/>
 <%@ include file="/page/static/top_top.html" %>
 <div class="homeBody">
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.SECURITY.getBh()%></s:param>
      </s:action>
      <div class="list_right">
			    <div class="rightTit"><h2><i></i>修改密码</h2> </div>
			    <div class="rightCon">
			        <div class="ln_addressBox" id="addInput">
				            <div class="ln_addressBoxTit">
								<div class="ln_addressBoxTitL" c="0">修改密码</div>				
				            </div>
				            <div class="ln_addressCon">
				                <div class="ln_addressTr">
				                	<span class="ln_addressTrTit">
				                		<i>*</i>原密码：
				                	</span>
				                	<input id="oldpwd" name="oldpwd" value="" placeholder="数字,字母,特殊字符任意组合的6-16位字符" class="qjwSubmit4" type="password">
				                	<div class="acc">
				                		<div class="accNotic" style="display:none">
					                		<i></i>
					                		<span>请输入6~16位原密码</span>
				                		</div>
				                	</div>
				                </div>
				                <div class="ln_addressTr">
				                	 <span class="ln_addressTrTit">
				                	 	<i>*</i>新密码：
				                	 </span>
				                	 <input id="pwd" name="pwd" class="qjwSubmit4" value="" placeholder="数字,字母,特殊字符任意组合的6-16位字符" maxlength="50" type="password">
				                	 <div class="acc">
				                	 	<div id="addressTS" class="accNotic" style="display:none">
					                	 	<i></i>
					                	 	<span>请输入6~16位新密码</span>
					                	 </div>
					                 </div>
					            </div>
				                <div class="ln_addressTr">
				                	<span class="ln_addressTrTit">
				                		<i>*</i>确认密码：
				                	</span>
				                	<input id="pwdCheck" name="pwdCheck" class="qjwSubmit4" value="" placeholder="数字,字母,特殊字符任意组合的6-16位字符" type="password">
				                	<div class="acc">
				                		<div id="mobileTS" class="accNotic" style="display:none">
					                		<i></i>
					                		<span>请输入6~16位确认密码</span>
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
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" language="javascript"  src="<%=path%>/js/common.js"></script>
<script type="text/javascript" language="javascript"  src="<%=path%>/js/user/updpwd.js"></script>
 <script type="text/javascript" language="javascript"  src="<%=path%>/js/encryption/security.js"></script>
</html>