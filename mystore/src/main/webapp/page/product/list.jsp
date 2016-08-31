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
<title>商品列表_搜索</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/detail.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/product/list.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/product/list.js" type="text/javascript"></script>
</head>
<body>
 <s:action name="topAction!top" namespace="/top" executeResult="true" ignoreContextParams="true"/>
 <div class="homeBody">
      <div class="list_left">
         <div class="box">
             <div class="title">热卖推荐</div>
             <div class="content">
             	<div class="li">
             	    <div class="p-img">
             	         <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com" target="_blank">
             	              <img src="http://localhost:8080/picService/upload/pro/img/1/20160505220700.jpg" />
             	         </a>
             	    </div>
             		<div class="p-name">
             			 <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com"  target="_blank">
             	             <em>酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显</em>
             	         </a>
             		</div>
             		<div class="p-price">                          
             			<strong class="price">
             				<span>￥2499.00</span>
             			</strong>                                              
             		</div>
             		<div class="p-review">                          
             			已有<a class="number" target="_blank" href="//item.jd.com/10511150218.html#comment">67</a>人评价                      
             		</div>
             	</div>
             	<div class="li">
             	    <div class="p-img">
             	         <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com" target="_blank">
             	              <img src="http://localhost:8080/picService/upload/pro/img/1/20160505220700.jpg" />
             	         </a>
             	    </div>
             		<div class="p-name">
             			 <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com"  target="_blank">
             	             <em>酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显</em>
             	         </a>
             		</div>
             		<div class="p-price">                          
             			<strong class="price">
             				<span>￥2499.00</span>
             			</strong>                                              
             		</div>
             		<div class="p-review">                          
             			已有<a class="number" target="_blank" href="//item.jd.com/10511150218.html#comment">67</a>人评价                      
             		</div>
             	</div>
             </div>
         </div>
         <div class="box">
             <div class="title">热卖推荐</div>
             <div class="content">
             	<div class="li">
             	    <div class="p-img">
             	         <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com" target="_blank">
             	              <img src="http://localhost:8080/picService/upload/pro/img/1/20160505220700.jpg" />
             	         </a>
             	    </div>
             		<div class="p-name">
             			 <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com"  target="_blank">
             	             <em>酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显</em>
             	         </a>
             		</div>
             		<div class="p-price">                          
             			<strong class="price">
             				<span>￥2499.00</span>
             			</strong>                                              
             		</div>
             		<div class="p-review">                          
             			已有<a class="number" target="_blank" href="//item.jd.com/10511150218.html#comment">67</a>人评价                      
             		</div>
             	</div>
             	<div class="li">
             	    <div class="p-img">
             	         <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com" target="_blank">
             	              <img src="http://localhost:8080/picService/upload/pro/img/1/20160505220700.jpg" />
             	         </a>
             	    </div>
             		<div class="p-name">
             			 <a title="酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显" href="http://www.baidu.com"  target="_blank">
             	             <em>酷睿i5-6500/8G/120G/GTX950独显/技嘉主板/游戏四核台式机电脑主机整机 电脑主机(无显示器) 酷睿i5-6500四核+核显</em>
             	         </a>
             		</div>
             		<div class="p-price">                          
             			<strong class="price">
             				<span>￥2499.00</span>
             			</strong>                                              
             		</div>
             		<div class="p-review">                          
             			已有<a class="number" target="_blank" href="//item.jd.com/10511150218.html#comment">67</a>人评价                      
             		</div>
             	</div>
             </div>
         </div>
      </div>
      <div class="list_right">
      		<div class="nav">
      		   <div class="rela"><em>相关产品</em><span>44</span><em>件</em></div>
      		   <div class="li">
				   <i class="square"></i>
				   <a class="level level1" title="全部" href=" /1-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm#v2 ">全部</a>
				   <i class="arrow"></i>
				   <a class="level level2" title="白酒 " href=" /1-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm#v2 ">白酒 </a>
			   </div>
      		</div>
      		<div class="selected">
      		    <p class="tit">你已选择：</p>
      		    <div class="options">
					<a href="/1-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2"><span class="type">品牌：汾酒</span><span class="remove"></span></a>
					<a href="/1-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2"><span class="type">品牌：宋河</span><span class="remove"></span></a>
				</div>
      		</div>
      		<div class="select">
      		    <div style="display:block" class="selectLine2">
					<div class="proLeft">产区</div>
					<div class="proRight">
						<ul class="clearfix">
							<li><a title="山西" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山西</a></li>
							<li><a title="山东" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山东</a></li>
						</ul>	
					</div>
			    </div>
			    <div style="display:block" class="selectLine2">
					<div class="proLeft">产区</div>
					<div class="proRight">
						<ul class="clearfix">
							<li><a title="山西" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山西</a></li>
							<li><a title="山东" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山东</a></li>
						</ul>	
					</div>
			    </div>
			    <div style="display:block" class="selectLine2">
					<div class="proLeft">产区</div>
					<div class="proRight">
						<ul class="clearfix">
							<li><a title="山西" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山西</a></li>
							<li><a title="山东" href="/1-4-0-9488-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">山东</a></li>
						</ul>	
					</div>
			    </div>
      		</div>
      		<div class="moreS clearfix">
      			<p class="tit">更多选项</p>
				<div class="moreOption wine clearfix">
					<a href="javascript:void(0)" class="choose">酒精度<i></i></a>
					<div class="box" style="display: none;">
						<ul class="clearfix">
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-1-0-0-0.htm?area=2#v2">35度以下</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-2-0-0-0.htm?area=2#v2">35-39度</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-3-0-0-0.htm?area=2#v2">40-49度</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-4-0-0-0.htm?area=2#v2">50度以上</a>
							</li>
						</ul>
					</div>
					<a href="javascript:void(0)" class="choose">净含量<i></i></a>
					<div class="box" style="display: none;">
						<ul class="clearfix">
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-1-0-0.htm?area=2#v2">475m以下</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-2-0-0.htm?area=2#v2">475ml</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-3-0-0.htm?area=2#v2">500ml</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-4-0-0.htm?area=2#v2">1L</a>
							</li>
							<li>
								<a href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-5-0-0.htm?area=2#v2">1.5L以上</a>
							</li>
						</ul>
					</div>
					<a href="javascript:void(0)" class="choose clearfix">价格<i></i></a>
					<div class="box" style="display: none;">
						<ul class="clearfix">
							<li><a href="/1-4-0-0-0-0-1-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">1-99元</a></li>
							<li><a href="/1-4-0-0-0-0-2-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">100-299元</a></li>
							<li><a href="/1-4-0-0-0-0-3-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">300-599元</a></li>
							<li><a href="/1-4-0-0-0-0-4-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">600-999元</a></li>
							<li><a href="/1-4-0-0-0-0-5-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">1000-1999元</a></li>
							<li><a href="/1-4-0-0-0-0-6-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2">2000元以上</a></li>
						</ul>
					</div>
				</div>
			</div>
      		<div id="order" class="order">
      			<ul class="clearfix">
					<li class="tab  first ">
						<a title="默认排序" href="/1-4-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2" rel="nofollow">默认排序</a>
					</li>
					<li class="tab">
						<a title=" 默认 " href="/1-4-0-0-0-0-0-1-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2" rel="nofollow">销量 <i></i> </a>
					</li>
					<li class="tab jg">
						<a title=" 默认 " href="/1-4-0-0-0-0-0-2-1-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2" rel="nofollow">价格 <i style="background-position: -86px -108px; "></i> </a>
					</li>
					<li class="tab">
						<a title=" 默认 " href="/1-4-0-0-0-0-0-3-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2" rel="nofollow">评价 <i></i> </a>
					</li>
					<li class="tab sjsj">
						<a title=" 默认 " href="/1-4-0-0-0-0-0-4-0-0-0-0-0-0-0-0-0-0-0.htm?area=2#v2" rel="nofollow">上架时间 <i></i> </a>
					</li>
					<li pricearea="0" class="priceArea clearfix">
						<span class="left clearfix"><i>¥</i><input type="text" id="minPrice" value=""></span>
						<span class="zhi"></span>
						<span class="right clearfix"><i>¥</i><input type="text" id="maxPrice" value=""></span>
						<span class="sure"><a rel="nofollow" href="javascript:;" class="ok">确定</a></span>
					</li>
				
					<input type="hidden" id="price" value="">
					<input type="hidden" id="AB" value="0">
					<input type="hidden" id="ruleKey" value="a">
					<input type="hidden" id="assKey" value="">
				    <!-- 
					<li class="page">
						<p><span class="cur">1</span><span>/</span><span class="total">2</span></p>											
				    	<a style="background-position: 0px -142px; " href="javascript:;" class="btn prev"></a> 
				    	<a style="background-position: -35px -142px; " href="/1-4-0-0-0-0-0-0-0-0-0-0.htm?pageNum=2&amp;area=2" class="btn next"></a>
				    </li>
				    -->
				</ul>
				<div class="new_distBox">
					<label class="new_lab"><input type="checkbox" id="ynProduct">&nbsp;仅显示有货</label>
				</div>
			</div>
      		<div class="proListSearch">
      		    <div class="Pagination">    
					<div style="float:right;">
							${pageInfo}					
					</div>
				</div>
      			<div class="content"> 
					<div class="collect_box">
						<span id="cell_24268" class="collect collect2" style="display: none;"><i></i>收藏</span> 
						<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html  " class="img clearfix"> 
						<span style="display:none" class="cxtag tj" id="mark_24268"></span> 
						<img src="http://img06.jiuxian.com/2015/1109/222e1223a82a4b4794e268642008e6af4.jpg" proimgid="24268"></a> 
					</div>
					<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html " class="proName">【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml<span class="slogan" id="promote_24268">正统汾酒集团，两瓶送原厂礼袋</span></a>
					<div class="priceArea">
						<p id="product_24268" class="price">¥69.00 </p>
						<div id="activity_24268"><span class="yh">返券</span><span class="yh">赠品</span></div>&nbsp;
					</div>
					<a target="_blank" title="" href="http://www.jiuxian.com/goods-24268.html#answerArea" class="judge">已有<span>462</span>人评价</a>
					<div class="buyArea clearfix">
						<span>
							<input type="text" name="" value="1" onkeyup="changeNum(this.value,24268)" id="InputNum24268" gname="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" gid="24268">
						</span>
						<div class="edit"> 
							<a href="javascript:;" class="increase"></a> 
							<a href="javascript:;" class="decrease"></a> 
						</div>
						<a href="javascript:;" class="cart clearfix prtlt_btn2">加入购物车</a> 
					</div>
				</div>
				
				<div class="content"> 
					<div class="collect_box">
						<span id="cell_24268" class="collect collect2" style="display: none;"><i></i>收藏</span> 
						<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html  " class="img clearfix"> 
						<span style="display:none" class="cxtag tj" id="mark_24268"></span> 
						<img src="http://img06.jiuxian.com/2015/1109/222e1223a82a4b4794e268642008e6af4.jpg" proimgid="24268"></a> 
					</div>
					<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html " class="proName">【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml<span class="slogan" id="promote_24268">正统汾酒集团，两瓶送原厂礼袋</span></a>
					<div class="priceArea">
						<p id="product_24268" class="price">¥69.00 </p>
						<div id="activity_24268"><span class="yh">返券</span><span class="yh">赠品</span></div>&nbsp;
					</div>
					<a target="_blank" title="" href="http://www.jiuxian.com/goods-24268.html#answerArea" class="judge">已有<span>462</span>人评价</a>
					<div class="buyArea clearfix">
						<span>
							<input type="text" name="" value="1" onkeyup="changeNum(this.value,24268)" id="InputNum24268" gname="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" gid="24268">
						</span>
						<div class="edit"> 
							<a href="javascript:;" class="increase"></a> 
							<a href="javascript:;" class="decrease"></a> 
						</div>
						<a href="javascript:;" class="cart clearfix prtlt_btn2">加入购物车</a> 
					</div>
				</div>
				
				<div class="content"> 
					<div class="collect_box">
						<span id="cell_24268" class="collect collect2" style="display: none;"><i></i>收藏</span> 
						<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html  " class="img clearfix"> 
						<span style="display:none" class="cxtag tj" id="mark_24268"></span> 
						<img src="http://img06.jiuxian.com/2015/1109/222e1223a82a4b4794e268642008e6af4.jpg" proimgid="24268"></a> 
					</div>
					<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html " class="proName">【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml<span class="slogan" id="promote_24268">正统汾酒集团，两瓶送原厂礼袋</span></a>
					<div class="priceArea">
						<p id="product_24268" class="price">¥69.00 </p>
						<div id="activity_24268"><span class="yh">返券</span><span class="yh">赠品</span></div>&nbsp;
					</div>
					<a target="_blank" title="" href="http://www.jiuxian.com/goods-24268.html#answerArea" class="judge">已有<span>462</span>人评价</a>
					<div class="buyArea clearfix">
						<span>
							<input type="text" name="" value="1" onkeyup="changeNum(this.value,24268)" id="InputNum24268" gname="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" gid="24268">
						</span>
						<div class="edit"> 
							<a href="javascript:;" class="increase"></a> 
							<a href="javascript:;" class="decrease"></a> 
						</div>
						<a href="javascript:;" class="cart clearfix prtlt_btn2">加入购物车</a> 
					</div>
				</div>
				
				<div class="content"> 
					<div class="collect_box">
						<span id="cell_24268" class="collect collect2" style="display: none;"><i></i>收藏</span> 
						<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html  " class="img clearfix"> 
						<span style="display:none" class="cxtag tj" id="mark_24268"></span> 
						<img src="http://img06.jiuxian.com/2015/1109/222e1223a82a4b4794e268642008e6af4.jpg" proimgid="24268"></a> 
					</div>
					<a target="_blank" title="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" href=" http://www.jiuxian.com/goods-24268.html " class="proName">【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml<span class="slogan" id="promote_24268">正统汾酒集团，两瓶送原厂礼袋</span></a>
					<div class="priceArea">
						<p id="product_24268" class="price">¥69.00 </p>
						<div id="activity_24268"><span class="yh">返券</span><span class="yh">赠品</span></div>&nbsp;
					</div>
					<a target="_blank" title="" href="http://www.jiuxian.com/goods-24268.html#answerArea" class="judge">已有<span>462</span>人评价</a>
					<div class="buyArea clearfix">
						<span>
							<input type="text" name="" value="1" onkeyup="changeNum(this.value,24268)" id="InputNum24268" gname="【酒仙独家】53°汾酒集团原浆（臻品升级版）475ml" gid="24268">
						</span>
						<div class="edit"> 
							<a href="javascript:;" class="increase"></a> 
							<a href="javascript:;" class="decrease"></a> 
						</div>
						<a href="javascript:;" class="cart clearfix prtlt_btn2">加入购物车</a> 
					</div>
				</div>
				
      		</div>
      		<div class="Pagination">    
				<div style="float:right;">
						${pageInfo}					
				</div>
			</div>
      </div>
 </div>
 <jsp:include page="/page/foot.jsp" />
</body>
</html>