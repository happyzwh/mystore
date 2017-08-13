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
<title>帐户日志</title>
</head>
<body>
 <div class="list_content">
	<table class="accountTab" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr class="goldenTabTrTh">
						<th width="200">时间</th>
						<th width="100">业务类型</th>
						<th width="100">出入类型</th>
						<th width="180">金额</th>
						<th width="300">备注</th>
					</tr>
				</thead>
				<tbody>
				 	 <s:iterator value="list" status="ind">
						<tr>
							<td><s:date name='createDate' format="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
								<s:if test = 'bizType == "0"'>充值</s:if>
								<s:elseif test = 'bizType == "1"'>提现冻结</s:elseif>
								<s:elseif test = 'bizType == "2"'>提现付款</s:elseif>
								<s:elseif test = 'bizType == "3"'>下单冻结</s:elseif>
								<s:elseif test = 'bizType == "4"'>下单付款</s:elseif>
								<s:elseif test = 'bizType == "5"'>退款</s:elseif>
								<s:elseif test = 'bizType == "6"'>返现</s:elseif>
							</td>
							<td>
								<s:if test = 'ioType == "0"'>出帐</s:if>
								<s:elseif test = 'ioType == "1"'>入帐</s:elseif>
							</td>
							<td class="jianjian">¥<s:text name="format.numbers"><s:param value="amount"/></s:text></td>
							<td class="niubi-li"><s:property value="desc" /></td>
						</tr>	
					</s:iterator>	
				</tbody>
	 </table> 
	 <div class="Pagination"> 
			<div style="float:right;">
				${pageInfo}					
			</div>
     </div>     
  </div>
</body>
</html>