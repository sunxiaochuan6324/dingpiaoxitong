<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <base href="<%=basePath%>">
    	<table cellspacing="0" cellpadding="0" border="0">
					<tr style="background-color:#AF7AFF;color: white;font-size: 15px;">
						<td width="360px" height="30px">
							欢迎使用网上订票系统<c:if test="${empty userid}"><BR/>
							<a class="tp2"  href="InitLoginSvlt?sflag=0">&nbsp;&nbsp;登陆</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a class="tp2"  href="InitLoginSvlt?sflag=1">注册</a>
							</c:if>
							<c:if test="${!empty userid}">
								<a class="tp2" href="<%=basePath %>LogoutSvlt?flag=cus">注销</a>
							</c:if>
						</td>
						<td width="664px" align="center">
							<c:if test="${!empty userid}">
							    <a class="tp2" target="mainf" href="CusCkSvlt">车票查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="tp2" target="mainf" href="DpListSvlt">我的订单</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="tp2" target="mainf" href="changePwd.jsp">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<a class="tp2" href="<%=basePath %>admin/login.jsp" style="font-weight: bolder;">管理员登录</a>
						</td>
					</tr>
					<tr>
						<td height="120px" width="1024px" colspan="2">
							<a class="tp2" href="index.jsp">
								<img border="0" height="320px" width="1024px" alt="" src="<%=basePath %>images/top_bg3.jpg"/>
							</a>
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 260px;background-color:#0C3F16; color: white;font-weight: bold;height: 2px;">
						</td>
						<td style="width:664px; background-color:#0C3F16; height: 2px;">
						</td>
					</tr>
				</table>
