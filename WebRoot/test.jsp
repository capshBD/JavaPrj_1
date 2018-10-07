<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="FCKeditor/fckeditor.js">
		
	</script>

  </head>
  
  <body>
  	<form action="show.jsp" method="get">
            <table border="0" width="700">
                <tr>
                    <td>
                        <textarea id="content" name="content"
                            style="WIDTH: 100%; HEIGHT: 400px">input</textarea>
                        <script type="text/javascript">
                            var oFCKeditor = new FCKeditor('content') ;
                            oFCKeditor.BasePath = "/JavaPrj_1/FCKeditor/" ;
                            oFCKeditor.Height = 400;
                            oFCKeditor.ToolbarSet = "Default" ;
                            oFCKeditor.ReplaceTextarea();
                        </script>
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
  </body>
</html>
