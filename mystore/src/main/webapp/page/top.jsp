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
  		<div class="topadv">
  			<a href='<s:property value="advImg.url"/>' target="_blank">
				<img width="100%" height="100%" src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='advImg.path_pic'/>"/>
			</a>
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
	            <li><a href="<%=path%>/">欢迎光临惠美购商城!</a></li>
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
	            	<s:iterator value="firstCateList" status="inx">
		                  <li class="catItem">
		                    <div class="catItemCon">
								<h3><a href="<%=path%>/search_list.dhtml?keys=<s:property value='id'/>-0-0-0-0-0-0-0" target="_blank"><s:property value='name'/></a></h3>
								<div class="categoryCon">
								   <s:set name="index" value="0"/>
								    <s:iterator value="sons" status="ind">
								        <s:if test="#index==0">
								       		 <p>
								        </s:if>
								        <s:set name="index" value="#index+1"/>
								    	<a rel="nofollow" target="_blank" href="<%=path%>/search_list.dhtml?keys=<s:property value='id'/>-0-0-0-0-0-0-0"><s:property value='name'/></a>					
								        <s:if test="#index == 3">
								        	  </p>
								        	  <s:set name="index" value="0"/>
								        </s:if>
								    </s:iterator>
								</div>
							</div>
		                  </li> 
	                 </s:iterator> 
	            </ul>
	            <s:iterator value="secondCateList" status="inx">
		            <div style="display:none;" class="menuBox ">
						<!-- 右侧文字 start -->
							<div class="menuCon">	
							   <s:iterator value="top" status="inxs">
									<div class="menuItem">    
										<div class="menuItemTitle"><h4><s:property value='name'/></h4></div>
										<div class="menuItemCon">
											<div class="menuCon-list">
											    <s:set name="index" value="0"/>
												<s:iterator value="sons" status="ind">
												    <s:if test="#index==0">
											       		<p class="clearfix">
											        </s:if>
											        <s:set name="index" value="#index+1"/>
												    <a target="_blank" href="<%=path%>/search_list.dhtml?keys=<s:property value='id'/>-0-0-0-0-0-0-0"><s:property value='name'/></a>
												    <s:if test="#index == 7">
										        	  </p>
										        	  <s:set name="index" value="0"/>
										        </s:if>
												</s:iterator>
											</div>
										</div>
									</div> 
							   </s:iterator> 
							</div>
					 <!-- 右侧文字 end -->   
					</div>
           	 </s:iterator>
            </div>
         </div>
         <!-- div class="navi_middle">
            <ul>
               <li><a href=""><em class="icon_t1" style="font-style: normal;">首页</em></a></li>
               <li><a href="#">2222</a></li>
               <li><a href="#">3333</a></li>
               <li><a href="#">4444</a></li>
               <li><a href="#">5555</a></li>
               <li><a href="#">6666</a></li>
            </ul>
         </div -->
         <div class="navi_right">
           <a href="<%=path%>/user_center.dhtml"><em class="icon_t2" style="width:120px;">我的惠美购</em><em class="ar2"></em></a>
           <a href="<%=path%>/cart_myCart.dhtml"><em class="icon_t3" style="width:90px;">购物车</em><b id="ECS_CARTINFO" class="cart_num" style="color:#ffffff;">0</b><em class="ar2" style="right:-23px;"></em></a>
           <a href="#"><em style="width:90px;">去结算</em><em class="ar2"></em></a>
         </div>
      </div>
  </div>
</div>
<form id="topSearchForm" action="<%=path%>/search_lists.dhtml" method="post" target="_self">
	<input type="hidden" name="key" id="key" value="" />
 	<input type="hidden" name="pageNo" id="pageNo" value="1" />
 </form>
</body>
</html>