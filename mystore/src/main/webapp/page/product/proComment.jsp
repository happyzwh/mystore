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
<title>商品评论</title>
<link rel="stylesheet" href="<%=path%>/css/common.css" type="text/css"/>
<link rel="stylesheet" href="<%=path%>/css/comment.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/comment.js" type="text/javascript"></script>
</head>
<body>
 <div class="comment">
      <div class="p10 cl">  
			<div class="commentborder comment_total">
                <div class="mt">
                    <h2>商品评价</h2>
                </div>
                <div class="mc loaded">
	                <div id="i-comment">    
		                <div class="rate">        
			                <strong><s:property value="proCommentStatis['hr']"/><span>%</span></strong>        
			                <br/> <span>好评度</span>     
		                </div>
	                
		                <div class="percent">       
			                  <dl>             
				                 <dt>好评<span>(<s:property value='proCommentStatis.get("hr")'/>)%</span></dt>             
				                 <dd> <div style="width: <s:property value='proCommentStatis.get("hr")'/>%;">&nbsp;</div></dd>        
			                  </dl>         
			                  <dl>             
				                  <dt>中评<span>(<s:property value='proCommentStatis.get("mr")'/>%)</span></dt>             
				                  <dd class="d1">
				                  	<div style="width: <s:property value='proCommentStatis.get("mr")'/>%;">&nbsp;</div>
				                  </dd>         
			                  </dl>         
		                  	  <dl>            
				                   <dt>差评<span>(<s:property value='proCommentStatis.get("lr")'/>%)</span>
				                   </dt>           
				                    <dd class="d1">             
				                    	<div style="width: <s:property value='proCommentStatis.get("lr")'/>%;">&nbsp;</div>
				                    </dd> 	        
		                      </dl>    
		                </div>    
	                 </div>
                  </div>
            </div>
            <div class="blank"></div>
            <div class="comments-list commentDetail">
                <div class="ctitbar">
				    	<ul class="tab" id="commentTab">
						    <li id="retab1" <s:if test="type==null || type==0">class="curr"</s:if>><a>全部评价<em>(<s:property value='proCommentStatis.get("count")'/>)</em></a></li>
						    <li id="retab2" <s:if test="type!=null && type==1">class="curr"</s:if>><a>好评<em>(<s:property value='proCommentStatis.get("hight")'/>)</em></a></li>
						    <li id="retab3" <s:if test="type!=null && type==2">class="curr"</s:if>><a>中评<em>(<s:property value='proCommentStatis.get("mid")'/>)</em></a></li>
						    <li id="retab4" <s:if test="type!=null && type==3">class="curr"</s:if>><a>差评<em>(<s:property value='proCommentStatis.get("low")'/>)</em></a></li>
					        <li class="tab-last"></li>
                        </ul>
			    </div>
                <div id="ECS_COMMENT">
					<div class="mc">
					  <s:iterator value="list" status="ind">
						<div class="item clearfix">                    
						    <div class="user">
								    <div class="u-icon">                          
									    <img src="themes/default/images/reviews/rank_.gif"/>			                   
								    </div>	
							        <div class="u-name">
							            b***9		       
							        </div>                          
						    </div>                        
						    <div class="i-item clearfix">
							           <div class="o-topic">
								            <strong class="topic">
									            <s:if test="content.length()>10">
													<s:property value="content.substring(0,10)+'...'" />
												</s:if>
												<s:else>
													<s:property value="content" />
												</s:else>
								            </strong>
								            <span class="star sa5">&nbsp;</span>
								            <span class="date-comment"><s:date name="createDate" format="yyyy-MM-dd hh:mm:ss" /></span>
						                </div>
							            <div class="comment-content">
							                        <dl>
														<dt>评论：</dt>
														<dd><s:property value="content" /></dd>
													</dl>                                
							            </div>
							            <s:iterator value="replys" status="indr">
								            <div class="comment-content comment-reply">
								                        <dl>
															<dt>回复：</dt>
															<dd><s:property value="content" /></dd>
														</dl>                                
								            </div>
							            </s:iterator>          
								</div>                
							</div>
						</s:iterator>	               
				   </div>                               		  
				   <div class="Pagination">    
						<span style="float:left">我在惠美购化妆品商城购买过此商品”<span style="cursor:pointer; color:#CB0009;" id="goComment">我要评价</span>”</span>
						<div style="float:right;">
							${pageInfo}					
						 </div>
					</div>
					      
					<div style="display:none" id="commentsList" class="commentsList">
						<div id="ECS_COMMENT"> 
							<div class="comdes">
		  						<div class="comcount"></div>
							</div>
							<ul id="plbox">
											    <li class="pLi">用户名：  匿名用户 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												   <a href="#" rel="nofollow">登 &nbsp;&nbsp;&nbsp;&nbsp;录</a> 
												    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
												   <a href="#" rel="nofollow">注 &nbsp;&nbsp;&nbsp;&nbsp;册 </a>
												   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
												   <span class="tishi" id="maxnum_commnum">最多输入100字符</span>  
												   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
												   <span class="tishi" id="remnum_commnum">剩余100字符</span>  
											    </li>
											    <li class="pLi">
											      <span class="f_r">评价等级：</span>
											      <input type="radio" id="comment_rank1" value="1" name="comment_rank"/>
											      <span class="comCountBar"><i style="width:20%"></i></span>
											      <input type="radio" id="comment_rank2" value="2" name="comment_rank"/>
											      <span class="comCountBar"><i style="width:40%"></i></span>
											      <input type="radio" id="comment_rank3" value="3" name="comment_rank"/>
											      <span class="comCountBar"><i style="width:60%"></i></span>
											      <input type="radio" id="comment_rank4" value="4" name="comment_rank"/>
											      <span class="comCountBar"><i style="width:80%"></i></span>
											      <input type="radio" id="comment_rank5" checked="checked" value="5" name="comment_rank"/>
											      <span class="comCountBar"><i style="width:100%"></i></span>
											    <li class="pLi">
											      	<textarea rows="" cols="" maxlength = "100" class="contarea" id="comment_content"></textarea>
											    </li>
											    <li style="text-align:center">
											      <input type="button" value="评 &nbsp;&nbsp;&nbsp;&nbsp;论 " style="cursor:pointer;" id="save_comment"/>
											      &nbsp;&nbsp;&nbsp;&nbsp;
											      <input type="button" value="清 &nbsp;&nbsp;&nbsp;&nbsp;空 " style="cursor:pointer;" id="reset_comment"/>
											    </li>
					     		</ul> 
						</div>
	   			    </div> 
   			</div>				
   		 </div>
       </div>
    </div>
</body>
</html>