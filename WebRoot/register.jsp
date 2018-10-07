<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/base/ui.all.css">
	<link rel="stylesheet" type="text/css" href="css/register.css">
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="js/ui.datepicker.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#bd').datepicker();
			$('#jd').datepicker();
			var Rad = document.getElementById("sub");
			$('#chk').click(function(){
				if($('#chk').attr('checked')){
					Rad.disabled=false;
				}else{
				    Rad.disabled=true;
				}
			})
		});
	</script>
  </head>
  
  <body>
  	<div id="main">
  		<div id="form">
  			<form action="EmpRegister" method="post">
  				<table  cellspacing="0px" cellpadding="0px">
  					<tr>
  						<td>会员名:</td>
  						<td><input name="empName" type="text"/></td>
  						<td class="proto" rowspan="6">
  						 <h4><img src="img/read.gif" alt="alt" />阅读论坛服务协议 </h4>
  						 <textarea name="textarea" cols="35" rows="15">欢迎阅读服务条款协议，本协议阐述之条款和条件适用于您使用Gmgw.com网站的各种工具和服务。</textarea>
  						</td>
  					</tr>
  					<tr>
  						<td>密码：</td>
  						<td><input name="password" type="password"/>（至少包含 6 个字符）</td>
  					</tr>
  					<tr>
  						<td>性别：</td>
  						<td><input name="sex"  type="radio" value="男" checked="checked" />
				    	    <img src="img/Male.gif" alt="alt" />男&nbsp; 
				    	    <input name="sex"  type="radio" value="女" />
				    	    <img src="img/Female.gif" alt="alt" />女
    	    			</td>
  					</tr>
  					<tr>
  						<td>出生日期:</td>
    	 				<td><input name="birthDate" type="text" id="bd"/></td>
  					</tr>
  					<tr>
  						<td>电话号码：</td>
  						<td><input name="empPhone" type="text"/></td>
  					</tr>
  					<tr>
  						<td>家庭地址：</td>
  						<td><input name="empPlace" type="text"/></td>
  					</tr>
  					<tr>
  						<td>加入时间：</td>
  						<td><input name="joinTime" type="text" id="jd"/></td>
  					</tr>
  					<tr>
  						<td></td>
  						<td><input src="img/registernow.gif" type="image" disabled="disabled" id="sub"/></td>
  						<td class="proto"><input type="checkbox" id="chk"/>同意以上协议</td>
  					</tr>
  				</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
