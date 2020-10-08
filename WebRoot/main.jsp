<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page  import="vo.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>主页</title>
<link href="/exercise1/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>	
	 <div id="container">
	 	<div id="header">
	 		<div id="rightTop">
	 			当前用户：<span>${sessionScope.currentUser.chrName}</span>&nbsp;[<a href="http://localhost:8080/exercise1/servlet/LogoutController">【安全退出】</a>]
	 		</div>
	 		<div id="menu">
	 			<ul>
	 				<li><a href="#">首页</a></li>
	 				<li class="menuDiv"></li>
	 				<li><a href="http://localhost:8080/exercise1/servlet/GetDownloadListController">资源下载</a></li>
	 				<li class="menuDiv"></li>
	 				<li><a href="http://localhost:8080/exercise1/servlet/UsersController">用户管理</a></li>
	 				<li class="menuDiv"></li>
	 				<li><a href="http://localhost:8080/exercise1/servlet/ResourceController">资源管理</a></li>
	 				<li class="menuDiv"></li>
	 				<li><a href="#">个人中心</a></li>
	 				<li class="menuDiv"></li>
	 			</ul>
	 		</div>
	 		<div id="banner">
	 		</div>
	 	</div>
	 </div>
<!--  	 <b>欢迎你：${sessionScope.currentUser.chrName}</b>
	 <br/>
	<font color="blue"><b>首页 | <a
			href="http://localhost:8080/exercise1/servlet/GetDownloadListController">下载
				| </a> 设计 | 相册 | 论 坛 | 关于
	</b></font> 
	-->
	
</body>
</html>