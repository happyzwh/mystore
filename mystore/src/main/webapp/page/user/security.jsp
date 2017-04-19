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
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/security.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/user/security.js"></script>
</head>
<body>
 <s:action name="top_top" namespace="/" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.SECURITY.getBh()%></s:param>
      </s:action>
      <div class="list_right">
			    <div class="rightTit"><h2><i></i>帐户安全</h2></div>
			    <div class="rightCon">
			        <div class="zpLevel clearfix">
					  	  <span class="zpBold">
					  	  	     账户安全等级：
					  	  	  <s:if test="level == @com.mystore.business.pojo.SecurityLevelMap@HIGH.bh"> 
					  	  	  	  <strong class="zphigh">高</strong>
					  	  	  </s:if>
					  	  	  <s:elseif test="level == @com.mystore.business.pojo.SecurityLevelMap@MIDDLE.bh"> 
					  	  	  	  <strong class="zpmiddle">中</strong>
					  	  	  </s:elseif>
					  	  	  <s:else> 
					  	  	  	  <strong class="zplow">低</strong>
					  	  	  </s:else>
					  	   </span>
			              <div class="zpLine">
			                  <s:if test="level == @com.mystore.business.pojo.SecurityLevelMap@HIGH.bh"> 
					  	  	  	  <div class="zpUp zphigh"></div>
					  	  	  </s:if>
					  	  	  <s:elseif test="level == @com.mystore.business.pojo.SecurityLevelMap@MIDDLE.bh"> 
					  	  	  	  <div class="zpUp zpmiddle"></div>
					  	  	  </s:elseif>
					  	  	  <s:else> 
					  	  	  	  <div class="zpUp zplow"></div>
					  	  	  </s:else>
			              </div>
					</div>
			        <div class="zpItem">
			           <ul>
						 <li class="first clearfix">               
						  	<i></i>
			              	<p class="zpName">登录密码</p>
			              	<p class="zpDetial">密码为数字,字母,特殊字符任意组合的6-16位字符。</p>
			             	<a href="<%=path%>/security_toUpdPwd.dhtml">修改</a>
			             </li>
						 <li class="clearfix">              
						    <i <s:if test='user.isEmailValid == "0" '>class="unvalid"</s:if>></i>
			                <p class="zpName">邮箱验证</p>
						  	<p class="zpDetial">
						  	    <s:if test='user.isEmailValid == "0"'>邮箱尚未验证,请先验证邮箱,增强帐户安全</s:if>
						  		<s:else>已验证邮箱：<s:property value='user.email'/></s:else>
						  	</p>
							<a href="<%=path%>/page/user/mailedit.jsp">
							    <s:if test='user.isEmailValid == "0"'>设置</s:if>
						  		<s:else>修改</s:else>
							</a>
						 </li>
			             <li class="clearfix">               
			             	<i <s:if test='user.isMobileValid == "0" '>class="unvalid"</s:if>></i>
			                <p class="zpName">手机验证</p>
						  	<p class="zpDetial">
						  	    <s:if test='user.isMobileValid == "0"'>手机尚未验证,请先验证手机,增强帐户安全</s:if>
						  		<s:else>已验证手机： 150****7739 若已丢失或停用，请立即更换，避免账户被盗。</s:else>
						  	</p>
							<a href="/myaccount/change_phone.htm">
							    <s:if test='user.isMobileValid == "0"'>设置</s:if>
						  		<s:else>修改</s:else>
							</a>
						 </li>
						 <li class="paymentPwd clearfix">              
						    <i <s:if test="user.pwdPay == null || user.pwdPay == '' ">class="unvalid"</s:if>></i>
			                <p class="zpName">支付密码</p>
			                <p class="zpDetial">用于支付和提现，密码为数字,字母,特殊字符任意组合的6-16位字符。</p>
						  	<a href="<%=path%>/security_toUpdPayPwd.dhtml">
						  		<s:if test="user.pwdPay == null || user.pwdPay == '' ">设置</s:if>
						  		<s:else>修改</s:else>
						  	</a>
						 </li>
			           </ul>
			        </div>
			        <div class="zpSecurityTip">
			          	<p class="zpName"><i></i><span>安全提示</span></p>
			          	<p>1.注意防范钓鱼网站，谨防网购诈骗。</p>
			          	<p>2.定期更换密码，防止密码泄漏，确保账户及交易安全。</p>
			        </div>
			    </div>
      </div>
      <div style="clear:both;"></div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>