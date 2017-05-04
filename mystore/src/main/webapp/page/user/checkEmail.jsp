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
      <div class="list_right" style="width:1000px;">
			    <div class="rightTit"><h2><i></i>邮箱验证</h2></div>
			    <div class="rightCon">
			        <div class="ln_addressBox" id="addInput">
				            <div class="ln_addressBoxTit">
								<div class="ln_addressBoxTitL" c="0">邮箱验证</div>				
				            </div>
				            <div class="ln_addressCon">
				                <div class="ln_addressTr">
				                	<s:property value='msg'/>
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