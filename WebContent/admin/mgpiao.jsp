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
    
    <title>车票信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/cstype.css">
	<link href="<%=basePath %>css/jquery.ui.core.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/jquery.ui.datepicker.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/jquery.ui.theme.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/mytable.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.ui.datepicker.js"></script>
	<script type="text/javascript">
		function addbt(){
			$("#flagi").val("add");
			$("#gstime").val("");
			$("#gpval").val("");
			$("#gcxzh").val("");
			$("#gyxsj").val("");
			dshow();
			$("#gldiv").show();
		}
		function delbt(id){			
			if(confirm("你确定要删除该信息么？")){
				$.ajax({
					url:'Del',
					dataType:'json',
					type:'post',
					data:{tname:'piao',idi:id},
					success:function(data){
						if(data.msg==1){
							alert("删除成功。");
							window.location.href="Init?tname=piao";
						}else{
							alert(data.msg);
						}
					}
				});
			}
		}
		function updbt(id,szdid,ezdid,stime,pval,ptype,cxzh,tid,yxsj){
			if(tid!=""&&tid!=null){
				$("#gszdid").html("");
				$("#gezdid").html("");
				if(tid==""){
					return;
				}
				$.ajax({
					url:'GetZdByTid',
					type:'post',
					data:{"tid":tid},
					dataType:'json',
					success:function(data){
						if(data.msg==1){
							var zl=data.zdlist;
							$(zl).each(function(i,z){
								$("#gszdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
								$("#gezdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
							});
							$("#flagi").val("upd");
							$("#idi").val(id);
							$("#gszdid").val(szdid);
							$("#gstime").val(stime);
							$("#gezdid").val(ezdid);
							$("#gptype").val(ptype);
							$("#gpval").val(pval);
							$("#gcxzh").val(cxzh);
							$("#gtid").val(tid);
							$().val();
							dshow();
							$("#gldiv").show();
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
		/**
		**/
		function gltj(){
			var szdid=$("#gszdid").val();
			var stime=$("#gstime").val();
			var ezdid=$("#gezdid").val();
			var ptype=$("#gptype").val();
			var pval=$("#gpval").val();
			var cxzh=$("#gcxzh").val();
			var tid=$("#gtid").val();
			var ids=$("#idi").val();
			var flag=$("#flagi").val();
			var yxsj=$("#gyxsj").val();
			if(szdid==""||ezdid==""||stime==""||pval==""||cxzh==""||tid=="0"||yxsj==""){
				alert('车票信息不完整。');
				return;
			}
			if("add"==flag){
				$.ajax({
					url:'Add',
					type:'post',
					data:{tname:'piao',"szdid":szdid,"stime":stime,"ezdid":ezdid,"pval":pval,"ptype":ptype,"cxzh":cxzh,"yxsj":yxsj,"tid":tid},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}else if("upd"==flag){
				$.ajax({
					url:'UpdSvlt',
					type:'post',
					data:{tname:'piao',"szdid":szdid,"stime":stime,"ezdid":ezdid,"pval":pval,"ptype":ptype,"cxzh":cxzh,"yxsj":yxsj,"tid":tid,idi:ids},
					dataType:'json',
					success:function(data){
						alert(data.msg);
					}
				});
			}
		}
		//取消
		function rst(){
			window.location.href="Init?tname=piao";
		}
		//查询
		/**
		**/
		function cktj(){
			var saddr=$("#cksaddr").val();
			var eaddr=$("#ckeaddr").val();
			var yxsj=$("#ckyxsj").val();
			if(saddr==""||eaddr==""||yxsj==""){
				alert("请输入正确的查询信息");
				return;
			}
			var sqls="select tmp.*,zdname as eaddr,tname,traintype from (select piao.*,zdname as saddr from piao,zhandian where piao.szdid=zhandian.id and yxsj='"+yxsj+"' and zdname like '%"+saddr+"%') tmp,zhandian,traininfo where tmp.tid=traininfo.id and tmp.ezdid=zhandian.id and zdname like '%"+eaddr+"%'";
			$.ajax({
				url:'CkSvlt',
				type:'post',
				data:{sql:sqls},
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						window.location.href="Init?tname=piao";
					}else{
						alert(data.msg);
					}
				}
			});
		}
		/**
		**/
		function getgzd(){
			var tid=$("#gtid").val();
			$("#gszdid").html("");
			$("#gezdid").html("");
			if(tid=="0"){
				return;
			}
			$.ajax({
				url:'GetZdByTid',
				type:'post',
				data:{"tid":tid},
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						var zl=data.zdlist;
						$(zl).each(function(i,z){
							$("#gszdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
							$("#gezdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
						});
					}
				}
			});
		}
		/**
		**/
		function getckzd(){
			var tid=$("#cktid").val();
			$("#ckszdid").html("");
			$("#ckezdid").html("");
			if(tid==""){
				return;
			}
			$.ajax({
				url:'GetZdByTid',
				type:'post',
				data:{"tid":tid},
				dataType:'json',
				success:function(data){
					if(data.msg==1){
						var zl=data.zdlist;
						$(zl).each(function(i,z){
							$("#ckszdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
							$("#ckezdid").append("<option value='"+z.id+"'>"+z.zdname+"</option>");
						});
					}
				}
			});
		}
		window.onload=function(){
			$("#con").css("height",$(document).height()-185);	
		}
		/***
		**/
		$(function () {
            $("#ckyxsj").datepicker();
            $("#gyxsj").datepicker();
        }); 
	</script>

  </head>
  
  <body>
    <center>
    	<table cellspacing="0" cellpadding="0" border="0">    			
    		<tr>
    			<td id="con" align="center" valign="top" style="color:#545454;padding-top: 10px;background-image: url('<%=basePath %>img/bj.jpg');">
    				<table>
    					<tr>
    						<td align="center" style="font-weight:bold; width: 1000px;">
    							车票信息管理
    							<hr>
    						</td>
    					</tr>
    					<tr>
    						<td align="center">
    							<table>
    								<tr>
    									<td>始发站：</td>
    									<td>
    										<input type="text" id="cksaddr" name="cksaddr"/>
    									</td>
    									<td>终点站：</td>
    									<td>
    										<input type="text" id="ckeaddr" name="ckeaddr"/>
    									</td>
    									<td>有效日期：</td>
    									<td>
    										<input type="text" id="ckyxsj" name="ckyxsj"/>
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
    									<td colspan="15" align="center">${dlmsg }</td>
    								</tr>
    								<tr bgcolor="#4a4a4a">
    									<th width="50px"  style="color:white;">ID</th>
    									<th width="100px"  style="color:white;">车次</th>
    									<th width="100px"  style="color:white;">火车类型</th>
    									<th width="100px"  style="color:white;">始发站</th>
										<th width="100px"  style="color:white;">终点站</th>
										<th width="100px"  style="color:white;">发车时间</th>
										<th width="100px"  style="color:white;">票价</th>
										<th width="100px"  style="color:white;">车厢座号</th>
										<th width="100px"  style="color:white;">车票类型</th>
										<th width="100px"  style="color:white;">有效时间</th>
    									<th width="60px"  style="color:white;">修改</th>
    									<th width="60px"  style="color:white;">删除</th>
    								</tr>
    								<!-- 
    								 -->
    								<c:forEach var="a" items="${alist}">
    									<tr>
    										<td width="50px"  >${a.id }</td>
    										<td width="100px"  >${a.tname }</td>
	    									<td width="100px"  >${a.traintype }</td>
	    									<td width="100px"  >${a.szdname}</td>
	    									<td width="100px"  >${a.ezdname }</td>
	    									<td width="100px"  >${a.stime }</td>
	    									<td width="100px"  >${a.pval }</td>
	    									<td width="100px"  >${a.cxzh }</td>
	    									<td width="100px"  >${a.ptype }</td>
	    									<td width="100px"  >${a.yxsj }</td>
    										<td><a href="javascript:updbt('${a.id }','${a.szdid }','${a.ezdid }','${a.stime }','${a.pval }','${a.ptype }','${a.cxzh }','${a.tid }','${a.yxsj }');">修改</a></td>
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
    									<td>列车：</td>
    									<td>
    										<select style="width: 100px" id="gtid" name="gtid" onchange="getgzd();">
    											<option value="0">选择车次</option>
    											<c:forEach var="t" items="${tlist}">
	    											<option value="${t.id }">${t.tname }</option>
    											</c:forEach>
    										</select>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>始发站：</td>
    									<td>
    										<select style="width: 100px" id="gszdid" name="gszdid">
    										</select>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>终点站：</td>
    									<td>
    										<select style="width: 100px" id="gezdid" name="gezdid">
    										</select>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>发车时间：</td>
    									<td>
    										<input type="text" id="gstime" name="gstime" value="" />xx:xx:xx
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>车厢座号：</td>
    									<td>
    										<input type="text" id="gcxzh" name="gcxzh" value="" />
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>票价：</td>
    									<td>
    										<input type="text" id="gpval" name="gpval" value=""/>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>车票类型：</td>
    									<td>
    										<select style="width: 100px" id="gptype" name="gptype">
    											<option>硬卧</option>
    											<option>软卧</option>
    											<option>硬座</option>
    										</select>
    									</td>
    								</tr>
    								<tr>
    									<td height="15" colspan="2"></td>
    								</tr>
    								<tr>
    									<td>有效日期：</td>
    									<td>
    										<input type="text" id="gyxsj" name="gyxsj" value=""/>
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