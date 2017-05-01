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
<title></title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/top.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/common.js" type="text/javascript"></script>
<script src="<%=path%>/js/top.js" type="text/javascript"></script>
</head>
<body>
<input id="path" type="hidden" value="<%=path%>"/>
<div class="topout">
  <s:if test="advImg != null">
  		<div class="topadv" style='background-image: url("<%=ConfigReader.getPath_pic_service()%>/<s:property value='advImg.path_pic'/>");'>
  			<a href='<s:property value="advImg.url"/>' target="_blank"></a>
  		</div>
  </s:if>
  <div class="topnavigate_out">
	  <div class="topnavigate">
	      <div class="topnavigate_left">
	         <ul>
	           <li><a href="<%=path%>/page/toindex.jsp">收藏商城</a></li>
	         </ul>
	      </div>
	      <div class="topnavigate_right">
	         <ul>
	            <li><a href="<%=path%>/help_center.dhtml">帮助中心 </a></li>
	            <li><a href="<%=path%>/user_goLogin.dhtml">登录</a><i class="cccc"> | </i><a href="<%=path%>/user_goRegister.dhtml">注册</a></li>
	            <li><a href="<%=path%>/page/toindex.jsp">欢迎光临惠美购商城!</a></li>
	         </ul>
	      </div>
	      <div style="clear:both;"></div>
	  </div>
  </div>
  <div class="logo_out">
      <div class="logo_in">
         <div class="logo_wap">
        	 <div class="logo"></div>
         </div>
         <div class="search_out">
            <div class="search_form" style="text-align:left;position:relative;">
                <input type="text" class="text" accesskey="s" id="keys" autocomplete="off" clstag="h|keycount|2015|03a" style="color: rgb(153, 153, 153);"/>
                <div class="list_pro_search">
	                 <ul>
	                     <li>1111</li>
	                     <li>2222</li>
	                     <li class="li_last">3333</li>
	                 </ul>
	            </div>
                <button class="button cw-icon" id="topSearch" clstag="h|keycount|2015|03c"><i></i>搜索</button>
            </div>
            <div class="search_text">
               <ul>
                  <li style="color:red;">京东</li>
                  <li>淘宝</li>
                  <li>易讯</li>
                  <li>小米</li>
               </ul>
            </div>
         </div>
         <div class="searchrightadv_out">
             <div class="searchrightadv">
             	<a href="<%=path%>/help_center.dhtml?id=32"></a>
             </div>
             <div style="clear:both;"></div>
         </div>
      </div>
      <div style="clear:both;"></div>
  </div>
  <div class="navi_out">
      <div class="navi_in">
         <div class="navi_left_out">
            <div class="navi_left"><a href="javascript:void(0);" style="color:#ffffff; font-weight: bold;">全部商品分类</a></div>
            <div class="categoryBox_out">
	            <ul class="categoryBox" >
	                  <li class="catItem">
	                    <div class="catItemCon">
							<h3 iszlm="1" ishot="true" url="http://www.jiuxian.com/" pathname="一键选酒" name="tagH"><i class="publicIcon seleWine"></i><a href="http://www.jiuxian.com" target="_blank"></a>一键选酒</h3>
							<div class="categoryCon">
							<p>
								<a class="on" rel="nofollow" target="_blank" href="http://shop.jiuxian.com/brand-371/activity-395.htm?area=2">实惠套装</a>					
								<a rel="nofollow" target="_blank" href="http://shop.jiuxian.com/brand-371/activity-709.htm?area=2">送礼专区</a>
								<a rel="nofollow" target="_blank" href="http://shop.jiuxian.com/brand-371/activity-624.htm?area=2">宴请酒</a>												
							<p>
								<a rel="nofollow" target="_blank" href="http://shop.jiuxian.com/brand-371/activity-701.htm?area=2">名酒直降</a>					
								<a rel="nofollow" target="_blank" href="http://shop.jiuxian.com/brand-371/activity-440.htm?area=2">大坛酒</a>
								<a class="on" rel="nofollow" target="_blank" href="http://sale.jiuxian.com/two/index-4843.htm?area=2">婚宴喜酒</a>
							</p>
							</div>
						</div>
	                  </li> 
	            </ul>
	            <div style="display: none;" class="menuBox ">
					<!-- 右侧文字 start -->
					<div class="menuCon">	
						<div class="menuItem">    
							<div class="menuItemCon">
								<div class="menuCon-list">
									<p class="clearfix">
										<a target="_blank" href="http://shop.jiuxian.com/brand-371/activity-807.htm?area=2">商务用酒</a>
										<a target="_blank" href="http://shop.jiuxian.com/brand-371/activity-813.htm?area=2">员工福利</a>
										<a target="_blank" href="http://shop.jiuxian.com/brand-371/activity-825.htm?area=2">高端酒</a>
										<a target="_blank" href="http://shop.jiuxian.com/brand-371/activity-823.htm?area=2">聚餐用酒</a>
									</p>
								</div>
							</div>
						</div>  
					</div>
				 <!-- 右侧文字 end -->   
				</div>
            
            </div>
         </div>
         <div class="navi_middle">
            <ul>
               <li><a href="<%=path%>"><em class="icon_t1" style="font-style: normal;">首页</em></a></li>
               <li><a href="#">2222</a></li>
               <li><a href="#">3333</a></li>
               <li><a href="#">4444</a></li>
               <li><a href="#">5555</a></li>
               <li><a href="#">6666</a></li>
            </ul>
         </div>
         <div class="navi_right">
           <a href="<%=path%>/user_center.dhtml"><em class="icon_t2" style="width:120px;">我的惠美购</em><em class="ar2"></em></a>
           <a href="<%=path%>/cart_myCart.dhtml"><em class="icon_t3" style="width:90px;">购物车</em><b id="ECS_CARTINFO" class="cart_num" style="color:#ffffff;">0</b><em class="ar2" style="right:-23px;"></em></a>
           <a href="#"><em style="width:90px;">去结算</em><em class="ar2"></em></a>
         </div>
      </div>
  </div>
</div>
<form id="topSearchForm" action="<%=path%>/search/searchAction!lists.dhtml" method="post" target="_self">
	<input type="hidden" name="key" id="key" value="" />
 	<input type="hidden" name="pageNo" id="pageNo" value="1" />
 </form>
</body>
</html>