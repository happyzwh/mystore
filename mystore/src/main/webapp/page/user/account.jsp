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
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/account.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script src="<%=path%>/js/user/account.js" type="text/javascript"></script>
<script src="<%=path%>/js/user/accountLog.js" type="text/javascript"></script>
<script src="<%=path%>/js/hidemenu.js" type="text/javascript"></script>
</head>
<body>
 <%@ include file="/page/static/top_top.html" %>
 <div class="homeBody">
      <s:action name="user_menu" namespace="/" executeResult="true" ignoreContextParams="true">
      		<s:param name="bh"><%=MenuMap.MYACCOUNT.getBh()%></s:param>
      </s:action>
	 <div class="list_right">
		   <div class="rightTit"><h2><i></i>我的余额</h2></div>
		   <div class="rightCon">
			   <div class="golden-content">
				   <div class="golden clearfix">
				      <p class="guoqi guoqiL">
					    <strong>我的余额</strong>
						<span>￥<s:text name="format.numbers"><s:param value="account.amount"/></s:text></span>
					  </p>
				  </div>
			 </div>
			 <div class="titles clearfix">
				 <ul class="tab_menu">
				 	<li class="on1-niubi select" name="accountlog">余额明细</li>
			     </ul>
			</div>
			<div class="accountlog detail">
				
			</div>
		</div>
	  </div>
      <div style="clear:both;"></div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>