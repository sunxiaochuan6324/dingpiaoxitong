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
		//查询
		function cktj(){
			var un=$("#cktname").val();
			var sqls="select * from traininfo";
			if(un!=""){
				sqls="select * from traininfo where tname like '%"+un+"%'"
			}
			$.ajax({
				url:'CkSvlt',
				type:'post',
				data:{sql:sqls},
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						window.location.href="Init?tname=initxl";
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
    			<td id="con" align="center" valign="top" style="color:#e0e0e0;padding-top: 10px;background-image: url('<%=basePath %>login.png');">
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
    										<input type="text" id="cktname" name="cktname"/>
    									</td>
    									<td>
    										<input type="button" value="查  询" onclick="cktj();"/>
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
    									<th width="90px"  style="color:white;">车次</th>
    									<th width="700px"  style="color:white;">线路</th>
    									<th width="100px"  style="color:white;">线路管理</th>
    								</tr>
    								<!-- 
    								 -->
    								<c:forEach var="a" items="${alist}">
    									<tr>
    										<td width="90px"  >${a.tname }</td>
	    									<td width="700px"  >
												<c:forEach varStatus="vs" var="zd" items="${a.zdlist}">
													${zd.zdname }
													<c:if test="${!vs.last}">—</c:if>
												</c:forEach>
											</td>
    										<td><a href="Init?flgs=1&tname=xianlu&tid=${a.tid }">线路管理</a></td>
    									</tr>
    								</c:forEach>
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