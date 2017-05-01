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
<title></title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/foot.css" type="text/css"/>
</head>
<body>
<div class="foot_out">
   <div class="foot_in">
	   <div class="footer"> 
			<div class="row02">
				<div class="row02bg"></div>
			</div> 
			  <div class="row03">
			      <span>
			   
			             <a rel="nofollow" target="_blank" href="<%=path%>/help_center.dhtml?id=25">关于我们</a>
			           &nbsp;|&nbsp;
			              <a rel="nofollow" target="_blank" href="<%=path%>/help_center.dhtml?id=26">商务合作</a>
			           &nbsp;|&nbsp;
			              <a rel="nofollow" target="_blank" href="<%=path%>/help_center.dhtml?id=27">法律声明</a>
			           &nbsp;|&nbsp;
			              <a rel="nofollow" target="_blank" href="<%=path%>/help_center.dhtml?id=28">联系我们</a>
			           &nbsp;|&nbsp;
			              <a rel="nofollow" target="_blank" href="<%=path%>/help_center.dhtml?id=29">加入我们</a>   
			     </span> 
			      <p>ICP备案证书号:<a target="_blank" rel="nofollow" href="<%=path%>">京ICP备00000000号</a></p>  
			  </div> 
		</div>
    </div>
</div>
</body>
</html>