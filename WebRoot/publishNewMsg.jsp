<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布消息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<div>
    <p align="center"><font color="red">${requestScope.error}</font></p>
    <form action="MsgPublish" method="post">
    <p align="center">消息标题:<input name="title" type="text" size="50"/></p>
    <p align="center">消息内容:</p>
    <center>
    	<FCK:editor id="content" basePath="/JavaPrj_1/FCKeditor/" 
    		toolbarSet="Default" height="400" width="750">
    	</FCK:editor>
    </center>
    <p align="center"><input type="submit" value="提交"/><input type="reset" value="重置"/></p>
    </form>
    </div>
  </body>
</html>
