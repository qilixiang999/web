<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>出错啦</title>
	<link href="/exercise1/css/error.css" rel="stylesheet" />
	<script src="/exercise1/js/error.js"></script>
</head>

<body>
	<div id="errorDiv">
		<div id="errorHint">
			<p id="errorInfo">${info}</p>
			<p><span id="leaveTime">10</span>秒后自动返回到登陆页面</p>
			<p>不能跳转，请<a href="/exercise1/login.html">点击这儿</a></p>
		</div>
	</div>
</body>
</html>