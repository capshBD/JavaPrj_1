<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/msgList.css"/>
  </head>
  
  <body>
    <div>
    	<center style="color: red">${error}</center>
    	<ul>
    	<c:forEach items="${sessionScope.messageList}" var="message">
    		<li>
    		<div style="float: left"><a class="msg_li" href="GetMessage?messageID=${message.messageID}">${message.messageTitle}</a>
    		</div>
    		<div align="right">发布人ID:${message.employeeID} 发布时间:${message.publishTime}</div>
    		<div align="right">回复数:${message.replyCount}</div>
    		<hr>
    		</li>
    	</c:forEach>
    	</ul>
    	<center>
    	<c:choose>
    		<c:when test="${page.hasPrePage}">
    			<a href="GetMessageList?currentPage=1&key_word=${key_word}">首页</a>|
    			<a href="GetMessageList?currentPage=${page.currentPage-1}&key_word=${key_word}">上一页</a>|
    		</c:when>
    		<c:otherwise>
    		             首页|上一页|
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${page.hasNextPage}">
    			<a href="GetMessageList?currentPage=${page.currentPage+1}&key_word=${key_word}">下一页</a>|
    			<a href="GetMessageList?currentPage=${page.totalPage}&key_word=${key_word}">末页</a>|
    		</c:when>
    		<c:otherwise>
    			下一页|末页|
    		</c:otherwise>
    	</c:choose>
    	第${page.currentPage}页|共${page.totalPage}页
    </center>
    </div>
  </body>
</html>
