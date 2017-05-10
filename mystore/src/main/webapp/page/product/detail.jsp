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
<title>商品详情</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/detail.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/detail.js" type="text/javascript"></script>
</head>
<body>
  <%@ include file="/page/static/top_top.html" %>
 <input type="hidden" id="proId" value="<s:property value='product.id'/>"/>
 <input type="hidden" id="basePath" value="<%=path%>"/>
 <div class="homeBody">
      <div class="nav">
           <a href="/">首页</a><i>></i>
           <s:iterator value="categorys" status="ind">
           		<a href="search_list.dhtml?keys=<s:property value='id'/>-0-0-0-0-0-0-0"><s:property value='name'/></a><i>></i>
           </s:iterator>
           <a href="search_list.dhtml?keys=<s:property value='product.id_cate'/>-<s:property value='product.id_brand'/>-0-0-0-0-0-0"><s:property value='product.brandName'/></a><i>></i>
           <s:property value='product.name'/> 
      </div>
      <div class="dIntro">
              <div class="preview">
					<div id="vertical" class="bigImg">
						<img src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='proImgs[0].path_img.replace("big","mid")'/>" width="400" height="400" alt="" id="midimg" />
						<div style="display:none;" id="winSelector"></div>
					</div><!--bigImg end-->	
					<div class="smallImg">
						<div class="scrollbutton smallImgUp disabled"></div>
						<div id="imageMenu">
							<ul>
							    <s:iterator value="proImgs" status="ind">
							          <li <s:if test="#ind.first">id="onlickImg"</s:if>><img src="<%=ConfigReader.getPath_pic_service()%>/<s:property value='path_img.replace("big","small")'/>" width="68" height="68" alt=""/></li>
							    </s:iterator>
							</ul>
						</div>
						<div class="scrollbutton smallImgDown"></div>
					</div><!--smallImg end-->	
					<div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>
			  </div>
		      
		      <div class="goods-intro">
				    <div class="goods_name">
				    	<h1><s:property value='product.name'/></h1>
				    	<strong></strong>
				    </div>
				    
				    <ul class="goods-price list">  
					     <li>
					        <span>市&nbsp;&nbsp;场&nbsp;&nbsp;价：</span>
						     <del class="price_com">￥<s:text name="format.number"><s:param value="proPrice.markPrice"/></s:text></del>
					     </li>
					     <li>
					        <span>商&nbsp;&nbsp;城&nbsp;&nbsp;价：</span>
					        <span class="salePrice_big">
					      	 	 ￥<s:text name="format.number"><s:param value="proPrice.shopPrice"/></s:text>
					        </span>&nbsp;&nbsp;
						    (&nbsp;<s:text name="format.number"><s:param value="proPrice.shopPrice/proPrice.markPrice*10"/></s:text>折&nbsp;)&nbsp;&nbsp;
						    <span class="price_com">
						     	立省： ￥<s:text name="format.number"><s:param value="proPrice.markPrice-proPrice.shopPrice"/></s:text>
						    </span>
					     </li> 
					     <li>好&nbsp;&nbsp;评&nbsp;&nbsp;度：&nbsp;&nbsp;<span class="comCountBar"><i style="width:90%"></i></span>&nbsp;&nbsp;(<a class="comCount" style="color:#f60;" href="#xx3" rel="nofollow">已有21人评论</a>)</li>
					     <li><span>商品货号：<s:property value='product.sn'/></span></li>  
				    </ul>
				    
				    <div class="hightline cl">  
				    	<div class="hightbox">     
				    		 <div class="goods-spec"> 
				     
				     			<div class="buyinfo"> 
				     			 	 <table width="100%" cellspacing="0" cellpadding="0" border="0">
									       <tbody>
										       <tr>
										       	<th width="20%">购买数量：</th>
											       	<td>
											      		 <span id="decrease" class="numadjust decrease"></span>
											      		 <input type="text" value="1" size="5" id="number" name="number"/>
											      		 <span id="increase" class="numadjust increase"></span> 
											      		 <span style=" line-height:28px">&nbsp;&nbsp;</span>
									               </td>
								               </tr>
								            </tbody>
								      </table> 
				                  </div> 
				     		</div>
				   		 </div>    
				    	<div class="cl"></div>  
				   		<div class="btnBar">   
						      <div class="btnsBox">  
						      	 <a href="javascript:addToCart2(843)" onclick="getmousepos(event)" value="加入购物车" class="actbtn btn-buy"></a>
						      </div> 
						      <div class="fav_goods"><a class="btn-fav" href="javascript:collect(843)">&nbsp;&nbsp;收藏此商品</a></div>
				    	</div>      
					 </div>         
		   	 </div>   
    		<div style="clear:both;"></div>
    		
      </div>
      <div class="detail_box">
     		 <div class="detail_left">
			    <div class="cl">
			       <div class="blank5"></div>
			       <div class="hot_sale"> 
			             <div class="txtTit"><font class="f93 fl pl15">畅销排行</font><font class="f94 fl">&nbsp;HOT SALES</font></div>
						 <a href="http://www.huimeigou.com/jzt-g9549.html"><img class="addd" src="http://www.huimeigou.com/images/201507/goods_img/9549_P_1437958266904.jpg"/></a>
			             <ul class="hs_goodslist">
				             <li class="hs_goods">
					              <span class="img"><img border="0" class="lazyload" alt="原生态磁能修护霜55g" src="http://huimeigou.gotoip3.com/images/201501/thumb_img/963_thumb_G_1422467817402.jpg"/></span>            
							      <a class="name" href="/jingmeiru-g963.html">原生态磁能修护霜55g</a>
					              <font class="price fl">￥150</font><br/><font class="f3 fl">专柜价:<del>￥216</del></font>
				             </li>                                                                                          
			            </ul>
			        </div> 
			     </div>
			  </div>
			  <div class="detail_right">
				  <div class="tab_menu">
						<ul>
							<li name="goodsdetail" class="first select">商品介绍</li>
							<li name="goodscomment">商品评论</li>
							<li name="goodsconsult">商品咨询</li>
						</ul>
				   </div>
				   <div class="goodsdetail detail">
					   <div class="goods_property"> 
					        <s:iterator value="proAttrMaps" status="state"> 
							        <div>
							            <i class="c2 mr10"><s:property value="name"/>:</i><s:property value="value"/>
							        </div>
					        </s:iterator>
					    </div>
					     <div style="clear:both"></div>
					    <div class="xxinfo">
					        <s:if test="proInfo != null && proInfo.descr != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>商品描述</span>
									</div>
									<div class="a-mark">
										${proInfo.descr}
									</div>
								</div>     
							</s:if>
					        <s:if test="proInfo != null && proInfo.feature != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>商品特点</span>
									</div>
									<div class="a-mark">
										${proInfo.feature}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.proShow != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>商品展示</span>
									</div>
									<div class="a-mark">
										${proInfo.proShow}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.areaIntroduce != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>产地介绍</span>
									</div>
									<div class="a-mark">
										${proInfo.areaIntroduce}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.qualification != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>商品资质</span>
									</div>
									<div class="a-mark">
										${proInfo.qualification}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.useMethod != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>使用方法</span>
									</div>
									<div class="a-mark">
										${proInfo.useMethod}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.brandCulture != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>品牌文化</span>
									</div>
									<div class="a-mark">
										${proInfo.brandCulture}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.honor != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>获奖荣誉</span>
									</div>
									<div class="a-mark">
										${proInfo.honor}
									</div>
								</div>     
							</s:if>
							<s:if test="proInfo != null && proInfo.providerIntroduce != null">
							    <div class="i-box">
									<div class="title">
										<i></i><span>厂家介绍</span>
									</div>
									<div class="a-mark">
										${proInfo.providerIntroduce}
									</div>
								</div>     
							</s:if>
						</div>
					</div>
					<div class="goodscomment detail">
					</div>
					<div class="goodsconsult detail">
					</div>
			   </div>
			  
			  <div style="clear:both;"></div>
	  </div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>