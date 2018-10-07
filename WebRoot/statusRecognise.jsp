<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/statusRecognise.css">
	

  </head>
  
  <body>
   <div id="main">
   	<div class="logimg"><img src="img/logintitle.gif"/></div>
   	<form action="StatusRecognise" method="post">
   		<table border="0">
   			<tr>
   				<td></td>
   				<td><font color="red">${requestScope.error}</font></td>
   			</tr>
   			<tr>
   				<td>员工编号:</td>
   				<td><input type="text" name="employeeID" value="${param.employeeID}"/></td>
   			</tr>
   			<tr>
   				<td>系统口令:</td>
   				<td><input type="password" name="password"/></td>
   			</tr>
   			<tr>
   				<td></td>
   				<td align="center"><input type="image" src="img/login.gif"/></td>
   			</tr>
   		</table>
   	</form>
   </div>
  </body>
</html>
