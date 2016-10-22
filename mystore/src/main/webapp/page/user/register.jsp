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
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/user/register.css" />
</head>
<input type="hidden" id="bathPath" value="<%=path%>" />
<input type="hidden" id = "exponent" value="${model.exponent}"/>
<input type="hidden" id="modulus" value="${model.modulus}"/>
<body>
 <s:action name="topAction!top" namespace="/top" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
 	  <div class="container-con register-cont">
	    <h2 class="register-cont-h2">欢迎注册 <span class="sys-error">欢迎注册</span></h2>
	    <div class="register-cont-box">
	        <div class="register-cont-left">
	            <form action="/user/userAction!register.dhtml" method="post" class="registerForm" name="mobileForm" id="regForm">            
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
	                        <div id="pwdStatus" class="mt5">
                                    <div class="pswarp">
                                        <span class="s_box">弱</span>
                                        <span class="s_box">中</span>
                                        <span class="s_box">强</span>
                                    </div>
                            </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>请确认密码</label>                        
	                        </div>
	                        <div class="form-element">
	                            <input type="password" nullmsg="请填写密码" errormsg="您两次输入的账号密码不一致！" class="required equalTo" datatype="psw" maxlength="16" id="comfirm_password" name="comfirm_password">                            
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请输入确认密码</span>
	                        </div>
	                    </li>
	                    <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码</label>                        
	                            </div>
	                        <div class="form-element">
	                            <input type="text" nullmsg="请输入验证码" maxlength="6" errormsg="验证码不正确！" class="required" datatype="*" placeholder="请输入验证码" id="verifyCode" name="verifyCode">
	                            <div id="codeImg">
	                           		 <img width="108" height="42" alt="看不清？请刷新"  src="<%=path%>/CheckCode.pic?" id="flushcode" valign="middle" style="right:105px;cursor: pointer;"/>    
	                            </div>                       
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请输入验证码</span>
	                        </div>
	                    </li>
	                    <!-- 
	                     <li>
	                        <div class="form-label">
	                            <s>*</s>
	                            <label>短信验证码</label>
	                        </div>
	                        <div style="width: 62%;" class="form-element">
	                            <input type="text" nullmsg="请输入短信验证码" errormsg="验证码不正确" datatype="*" placeholder="请输入短信验证码" id="phone_code">
	                            <div id="codeImg">
	                            	<button class="sendMoblieBtn" type="button" name="btn" id="btn">获取验证码</button>
	                            </div>
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b>请输入短信验证码
	                        </div>
	                    </li>
	                     -->
	                    <li stype="margin:0 auto;">
	                        <div stype="text-align:center;width:50%;margin-bottom:20px;">
	                            <div class="checkbox_div" stype="margin-left:50px;">
	                               <input type="checkbox" data-label="我已阅读并同意" class="checkbox required" id="agreeProtocol"  name="agreeProtocol" checked="checked">
	                               <span class="checklabel checked">&nbsp;&nbsp;我已阅读并同意</span>
	                               <a style="color: #297bc7;cursor: pointer;text-decoration: none;" href="javascript:;">《用户协议》</a>
	                             </div> 
	                        </div>
	                        <div class="errorTip hidden">
	                            <b class="btip"></b><span>请选择</span>
	                        </div>
	                        <button class="imgRoundBtn" type="submit" id="registerButton">同意协议并注册</button>
	                    </li>
	                    <li class="itemli">
                                <div class="toReg">
                                   	 已是会员？<a href="/login.html" style="color: #297bc7;cursor: pointer;text-decoration: none;">立即登录</a>
                                </div>
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
<script type="text/javascript" language="javascript" src="<%=path%>/js/user/register.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/encryption/security.js"></script>
<style type="text/css">
        input.error{
			border:0px;
		}
</style>
</html>