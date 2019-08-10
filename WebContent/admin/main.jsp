<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>后台管理系统</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
	<link rel="StyleSheet" href="<%=basePath %>css/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>js/dtree.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			$("#con").css("height",$(document).height()-185);	
		}
	</script>
	<style type="text/css">
    body{
        padding:0;
        margin:0;
    }
    div,ul,li,img,a,p{
        padding:0;margin:0;
    }
    img{
        border:0;
    }
    ul,li{
        list-style-type: none;
    }

    .l{
        float: left !important;
    }
    .r{
        float: right !important;
    }
    a{
        text-decoration: none;
    }
    #header{
         width:94%;
         overflow: hidden;
         height:75px;
         padding:0 3%;
         padding-top:25px;
        background: #c1c1c1; /* Old browsers */
        /*background: -moz-linear-gradient(bottom, #1b91d7 0%, #2297dd 33%, #1e93d6 53%, #2297dd 61%, #0781cc 100%);
        background: -webkit-gradient(linear, bottom left, bottom left, color-sleft(0%,#1b91d7), color-sleft(33%,#2297dd), color-sleft(53%,#1e93d6), color-sleft(61%,#2297dd), color-sleft(100%,#0781cc));
        background: -webkit-linear-gradient(bottom, #1b91d7 0%,#2297dd 33%,#1e93d6 53%,#2297dd 61%,#0781cc 100%);
        background: -o-linear-gradient(bottom, #1b91d7 0%,#2297dd 33%,#1e93d6 53%,#2297dd 61%,#0781cc 100%);
        background: -ms-linear-gradient(bottom, #1b91d7 0%,#2297dd 33%,#1e93d6 53%,#2297dd 61%,#0781cc 100%);*/
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1b91d7', endColorstr='#0781cc',GradientType=0 );
        background: linear-gradient(bottom #1b91d7 0%,#2297dd 33%,#1e93d6 53%,#2297dd 61%,#0781cc 100%);
     }
    .logo-left{
        margin-top:10px;
        padding-right:40px;
    }
    .center-cont, .center-cont ul{
        height:50px;
        line-height:50px;
    }
    .center-cont ul li{
        float: left;
        padding:0 25px;
    }
    .center-cont ul li a{
        color:#fff;
        font-size:20px;
    }
    .center-cont ul li a:hover{
        color:#c6e3ff;
    }
    .set-up a{
        display: block;
        float: left;
        margin-right:20px;
        height:54px;
        line-height: 54px;;
        font-size:14px;
        color:#fff;
    }
    .set-up a:hover{
        color:#c6e3ff;
    }
    .set-up a span{
        display: block;
        width:35px;
        height:35px;
        float: left;
        margin-top:7px;
        margin-right:5px;
    }

  </style>
  </head>
  <body scroll="no">
 
  	 <div id="header">
    <div class="logo-left l">
        
    </div>
    <div class="center-cont l">
        <ul>
            <li><a href="<%=basePath %>/admin/login.jsp">后台管理</a></li>
            <li><a href="<%=basePath %>">主界面</a></li>
           
        </ul>
    </div>
    <div class="set-up r">
        <a href="" id="name"><span></span>用户名：${username }</a>
        <a href="<%=basePath %>changePwd.jsp" target="page" id="setup"><span></span>修改密码</a>
        <a href="" id="help" target="_blank"><span></span>主界面</a>
        <a href="<%=basePath %>LogoutSvlt?flag=gly" id="lose"><span></span>管理员注销</a>
    </div>
</div>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

	  <tr>
	    <td height="100%"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="background-repeat: repeat-x;">
	        <tr>
	          <td width="175" height="100%" valign="top"><table width="175" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=basePath %>images/menu_bg.gif">
	             <!--   <tr>
	                <td height="25"><img src="<%=basePath %>images/menu_top.gif" width="175" height="25"/></td>
	              </tr>-->
	              <tr>
	                <td height="100%" style="background-image:url(<%=basePath %>images/menu_bgT.gif); background-repeat:no-repeat" valign="top"><div class="dtree">
	                    <script type="text/javascript">
						d = new dTree('d');
						d.config.stepDepth = 1;
						d.config.useStatusText = true;
						 
						d.add(0,-1,' <strong>后台管理</strong>');
						d.add(1,0,'火车信息管理');
						d.add(101,1,'用户信息管理');						
						d.add(10101,101,'管理员信息管理','Init?flgs=1&tname=admin',"",'page');
						d.add(10102,101,'会员信息管理','Init?flgs=1&tname=cusinfo',"",'page');
						d.add(102,1,'基本信息管理');
						d.add(10201,102,'火车信息管理','Init?flgs=1&tname=traininfo',"",'page');						
						d.add(10202,102,'站点信息管理','Init?flgs=1&tname=zhandian',"",'page');
						d.add(10203,102,'线路信息管理','Init?flgs=1&tname=initxl',"",'page');
						d.add(103,1,'车票信息管理');
						d.add(10301,103,'车票信息管理','Init?flgs=1&tname=piao',"",'page');
						d.add(10302,103,'订票信息管理','Init?flgs=1&tname=dpiao',"",'page');
						document.writeln(d);
						</script>
	                </div></td>
	              </tr>
	              <tr>
	                <td height="31"><img src="<%=basePath %>images/menu_foot.gif" width="175" height="31"></td>
	              </tr>
	            </table></td>
	          <td id="con" height="100%" valign="top" align="center"><iframe src="<%=basePath %>Init?flgs=1&tname=admin" width="100%" height="100%" frameborder="0" scrolling="yes" name="page"></iframe></td>
	        </tr>
	      </table></td>
	  </tr>
	</table>
</body>
</html>