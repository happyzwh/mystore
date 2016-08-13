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
<script src="<%=path%>/js/consult.js" type="text/javascript"></script>
</head>
<body>
 <div class="comment">
      <div class="p10 cl">  
			<div class="commentborder comment_total">
                <div class="mt">
                    <h2>商品咨询</h2>
                </div>
            </div>
            <div class="comments-list commentDetail">
                <div class="ctitbar">
				    	<ul class="tab" id="consultTab">
						    <li id="retab1" <s:if test="type==null || type==0">class="curr"</s:if>><a>全部咨询<em>(<s:property value='proConsultStatis.get("count")'/>)</em></a></li>
						    <li id="retab2" <s:if test="type!=null && type==1">class="curr"</s:if>><a>商品<em>(<s:property value='proConsultStatis.get("pro")'/>)</em></a></li>
						    <li id="retab3" <s:if test="type!=null && type==2">class="curr"</s:if>><a>支付<em>(<s:property value='proConsultStatis.get("pay")'/>)</em></a></li>
						    <li id="retab4" <s:if test="type!=null && type==3">class="curr"</s:if>><a>售后<em>(<s:property value='proConsultStatis.get("sale")'/>)</em></a></li>
					        <li class="tab-last"></li>
                        </ul>
			    </div>
                <div id="ECS_COMMENT">
					<div class="mc">
					  <s:iterator value="list" status="ind" id="consult">
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
									            <s:if test="#consult.content.length()>10">
													<s:property value="#consult.content.substring(0,10)+'...'" />
												</s:if>
												<s:else>
													<s:property value="#consult.content" />
												</s:else>
								            </strong>
								            <span class="star sa5">&nbsp;</span>
								            <span class="date-comment"><s:date name="#consult.createDate" format="yyyy-MM-dd hh:mm:ss" /></span>
						                </div>
							            <div class="comment-content">
							                        <dl>
														<dt>咨询：</dt>
														<dd><s:property value="#consult.content" /></dd>
													</dl>                                
							            </div>
							            <s:iterator value="#consult.replys" status="indr" id="reply">
								            <div class="comment-content comment-reply">
								                        <dl>
															<dt>回复：</dt>
															<dd><s:property value="#reply.content" /></dd>
														</dl>                                
								            </div>
							            </s:iterator>          
								</div>                
							</div>
						</s:iterator>	               
				   </div>                               		  
				   <div class="Pagination">    
						<span style="float:left"><span style="cursor:pointer; color:#CB0009;" id="goConsult">我要咨询</span></span>
						<div style="float:right;">
							${pageInfo}					
						 </div>
					</div>
					      
					<div style="display:none" id="consultsList" class="commentsList">
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
												   <span class="tishi" id="remnum_connum">剩余100字符</span>  
											    </li>
											    <li class="pLi">
											      <span class="f_r">咨询类别：</span>
											      <input type="radio" value="1" name="consult_type"/>
											      	商品
											      <input type="radio" value="2" name="consult_type"/>
											      	支付
											      <input type="radio" value="3" checked="checked" name="consult_type"/>
											                       售后
											    <li class="pLi">
											      	<textarea rows="" cols="" maxlength = "100" class="contarea" id="consult_content"></textarea>
											    </li>
											    <li style="text-align:center">
											      <input type="button" value="评 &nbsp;&nbsp;&nbsp;&nbsp;论 " style="cursor:pointer;" id="save_consult"/>
											      &nbsp;&nbsp;&nbsp;&nbsp;
											      <input type="button" value="清 &nbsp;&nbsp;&nbsp;&nbsp;空 " style="cursor:pointer;" id="reset_consult"/>
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