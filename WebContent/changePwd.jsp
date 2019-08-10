<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改登录密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript">
		function tjbt(){
			var op=$("#opwd").val();
			var np=$("#npwd").val();
			var dnp=$("#dnpwd").val();
			if(op==""){
				alert("旧密码不为空。");
				return;
			}
			if(np==""){
				alert("新密码不能为空。");
				return;
			}
			if(np!=dnp){
				alert("两次密码输入不一致。");
				return;
			}
			$.ajax({
				url:'ChangePwd',
				type:'post',
				data:{opwd:op,npwd:np},
				dataType:'json',
				success:function(data){
					alert(data.msg);
				}
			});
		}
	</script>
  </head>
  
  <body>
     <center>
     	<table border="0" cellpadding="0" cellspacing="0" >
     		<tr>
     			<td align="center" colspan="2" height="20px" width="40px">
     				修改登录密码
     				<hr/>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" height="20px" width="400px">
     				<hr/>
     			</td>
     		</tr>
     		<tr>
     			<td width="150px">输入原密码：</td>
     			<td>
     				<input type="text" id="opwd" name="opwd"/>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" height="20px" width="400px">
     			<hr/>
     			</td>
     		</tr>
     		<tr>
     			<td  width="150px">输入新密码：</td>
     			<td>
     				<input type="text" id="npwd" name="npwd"/>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" height="20px" width="400px">
     			<hr/>
     			</td>
     		</tr>
     		<tr>
     			<td  width="150px">再次输入新密码：</td>
     			<td>
     				<input type="text" id="dnpwd" name="dnpwd"/>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" height="20px" width="400px">
     			<hr/>
     			</td>
     		</tr>
     		<tr>
     			<td align="center" colspan="2" height="20px" width="400px">
     				<input type="button" value="提 交" onclick="tjbt();"/>
     			</td>
     		</tr>
     	</table>
     </center>
  </body>
</html>
