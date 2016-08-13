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
<title>用户注册</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/user/register.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/user/register.js" type="text/javascript"></script>
</head>
<body style="background: #eaedf1 none repeat scroll 0 0;">
 <s:action name="topAction!top" namespace="/top" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
 	  <div class="container-con register-cont">
	    <h2 class="register-cont-h2">欢迎注册</h2>
	    <div class="register-cont-box">
	        <div class="register-cont-left">
	            <form method="post" action="/site/register.html?did=&amp;sid=" id="registerForm" class="box-form">                
	            	<ul class="m-form">
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label for="RegisterForm_account_username">用户名</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="text" name="RegisterForm[account_username]" nullmsg="用户名/手机号码/邮箱" errormsg="手机号码不正确" datatype="m" placeholder="用户名/手机号码/邮箱" id="account_username" class="form-input">                           
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label for="RegisterForm_password">设置密码</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="password" name="RegisterForm[password]" nullmsg="请填写密码" errormsg="密码为数字,字母,特殊字符任意组合的6-16位字符" datatype="psw" placeholder="数字,字母,特殊字符任意组合的6-16位字符" id="password" class="form-input nospace">                           
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label for="RegisterForm_comfirm_password">确认密码</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="password" name="RegisterForm[comfirm_password]" nullmsg="请填写密码" errormsg="您两次输入的账号密码不一致！" datatype="psw" recheck="RegisterForm[password]" placeholder="再次输入密码" id="comfirm_password" class="form-input nospace">                            
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label for="RegisterForm_verifyCode">验证码</label>                        
	                            </div>
	                        <div class="form-element h-form-element">
	                            <input type="text" name="RegisterForm[verifyCode]" nullmsg="请填写验证码" errormsg="验证码不正确！" datatype="*" placeholder="4位验证码" id="verifyCode" class="form-input">
	                            <img width="108" height="42" alt="" src="/site/captcha.html?v=57ac35b78fee2" id="yw0" valign="middle" style="right:105px;" class="pull-right img-code"><a href="/site/captcha.html?refresh=1" id="yw0_button" style="position: absolute;bottom: -4px;right: 0;" class="blue">看不清？换一张</a>                           
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li>
	                        <div style="border-bottom:none;" class="form-element">
	                            <input type="hidden" name="RegisterForm[agreeProtocol]" value="0" id="ytagreeProtocol"><div class="checkbox_div"><input type="checkbox" value="1" name="RegisterForm[agreeProtocol]" autocomplete="off" data-label="我已阅读并同意" class="checkbox" id="agreeProtocol" checked="checked"><span class="m-check checked"></span><span class="checklabel checked">我已阅读并同意</span></div>                            <a class="blue f-agreen_btn" href="javascript:;">《自助通用户协议》</a>
	                            <div class="checkout"></div>
	                        </div>
	
	                    </li>
	                    <li class="form-btn">
	                        <button autocomplete="off" type="submit" id="register-btn" class="btn btn-primary u-tj_btn">
	                    		    点击注册
	                        </button>
	                    </li>
	                </ul>
	              	<ul class="m-form">
	                    <li style="display: none;" id="image-verify">
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>验证码</label>
	                        </div>
	                        <div class="form-element">
	                            <input type="text" ignore="ignore" nullmsg="请填写验证码" errormsg="验证码不正确" datatype="*" placeholder="4位验证码" id="validate_verifyCode" class="form-input">
	                            <img width="108" height="42" alt="" src="/site/captcha.html?v=57ac35b736f00" id="yw1" valign="middle" style="right:105px;" class="pull-right img-code"><a href="/site/captcha.html?refresh=1" id="yw1_button" style="position: absolute;top: 5px;right: 0;" class="blue">看不清？换一张</a>                           
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>短信验证码</label>
	                        </div>
	                        <div style="width: 62%;" class="form-element">
	                            <input type="text" nullmsg="请输入验证码" errormsg="验证码不正确" datatype="*" name="ValidateForm[smsCode]" placeholder="请输入验证码" id="phone_code" class="form-input">
	                            <button height="42" width="108" type="button" class="btn btn-primary J-resetSend" id="J-resetSend">重新发送</button>
	                            <div class="checkout"></div>
	                        </div>
	                    </li>
	                    <li class="form-btn">
	                        <button autocomplete="off" type="submit" id="register-btn-next" class="btn btn-primary u-tj_btn">完成注册</button>
	                    </li>
	                </ul>
	            </form>
	            <div style="clear:both;"></div>
	        </div>
	        <div style="border-left: 1px solid #DDDDDD" class="register-cont-right">
	            <h3 class="register-cont-h3">注册即享以下服务</h3>
	            <img style="margin-bottom: 60px;" src="/static/images/xiang.jpg"/>
	            <p>已有账号？<a class="blue" href="login.html">立即登录</a></p>
	            <div style="clear:both;"></div>
	        </div>
	        <div style="clear:both;"></div>
	    </div>
	</div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>