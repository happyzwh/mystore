<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>帮助中心</title>
</head>
<body>
<s:set name="type" value="#request.id" scope="request"/>
<div class="list_left">
      		<h1>
	      		<a href="help_center.dhtml">
	      			<i></i><span>帮助中心</span>
	      		</a>
      		</h1>
      		<s:if test="helpMenu != null && helpMenu.size > 0 ">
	      		<div class="navList">
	      		     <s:iterator value="helpMenu" id="cate">
	      		   		   <div class="func func1">
			      		       <p class="title"><i></i><span><s:property value='#cate.name'/></span></p>
			      		       <s:iterator value="#cate.infos" id="info" status="ind">
			      		       		<a class="item <s:if test='#request.type == #info.id'>on</s:if>" href="<%=path%>/help_center.dhtml?id=<s:property value='#info.id'/>" title=""><span><s:property value='#info.title'/></span><i></i></a>
			      		       </s:iterator>
			      		   </div>
	      		     </s:iterator>
	      		</div>
      		</s:if>
      </div>
	</body>
</html>