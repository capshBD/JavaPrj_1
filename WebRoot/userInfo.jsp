<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/userInfo.css">
	
  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${not empty param.regInfo}">
  			<p style="color:green">恭喜您,注册成功,请牢记您的登录账号:${param.regInfo}</p>
  			<%session.removeAttribute("regOk"); %>
  		</c:when>
  	<c:otherwise>
  	<div id="left">
    <c:choose>
  			<c:when test="${empty sessionScope.employee}">
  				<p style="color:red">没有进行身份识别,请进行身份识别!</p>
  			</c:when>
  			<c:otherwise>
  				<ul class="emp_info">
  				当前用户信息:
  					<li>员工姓名:${employee.employeeName}</li>
  					<li>员工性别:${employee.employeeSex?"男":"女"}</li>
  					<li>出生日期:${employee.employeeBirth}</li>
  					<li>办公电话:${employee.employeePhone}</li>
  					<li>员工住址:${employee.employeePlace}</li>
  					<li>管理领导:${employee.lead?"是":"否"}</li>
  				</ul>
  			</c:otherwise>
  		</c:choose>
  	</div>
  	<div id="right">
  		<p>${sessionScope.info}</p>
  	</div>
  	</c:otherwise>
  	</c:choose>
  </body>
</html>
