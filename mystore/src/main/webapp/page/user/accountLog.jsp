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
				<tbody>
					<tr class="goldenTabTrTh">
						<th width="200">时间</th>
						<th width="100">类型</th>
						<th width="180">金额</th>
						<th width="300">备注</th>
					</tr>
					<tr>
						<td>2014-02-09 14:59:55</td>
						<td> 支出 </td>
						<td class="jianjian">-6300.00</td>
						<td class="niubi-li">金币兑换商品后扣减金币数量-6300!</td>
					</tr>	
					<tr>
						<td>2014-02-09 09:15:07</td>
						<td> 支出 </td>
						<td class="jianjian">-6300.00</td>
						<td class="niubi-li">金币兑换商品后扣减金币数量-6300!</td>
					</tr>	
					<tr>
						<td>2014-02-07 00:25:53</td>
						<td> 支出 </td>
						<td class="jianjian">-6000.00</td>
						<td class="niubi-li">金币兑换商品后扣减金币数量-6000!</td>
					</tr>	
					<tr>
						<td>2014-02-05 20:20:13</td>
						<td> 支出 </td>
						<td class="jianjian">-6000.00</td>
						<td class="niubi-li">金币兑换商品后扣减金币数量-6000!</td>
					</tr>	
				</tbody>
	 </table>      
  </div>
</body>
</html>