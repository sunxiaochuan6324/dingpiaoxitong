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
    
    <title>线路信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/cstype.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/mytable.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
	<script type="text/javascript">
		function addbt(){
			$("#flagi").val("add");
			$("#xh").val("");
			dshow();
			$("#gldiv").show();
		}
		function delbt(id){		
			var tid=$("#tid").val();
			if(tid==""){
				return;
			}	
			if(confirm("你确定要删除该信息么？")){
				$.ajax({
					url:'Del',
					dataType:'json',
					type:'post',
					data:{tname:'xianlu',"tid":tid,"zdid":id},
					success:function(data){
						if(data.msg==1){
							alert("删除成功。");
							window.location.href="Init?tname=xianlu&tid="+tid;
						}else{
							alert(data.msg);
						}
					}
				});
			}
		}
		function dshow(){
			$("#nrdiv").hide();
			$("#gldiv").hide();
		}
		//保存
		function gltj(){
			var xh=$("#gxh").val();
			var zdid=$("#gzdid").val();
			var tid=$("#tid").val();
			var flag=$("#flagi").val();
			if(xh==""||zdid==""||tid==""){
				alert('信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'Add',
					type:'post',
					data:{tname:'xianlu',"zdid":zdid,"tid":tid,"xh":xh},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			var tid=$("#tid").val();
			window.location.href="Init?tname=xianlu&tid="+tid;
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
    							线路信息管理
    							<hr>
    						</td>
    					</tr>
    					<tr>
    						<td align="center">
    							<table>
    								<tr>
    									<td>车次：</td>
    									<td>
    										${t.tname }
    										<input id="tid" name="tid" type="hidden" value="${t.id }"/>
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
    									<td colspan="5" align="center">${dlmsg }</td>
    								</tr>
    								<tr bgcolor="#4a4a4a">
    									<th width="50px"  style="color:white;">ID</th>
    									<th width="100px"  style="color:white;">序号</th>
    									<th width="300px"  style="color:white;">线路名称</th>
    									<th width="60px"  style="color:white;">删除</th>
    								</tr>
    								<!-- 
    								 -->
    								<c:forEach var="a" items="${alist}">
    									<tr>
    										<td width="50px"  >${a.id }</td>
    										<td width="100px"  >${a.xh }</td>
	    									<td width="300px"  >${a.zdname }</td>
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
    										<input type="hidden" id="flagi" name="flagi" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td>序号：</td>
    									<td>
    										<input type="text" id="gxh" name="gxh" value="" onkeydown="return numbox();"/>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>站点：</td>
    									<td>
    										<select id="gzdid" name="gzdid">
    											<c:forEach var="zd" items="${zdlist}">
	    											<option value="${zd.id }">${zd.zdname }</option>
    											</c:forEach>
    										</select>
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