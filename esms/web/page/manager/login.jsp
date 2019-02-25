<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>管理员登陆页面</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>

<style type="text/css">
body {
	background: url(image/background.jpg) no-repeat;
	background-size: cover;
	font-size: 16px;
}

.form {
	background: rgba(255, 255, 255, 0.1);
	padding: 20px;
	padding-left: 40px;
	padding-right: 40px;
	border-radius: 10px;
}
</style>
</head>
<body>
	<%
		String loginInfo = (String) request.getAttribute("loginInfo");
		if (loginInfo == null) {
			loginInfo = "";
		}
	%>
	<input type="hidden" id="loginInfo" value="<%=loginInfo%>" />
	<div class="container-fluid" style="margin-top: 5%;">
		<div class="row">
			<div class="offset-2 col-md-8 text-center">
				<h1 class="text-white">电子超市管理系统后台登录</h1>
			</div>
		</div>
		<div class="row" style="margin-top: 10%;">
			<div class="offset-4 col-md-4">
				<form class="form form-horizontal">
					<div class="form-group"></div>
					<div class="form-group">
						<input type="text" id="account" class="form-control"
							placeholder="账　号"> <i class="fa fa-user"></i>
					</div>
					<div class="form-group help">
						<input type="password" id="password" class="form-control"
							id="inputPassword3" placeholder="密　码"> <i
							class="fa fa-lock"></i> <a href="#" class="fa fa-question-circle"></a>
					</div>
					<div class="form-group text-right">
						<button type="button" onclick="login()" class="btn btn-primary">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		// 判断是否需要在登陆页面给提示
		$(document).ready(function() {
			var loginInfo = $("#loginInfo").val();
			if (loginInfo != "") {

				bootbox.alert(loginInfo);

				$("#loginInfo").val("");
			}
		});

		// 登陆校验
		function login() {
			var account = $("#account").val();
			var password = $("#password").val();
			window.location.href = "/esms/manager/login_check?account="
					+ account + "&password=" + password;
		}
	</script>
</body>
</html>