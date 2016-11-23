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
<title>用户登录</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/register.css" />
</head>
<input type="hidden" id = "exponent" value="${model.exponent}"/>
<input type="hidden" id="modulus" value="${model.modulus}"/>
<body>
 <s:action name="top_top" namespace="/" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
 	  <div class="container-con register-cont">
	    <h2 class="register-cont-h2">用户登录 <span class="sys-error">用户登录</span></h2>
	    <div class="register-cont-box">
	        <div class="register-cont-left">
	            <form action="user_login.dhtml" method="post" class="registerForm" name="mobileForm" id="regForm">            
	            	<ul class="m-form">
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>用&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;名</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="text" nullmsg="用户名" class="required" datatype="m" placeholder="用户名" maxlength="50" id="userName" name="userName">                           
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请输入用户名</span>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="password" nullmsg="请填写密码" class="required" errormsg="密码为数字,字母,特殊字符任意组合的6-16位字符" maxlength="16" datatype="psw" placeholder="数字,字母,特殊字符任意组合的6-16位字符" name = "password" id="password"> 
	                            <input type="text" nullmsg="请填写密码" class="required" errormsg="密码为数字,字母,特殊字符任意组合的6-16位字符" maxlength="16" datatype="psw" placeholder="数字,字母,特殊字符任意组合的6-16位字符" id="password1" style="display:none;"> 
	                            <img id="changepwd" src="<%=path%>/images/pwdicon.png" style="position: absolute; right: 4px; top: 16px; cursor: pointer;">                     
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请输入密码</span>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="text" nullmsg="请输入验证码" maxlength="6" errormsg="验证码不正确！" class="required" datatype="*" placeholder="请输入验证码" id="verifyCode" name="verifyCode"/>
	                            <div id="codeImg">
	                           		 <img width="108" height="42" alt="看不清？请刷新"  src="<%=path%>/CheckCode.pic?" id="flushcode" valign="middle" style="right:105px;cursor: pointer;"/>    
	                            </div>                       
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请输入验证码</span>
	                        </div>
	                    </li>
	                    <li stype="margin:0 auto;">
	                        <button class="imgRoundBtn" type="submit" id="registerButton">立即登录</button>
	                    </li>
	                </ul>
	                <div style="clear:both;"></div>
	             </form>
	        </div>
	        <div class="register-cont-right">
	            
	        </div>
	        <div style="clear:both;"></div>
	    </div>
	</div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="<%=path%>/js/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-validation/localization/messages_zh.js"></script>
<link rel="stylesheet" href="<%=path%>/js/jquery-validation/css/validate.css" />
<script type="text/javascript" language="javascript" src="<%=path%>/js/user/login.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/encryption/security.js"></script>
<style type="text/css">
        input.error{
			border:0px;
		}
</style>
</html>