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
<title>用户中心</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/index.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="<%=path%>/js/user/index.js"></script>
</head>
<input type="hidden" id="bathPath" value="<%=path%>" />
<body>
 <s:action name="topAction!top" namespace="/top" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
 	  <input type="hidden" id="basePath" value="<%=path%>"/>
      <div class="list_left">
      		<h1>
	      		<a href="<%=path%>/user/userAction!index.dhtml">
	      			<i></i><span>我的惠美购</span>
	      		</a>
      		</h1>
      		<div class="userInfo">
      		    <div class="headImg"><a href="javascrpt:void(0);"><img src="http://localhost:8080/picService/upload/pro/img/8/20161022114304_small.jpg"/></a></div>
      		    <div class="userName">zwhzwh</div>
      		</div>
      		<div class="navList">
      		   <div>
      		      
      		   </div>
      		</div>
      </div>
      <div class="list_right">
      </div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>