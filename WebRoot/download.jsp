<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="vo.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>下载列表</title>
	<link rel="stylesheet" type="text/css" href="/exercise1/css/download.css">
</head>
<body>
<!--  	<c:forEach items="${downloadList }" var="Download" >
		<c:url value="http://localhost:8080/exercise1/servlet/DownloadController" var="downurl">
		<c:param name="path" value="${Download.path }"></c:param>
		</c:url>
			
		${Download.name }<a href="${downurl }">立即下载</a>
		</br>
	</c:forEach>
	-->
	<h1>资源下载</h1>
	<div class="container">
		<c:forEach items="${downloadList }" var="download">
			<ul>
				<li>
					<p class="name">${download.name }</p>
					<div class="pic-txt">
						<img class="img-area" src="${download.image }"/>
						<p class="txt-area">
							<span class="">${download.description }</span>
							<span class="tit-sub">
								<i class="space">时间：000</i>
								<i class="space">大小：${download.size }</i>
								<i>星级：</i>
								<i class="stars">
									<span style="width:${download.star/5*100}%"></span>
								</i>
							</span>
							</p>
					</div>
					<a class="dl-btn" href="http://localhost:8080/exercise1/servlet/DownloadController?id=${download.id }" title="点击下载">下载</a>
				</li>
			</ul>
		</c:forEach>
		<p class="back"><a href="/exercise1/main.jsp">返回首页</a></p>
	</div>
</body>
</html>