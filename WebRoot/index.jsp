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
    
    <title>系统首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#btn').click(function(){
				var key_word=$('#key_word').val();
				if(key_word==''){
					alert('请输入关键字!!!');
					return;
				}
				document.searchForm.submit();
			})
		
		})
	</script>
  </head>
  
  <body>
  	<div id="main">
  		<div id="header">
  			<div id="sys_menu">
  				<ul>
  					<li class="sys_m1"></li>
  					<li><a class="sys_link" href="userInfo.jsp" target="msg_li">返回首页</a></li>
  					<li class="sys_m2"></li>
  					<li><a class="sys_link" href="sysout.jsp" target="msg_li">退出系统</a></li>
  					<li class="sys_logout">
  						<c:if test="${not empty employee}">
  							<label style="font-size:12px;color:blue">已登录</label>
  						</c:if>
  						<c:if test="${empty employee}">
  							<a class="sys_link" href="statusRecognise.jsp">登录</a>
  						</c:if>
  					</li>
  					<li class="sys_logout"><a class="sys_link" href="register.jsp">注册</a></li>
  				</ul>
  			</div>
  			<div id="wel">欢迎${employee.employeeID}来到瞎特么扯论坛</div>
  			<div id="fun_menu">
  				<ul id="menu_layout">
  					<li><a class="fun_link" href="publishNewMsg.jsp" target="msg_li">发布帖子</a></li>
  					<li><a class="fun_link" href="GetMessageList" target="msg_li">浏览新帖</a></li>
  					<li><a class="fun_link" href="#">论坛公告</a></li>
  					<li><a class="fun_link" href="#">申请权限</a></li>
  					<li><a class="fun_link" href="#">个人中心</a></li>
  					<li><a class="fun_link" href="#">运营维护</a></li>
  					<li><a class="fun_link" href="#">系统版本</a></li>
  					<li><a class="fun_link" href="#">关于我们</a></li>
  				</ul>
  				<div id="search">
  					<form action="GetMessageList" method="post" name="searchForm" target="msg_li">
  				    <input id="key_word" type="text" name="key_word" style="width:160px;height: 22px"/>&nbsp;<input id="btn" type="button" value="搜贴" />
  				    </form>
  				</div>
  			</div>
  		</div>
  		
  		<div id="center">
  			<iframe name="msg_li" frameborder="0" scrolling="no" onload="this.height=msg_li.document.body.scrollHeight"  src="userInfo.jsp?regInfo=${sessionScope.regOk}"/>
  		</div>
  	</div>
  </body>
</html>
