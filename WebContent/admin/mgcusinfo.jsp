<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/cstype.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/mytable.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript">
		function addbt(){
			$("#flagi").val("add");
			$("#gcidcard").val("");
			$("#gcname").val("");
			$("#gcpassword").val("");
			dshow();
			$("#gldiv").show();
		}
		function delbt(id){			
			if(confirm("你确定要删除该信息么？")){
				$.ajax({
					url:'Del',
					dataType:'json',
					type:'post',
					data:{tname:'cusinfo',idi:id},
					success:function(data){
						if(data.msg==1){
							alert("删除成功。");
							window.location.href="Init?tname=cusinfo";
						}else{
							alert(data.msg);
						}
					}
				});
			}
		}
		function updbt(id,un,up,um){
			$("#flagi").val("upd");
			$("#idi").val(id);
			$("#gcidcard").val(un);
			$("#gcpassword").val(up);
			$("#gcname").val(um);
			dshow();
			$("#gldiv").show();
		}
		function dshow(){
			$("#nrdiv").hide();
			$("#gldiv").hide();
		}
		//保存
		function gltj(){
			var un=$("#gcidcard").val();
			var up=$("#gcpassword").val();
			var um=$("#gcname").val();
			var ids=$("#idi").val();
			var flag=$("#flagi").val();
			if(un==""||up==""||um==""){
				alert('账户信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'Add',
					type:'post',
					data:{tname:'cusinfo',cidcard:un,cpassword:up,cname:um},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'UpdSvlt',
					type:'post',
					data:{tname:'cusinfo',cidcard:un,cpassword:up,cname:um,idi:ids},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			$("#flagi").val("");
			$("#idi").val("");
			$("#gcidcard").val("");
			$("#gcpassword").val("");
			$("#gcname").val("");
			window.location.href="Init?tname=cusinfo";
		}
		//查询
		function cktj(){
			var un=$("#ckcidcard").val();
			var sqls="select * from cusinfo";
			if(un!=""){
				sqls="select * from cusinfo where cidcard='"+un+"'"
			}
			$.ajax({
				url:'CkSvlt',
				type:'post',
				data:{sql:sqls},
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						window.location.href="Init?tname=cusinfo";
					}else{
						alert(data.msg);
					}
				}
			});
		}
		window.onload=function(){
			$("#con").css("height",$(document).height()-185);	
		}
	</script>

  </head>
  
  <body>
    <center>
    	<table cellspacing="0" cellpadding="0" border="0">    			
    		<tr>
    			<td id="con" align="center" valign="top" style="color:#3f3f3f;padding-top: 10px;background-image: url('<%=basePath %>img/bj.jpg');">
    				<table>
    					<tr>
    						<td align="center" style="font-weight:bold; width: 1000px;">
    							会员信息管理
    							<hr>
    						</td>
    					</tr>
    					<tr>
    						<td align="center">
    							<table>
    								<tr>
    									<td>身份证号：</td>
    									<td>
    										<input type="text" id="ckcidcard" name="ckcidcard"/>
    									</td>
    									<td>
    										<input type="button" value="查  询" onclick="cktj();"/>
    									</td>
    									<td>
    										<input type="button" value="添  加" onclick="addbt();"/>
    									</td>
    								</tr>
    							</table>
    						</td>
    					</tr>
    					<tr>
    						<td align="center" valign="top">
    							<div id="nrdiv">
    							<table border="0" cellpadding="0" cellspacing="0" class="mytable">
    								<tr>
    									<td colspan="7" align="center">${dlmsg }</td>
    								</tr>
    								<tr bgcolor="#4a4a4a">
    									<th width="50px"  style="color:white;">ID</th>
    									<th width="150px"  style="color:white;">姓名</th>
    									<th width="200px"  style="color:white;">身份证号</th>
    									<th width="100px"  style="color:white;">密码</th>
										<th width="150px"  style="color:white;">注册时间</th>
    									<th width="60px"  style="color:white;">修改</th>
    									<th width="60px"  style="color:white;">删除</th>
    								</tr>
    								<!-- 
    								 -->
    								<c:forEach var="a" items="${alist}">
    									<tr>
    										<td width="50px"  >${a.id }</td>
    										<td width="150px"  >${a.cname }</td>
	    									<td width="200px"  >${a.cidcard }</td>
	    									<td width="100px"  >${a.cpassword}</td>
	    									<td width="150px"  >${a.intime }</td>
    										<td><a href="javascript:updbt('${a.id }','${a.cidcard }','${a.cpassword }','${a.cname }');">修改</a></td>
    										<td><a href="javascript:delbt('${a.id }');">删除</a></td>
    									</tr>
    								</c:forEach>
    							</table>
    							
    						</div>
    						<div id="gldiv" style="display: none;">
    							<table>
    								<tr>
    									<td height="15" colspan="2" align="center" style="color: red;">
    										<input type="hidden" id="idi" name="idi" value=""/>
    										<input type="hidden" id="userid" name="userid" value="${userid }"/>
    										<input type="hidden" id="flagi" name="flagi" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td>姓名：</td>
    									<td>
    										<input type="text" id="gcname" name="gcname" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>身份证号：</td>
    									<td>
    										<input type="text" id="gcidcard" name="gcidcard" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>登录密码：</td>
    									<td>
    										<input type="text" id="gcpassword" name="gcpassword" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2" align="right">
    										<input type="button" value="确定" onclick="gltj();"/>
    										<input type="button" value="取消" onclick="rst();"/>
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
    </center>
  </body>
</html>