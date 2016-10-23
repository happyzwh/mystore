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
<title>商城首页</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/index.js" type="text/javascript"></script>
</head>
<body>
 <s:action name="top_top" namespace="/" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
      <div class="mainBanner">
			<div class="bigImg">
		    	<ul id="bannerul" style="position: relative; width: 1000px; height: 470px;" class="bigUl">
		    	    <s:iterator value="bannerList">
				        <li style='background: transparent url("<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_pic'/>") no-repeat scroll center 0px; position: absolute; width: 1349px; left: 0px; top: 0px; display: ;'>
					        <div class="ban_cter">
					            <a href="<s:property value='url'/>" target="_blank" class="focusArea"></a>
					        </div>
				   		 </li>
			   		 </s:iterator>
			   	 </ul>
		    </div>
		    <div class="smallBtn">
				    <ul class="smallUl">
				    	<s:iterator value="bannerList" status="ind">
						    <li <s:if test="#ind.first">class="on"</s:if>><s:property value='#ind.index+1'/></li>
					    </s:iterator>
				    </ul>
			</div>
	 </div>
	 <s:if test="hotSaleList != null && hotSaleList.size > 0 ">
		 <div class="hotSale">
		       <div class="title">
		            <div class="left"><font class="f91 fl pl15">HOT</font><font class="f92 fl">热卖推荐</font></div>
		            <s:if test="hotSaleTxtList != null && hotSaleTxtList.size > 0 ">
			            <div class="right">
			               <div class="box">
				                 <s:iterator value="hotSaleTxtList" status="ind">
				                	<span><a target="_blank" href="<s:property value='url'/>"><s:property value='name'/></a></span><s:if test="#ind.last"></s:if><s:else>|</s:else>
				                 </s:iterator>
			                </div>
			            </div>
		            </s:if>
		       </div>
		       <div class="box">
		           <ul>
		          	 <s:iterator value="hotSaleList" status="ind">
		               <li>
		                  <div class="enty">
		                        <a href="product_detail.dhtml?id=<s:property value='id_pro'/>">
		                           <img height="" width="" src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_img'/>"/>
		                        </a>
		                        <font class="price">￥<s:property value='shopPrice'/></font>
		                        <font class="f3"><del>￥<s:property value='markPrice'/></del></font>
		                        <p><a target="_blank" href="product_detail.dhtml?id=<s:property value='id_pro'/>"><s:property value='name'/></a></p>
		                  </div>
		              </li>
		             </s:iterator>
		           </ul>
		       </div>
		 </div>
		 <div class="notice">
		             <div class="txtTit"><font class="f93 fl pl15">惠美购公告</font><font class="f94 fl">&nbsp;NOTICE</font></div>
		             <div class="txtList tc">         
	       				  <ul> 
	       				      <s:iterator value="noticeList" status="ind">
	       				            <li>
	       				              <a rel="nofollow" target="_blank" href="info_detail.dhtml?id=<s:property value='id'/>">
		       				              <b class="dot">·</b>
		       				              <s:if test="#ind.first">
			       				              <font class="f1">
			       				              		<s:property value='title'/>
			       				              </font>
		       				              </s:if>
		       				              <s:else>
		       				                  <s:property value='title'/>
		       				              </s:else>
	       				              </a> 
	       				            </li>
	       				      </s:iterator>
	                    </ul>   
	        		</div>
		 </div>
	 </s:if>
	 <s:if test="hotSaleDownList != null && hotSaleDownList.size > 0 ">
	 	<s:iterator value="hotSaleDownList" status="ind">
			 <div class="cl">
				<a target="_blank" href="<s:property value='url'/>"><img  src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_pic'/>"/></a>
			</div>
		</s:iterator>
	</s:if>
	<div class="hotSale">
	       <div class="title">
	            <div class="left"><font class="f91 fl pl15">NEW</font><font class="f92 fl">新品上架</font></div>
	            <div class="right">
	               <div class="box">
		                <s:iterator value="newUpTxtList" status="ind">
				                	<span><a target="_blank" href="<s:property value='url'/>"><s:property value='name'/></a></span><s:if test="#ind.last"></s:if><s:else>|</s:else>
				        </s:iterator>
	                </div>
	            </div>
	       </div>
	       <div class="box">
	           <ul>
	              <s:iterator value="newUpList" status="ind">
		               <li>
		                  <div class="enty">
		                        <a href="product_detail.dhtml?id=<s:property value='id_pro'/>">
		                           <img height="" width="" src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_img'/>"/>
		                        </a>
		                        <font class="price">￥<s:property value='shopPrice'/></font>
		                        <font class="f3"><del>￥<s:property value='markPrice'/></del></font>
		                        <p><a target="_blank" href="product_detail.dhtml?id=<s:property value='id_pro'/>"><s:property value='name'/></a></p>
		                  </div>
		              </li>
		             </s:iterator>
	            </ul>
	       </div>
	 </div>
	 <div class="fc_sale_rank">
	             <div class="txtTit"><font class="f93 fl pl15">大家喜欢</font><font class="f94 fl">&nbsp;ALL LIKE</font></div>
	             <div class="sr_list">
		            <ul>
			             <s:iterator value="allLikeList" status="ind">
				               <li <s:if test="#ind.first"> class="item hover" </s:if><s:else>class="item"</s:else>>
					                <p class="cl tit"><em><s:property value='#ind.index+1'/></em><a href="<%=path%>/product/productAction!detail.dhtml?id=<s:property value='id_pro'/>"><s:property value='name'/></a></p>
					                <div class="sr_con">
					                	 <a target="_blank" class="sr_img" href="product_detail.dhtml?id=<s:property value='id_pro'/>"><img alt="<s:property value='name'/>" src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_img'/>"/></a><p class="text"><s:property value='shopPrice'/><span>最近298人购买</span></p>
					                </div>
				               </li>
			              </s:iterator>                                                                                                                                           
		            </ul>
            </div>
	 </div>
    <s:if test="newUpDownList != null && newUpDownList.size > 0 ">
	 	<s:iterator value="newUpDownList" status="ind">
			 <div class="cl">
				<a target="_blank" href="<s:property value='url'/>"><img  src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_pic'/>"/></a>
			</div>
		</s:iterator>
	</s:if>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>