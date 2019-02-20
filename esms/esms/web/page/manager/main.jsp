<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页面</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<style type="text/css">
html {
	position: relative;
	min-height: 100%;
}

body {
	margin-bottom: 50px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 30px;
	background-color: #f5f5f5;
	line-height: 30px;
}

.footer-content {
	color: #777;
	font-size: 13px;
}

.header {
	background: #2f333e;
	color: #fff;
	border-radius: 0;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);
	z-index: 10;
	padding-left: 0;
	padding-right: 0;
	height: 50px;
	line-height: 50px;
}

.logo {
	width: 35px;
	height: 35px;
	border-radius: 5px;
	margin-bottom: 5px;
}

.sign-out {
	width: 35px;
	height: 35px;
	border-radius: 5px;
	margin-top: 8px;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 100%;
	background-color: #f1f1f1;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #2f333e;
	color: white;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

a:focus {
	text-decoration: none;
}

a:link {
	text-decoration: none;
}

a:active {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}
</style>

</head>
<body>
	<%
		Map<String, String> user = (Map<String, String>) request.getAttribute("user");
	%>
	<div class="container-fluid">
		<div class="row header">
			<div class="col-md-9">
				<img src="image/logo.png" class="logo" /> 电子超市管理系统
			</div>
			<div class="col-md-2 text-right">
				当前登陆人：<%=user.get("name")%></div>
			<div class="col-md-1 text-center">
				<img src="image/sign-out.jpg" class="sign-out" />
			</div>
		</div>
	</div>
	<div class="container-fluid mt-2">
		<div class="row">
			<div class="col-md-2">
				<ul>
					<li><a class="active" href="#home">订单出货</a></li>
					<li><a href="#news">用户管理</a></li>
					<li><a href="#contact">评价管理</a></li>
					<li><a href="#about">历史订单</a></li>
			</div>
			<div class="col-md-10">content</div>
		</div>
	</div>
	<footer class="footer bg-light">
	<div class="col-md-12 text-center footer-content">Copyright ©
		2019 电子超市管理系统 SuperMarket - All Rights Reserved.</div>
	</footer>
</body>
</html>