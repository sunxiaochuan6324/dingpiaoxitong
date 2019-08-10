<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆/注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/topa.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/reg.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript">
        function showdiv(divid,aid) {
            $("#lgdiv").hide();
            $("#rgdiv").hide();
            var did = "#" + divid;
            var aaid = "#" + aid;
            $("#lga").css("background-color", ""); 
            $("#lga").css("color", "");
            $("#rga").css("background-color", "");
            $("#rga").css("color", "");
            $(aaid).css("background-color", "#4f4f4f;");
            $(aaid).css("color", "White");
            $(did).show();
        }
        function rgcheck() {
            var idcard = $("#rgidcard").val();
            var password = $("#rgpassword").val();
            var sname=$("#rgsname").val();
            if (idcard == "" || password == ""||sname=="") {
                alert("请输入完整的个人信息。");
                return false;
            }
            $.ajax({
            	url:'RegSvlt',
            	type:'post',
            	data:{rgidcard:idcard,rgpassword:password,rgsname:sname},
            	dataType:'json',
            	success:function(data){
            		alert(data.msg);
            	}
            });
            return true;
        }
        function rst(){
			$("#lguname").val("");
			$("#lgupassword").val("");			
		}
		function lgtj(){
			var name=$("#lguname").val();
			var password=$("#lgupassword").val();
			if(name==""||password==""){
				alert("账号和密码都不能为空。");
				return;
			}
			$.ajax({
				url:'LoginSvlt',
				data:{uname:name,upassword:password,utype:'cus'},
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						window.location.href="index.jsp";
					}else{
						alert(data.msg);
					}
				}
			});
		}
    </script>
  </head>
  <body>
  <center>
    <table cellspacing="0" cellpadding="0" style="border: 1px solid #C0C0C0">
    	<tr>
    		<td>
    			<jsp:include page="/top.jsp"></jsp:include>
    		</td>
    	</tr>
    	<tr>
    		<td height="570px" width="1100px" align="center" valign="middle">
    			<table cellspacing="0" cellpadding="0" border="0">
    				<tr>    					
    					<td align="center" valign="middle">
    					<table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td valign="top">
                                <table style="padding-top:50px;" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td><a id="lga" class="ml" href="javascript:showdiv('lgdiv','lga')" <c:if test="${sflag==0}">style="background-color:#4f4f4f;color:white;"</c:if>>登陆</a></td>
                                    </tr>
                                    <tr>
                                        <td height="80px"></td>
                                    </tr>
                                    <tr>
                                        <td><a id="rga" class="ml" href="javascript:showdiv('rgdiv','rga')" <c:if test="${sflag==1}">style="background-color:#4f4f4f;color:white;"</c:if>>注册</a></td>
                                    </tr>
                                </table>
                            </td>
                            <td align="center">
                                <div id="lgdiv" style="border: 2px solid #4f4f4f; width:450px; height:300px; padding-top: 50px; <c:if test="${sflag!=0}">display: none;</c:if>">
                                    <table style="padding-top:30px;">
                                    	<tr>
                                    		<td colspan="2" align="center" style="color:red;"></td>
                                    	</tr>
                                        <tr>
                                            <td height="30px"><font><b>账 号：</b></font></td>
                                            <td>
                                                <input width="120px" id="lguname" name="lguname" type="text" value=""/>
                                                <input  name="utype" type="hidden" value="cus"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2" height="30px">
                                            	<label style="color:#7a7a7a;">输入身份证号码</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="30px"><font><b>密 码：</b></font></td>
                                            <td>
                                                <input width="120px" id="lgupassword" name="lgupassword" type="password" value=""/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2" height="30px">
                                            	<label style="color:#7a7a7a;">输入登陆密码</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" height="30px" align="center">                                                
                                                <input type="button" value="登 陆" onclick="lgtj();"/>     
                                                <input type="button" value="重 置" onclick="rst();">
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="rgdiv" style="border: 2px solid #4f4f4f; width:450px; height:300px; padding-top: 50px; <c:if test="${sflag!=1}">display: none;</c:if>">
                                    <table style="padding-top:10px;">
                                        <tr>
                                            <td height="30px;"><font><b>身份证号：</b></font></td>
                                            <td>
                                               <input id="rgidcard" name="rgidcard" type="text" value=""/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><label style="color:#7a7a7a;">用于登录的账号</label></td>
                                        </tr>
                                                                          
                                        <tr>
                                            <td height="30px;"><font><b>登录密码：</b></font></td>
                                            <td>
                                                <input id="rgpassword" name="rgpassword" type="text" value=""/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><label style="color:#7a7a7a;">输入登陆密码</label></td>
                                        </tr>
                                         <tr>
                                            <td height="30px;"><font><b>真实姓名：</b></font></td>
                                            <td>
                                                <input id="rgsname" name="rgsname" type="text" value=""/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><label style="color:#7a7a7a;">输入个人真实姓名</label>
											</td>
                                        </tr>                                                
                                        <tr>
                                            <td align="center" colspan="2" height="30px;">
                                            	<input type="button" value="注  册" onclick="rgcheck();"/>
                                            </td>                                            
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
    					</td>
    				</tr>
    			</table>
    		</td>
    	</tr>
    </table>
  </center>
  </body>
</html>