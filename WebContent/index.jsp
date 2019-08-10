<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>火车订票系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/topa.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript">
		function test(){
			ek.updateElement();
			var com=$("#tst").val();
			alert(com);
		}
	</script>
  </head>
  <body>
  <center>
    <table cellspacing="0" cellpadding="0" style="border: 1px solid #C0C0C0">
    	<tr>
    		<td>
    		<!-- 头部 -->
    			<jsp:include page="/top.jsp"></jsp:include>
    		</td>
    	</tr>
    	<tr>
    		<td height="570px" width="1024px" style="background-color:white;" valign="top"  align="center">
    			<iframe id="mainf" name="mainf" src="CusCkSvlt" frameborder="0" style="margin: 0;padding: 0;width: 100%;height:100%; min-width: 600px;min-height: 500px;"></iframe>
    		</td>
    	</tr>
    </table>
  </center>
  </body>
</html>
