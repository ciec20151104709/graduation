<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>电子超市登录</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="css/user/login.css" type="text/css" />

<script language='javascript' src='jquery/jquery-3.2.1.min.js'></script>
<script language='javascript' src='jquery/jquery.page.js'></script>
<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>

<script language="javascript" src="bootstrap/js/bootbox.min.js"></script>
</head>
<body>
	<form method="post" class="form center">
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">
						您还不是我们的会员？<a href="user/register_page" target="_self">立即注册</a>
					</div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">
						用户名:&nbsp;<input class="shurukuang" type="text" id="username" name="username"
							placeholder="请输入你的用户名" />
					</div>
					<div class="username">
						密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang"
							type="password" id="password" name="password" placeholder="请输入你的密码" />
					</div>
				</div>
				<div class="login_submit">
					<input class="submit" type="button" onclick="login();" name="submit" value="立即登录">
				</div>

			</div>
		</div>
	</form>
	<script type="text/javascript">
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "" || password == "") {
			alert("请填写用户名和密码");
			return;
		}
		//通过ajax保存
		$.ajax({
			url : "user/login_check",
			type : "POST",
			dataType : "text",
			data : {
				username: username,
				password : password
			},
			success : function(ret) {
				if (ret == "ERROR") {
					alert("登录失败");
				} else {
					alert("登录成功");
					window.close();
				}
			},
			error : function(res) {
				alert("登录失败，请重新操作！");
			}
		});
	}
	</script>
</body>
</html>