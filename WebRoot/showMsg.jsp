<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消息详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my rePage">
	
	<link rel="stylesheet" type="text/css" href="css/showMsg.css">
	

  </head>
  
  <body>
    <div>
    	<c:choose>
    	<c:when test="${!rePage.hasPrePage}">
    	<h2 align="center">${message.messageTitle}</h2><br/>
    	${message.messageContent}
    	<div align="right">发布人ID:${message.employeeID},发布时间:${message.publishTime}</div>
    	<br/>
    	</c:when>
    	</c:choose>
    	<div>
    		<c:forEach items="${sessionScope.replys}" var="reply">
    		<div style="float: left">回复人:${reply.employeeID}</div>
    		<div align="right"> 
    		回复时间:${reply.replyTime} ${reply.floor==1?'沙发':(reply.floor==2?'板凳':(reply.floor==3?'地板':(reply.floor==4?'下水道':reply.floor)))}
    		<c:if test="${reply.floor>4}">楼</c:if>
    		</div>
    		<hr>
    		<div>${reply.replyContent}</div>
    		</br></br>
    		</c:forEach>
    	</div>
    	<c:choose>
    		<c:when test="${!rePage.hasNextPage}">
	    		<div align="left">
	    		<p>回复消息:</p>
	    		<p><font color="red">${error}</font></p>
	    		<form action="CommitReply" method="post">
	    			<FCK:editor id="content" basePath='/JavaPrj_1/FCKeditor/'
    							toolbarSet="Default" height="300" width="680">
    				</FCK:editor>
	      			<input type="hidden" name="employeeID" value="${sessionScope.employeeID}"/>
	      			<input type="hidden" name="msgemployeeID" value="${message.employeeID}"/>
	      			<input type="hidden" name="messageID" value="${message.messageID}"/>
	      			<input type="submit" value="提交"/><input type="reset" value="重置"/>
	    		</form>
	    	   </div>
    		</c:when>
    	</c:choose>
    	<div align="center">
    		<c:choose>
    		<c:when test="${rePage.hasPrePage}">
    			<a href="GetReplyList?currentPage=1&messageID=${message.messageID}">首页</a>|
    			<a href="GetReplyList?currentPage=${rePage.currentPage-1}&messageID=${message.messageID}">上一页</a>|
    		</c:when>
    		<c:otherwise>
    		             首页|上一页|
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${rePage.hasNextPage}">
    			<a href="GetReplyList?currentPage=${rePage.currentPage+1}&messageID=${message.messageID}">下一页</a>|
    			<a href="GetReplyList?currentPage=${rePage.totalPage}&messageID=${message.messageID}">末页</a>|
    		</c:when>
    		<c:otherwise>
    			下一页|末页|
    		</c:otherwise>
    	</c:choose>
    	第${rePage.currentPage}页|共${rePage.totalPage}页
    	</div>
    </div>
  </body>
</html>
